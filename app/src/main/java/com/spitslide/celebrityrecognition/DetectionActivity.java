package com.spitslide.celebrityrecognition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.spitslide.celebrityrecognition.contextualwebsearch.ContextualAPI;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.FaceConceptsModel;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import clarifai2.dto.prediction.FaceConcepts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetectionActivity extends AppCompatActivity {

    private static final int RESULTS_COUNT = 3;
    private static final int IMAGES_PER_PERSONS = 5;
    private MatchesAdapter matchesAdapter;
    private String apiKey = BuildConfig.API_KEY;
    private NetworkInterface networkInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        TextView noResults = findViewById(R.id.no_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<Match> matches = new ArrayList<>();
        // we create empty items, so that latter we can update the exact position when asynchronously getting back results
        for (int i = 0; i < RESULTS_COUNT; i++) {
            matches.add(new Match());
        }
        matchesAdapter = new MatchesAdapter(matches);
        recyclerView.setAdapter(matchesAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://contextualwebsearch.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        networkInterface = retrofit.create(NetworkInterface.class);

        new ImagesFetchAsyncTask(recyclerView, noResults).execute();

//        ArrayList<String> queries = new ArrayList<>();
//        queries.add("dog");
//        queries.add("cat");
//        queries.add("duck");
//        queries.add("chicken");
//        queries.add("snake");
//
//        for (String query : queries) {
//            contextualApiCall(query);
//        }



    }

    private List<Concept> getClarifaiMatches() {
        String bitmapFilePath = getIntent().getExtras().getString("photoFile");
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapFilePath);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] byteArray = stream.toByteArray();
        Log.d("MY", byteArray.length + "");

        ClarifaiClient client = new ClarifaiBuilder(apiKey).buildSync();
        FaceConceptsModel model = client.getDefaultModels().celebrityModel();
        ClarifaiResponse<List<ClarifaiOutput<FaceConcepts>>> response = model.predict()
                .withInputs(ClarifaiInput.forImage(byteArray))
                .executeSync();
        Log.d("MY", response.rawBody());
//        String name = response.get().get(0).data().get(0).concepts().get(0).name();
        List<Concept> matches = new ArrayList<>();
        if(response.get().get(0).data().size() > 0) {
            matches = response.get().get(0).data().get(0).concepts();
        }
        return matches;

    }

    private void contextualApiCall(final List<Concept> matches) {
        for (int i = 0; i < RESULTS_COUNT; i++) {
            final int position = i;
            final String currentName = matches.get(i).name();
            final float currentValue = matches.get(i).value();
            DecimalFormat df = new DecimalFormat("##.##%");
            final String currenValuePercent = df.format(currentValue);
            Log.d("MY", "current name" + currentName);
            Call<ContextualAPI> call = networkInterface.getReponse(currentName, IMAGES_PER_PERSONS);
            call.enqueue(new Callback<ContextualAPI>() {
                @Override
                public void onResponse(Call<ContextualAPI> call, Response<ContextualAPI> response) {

                    // we save several image links per person because some don't work and we might need to try a different link in the adapter
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < response.body().getValue().size(); i++) {
                        imageUrls.add(response.body().getValue().get(i).getUrl());
                    }
                    Match match = new Match();
                    match.setUrls(imageUrls);
                    String titleCaseName = TitleCaseUtil.toTitleCase(currentName);
                    match.setName(titleCaseName);
                    match.setValue(currenValuePercent);
                    matchesAdapter.updateData(position, match);
                }

                @Override
                public void onFailure(Call<ContextualAPI> call, Throwable t) {

                }
            });
        }
    }


    private class ImagesFetchAsyncTask extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private TextView noResults;

        public ImagesFetchAsyncTask(RecyclerView recyclerView, TextView noResults) {
            this.recyclerView = recyclerView;
            this.noResults = noResults;
        }

        @Override
        protected String doInBackground(String... strings) {
            List<Concept> matches = getClarifaiMatches();
            String string = "";
            if (matches.size() > 0) {
                contextualApiCall(matches);
            } else {
                string = null;
            }
            return string;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                recyclerView.setVisibility(View.GONE);
                noResults.setVisibility(View.VISIBLE);
            }
        }
    }
}
