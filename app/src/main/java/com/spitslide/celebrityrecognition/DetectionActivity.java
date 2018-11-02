package com.spitslide.celebrityrecognition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.spitslide.celebrityrecognition.contextualwebsearch.ContextualAPI;

import java.io.ByteArrayOutputStream;
import java.io.File;
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

    private MatchesAdapter matchesAdapter;
    private String apiKey = BuildConfig.API_KEY;
    private NetworkInterface networkInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<Match> matches = new ArrayList<>();
        matchesAdapter = new MatchesAdapter(matches);
        recyclerView.setAdapter(matchesAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://contextualwebsearch.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        networkInterface = retrofit.create(NetworkInterface.class);

        new Thread(){
            @Override
            public void run() {
                List<Concept> matches = getClarifaiMatches();
                contextualApiCall(matches);

            }
        }.start();

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
        List<Concept> matches = response.get().get(0).data().get(0).concepts();
//        Log.d("MY", name);
        return matches;

    }

    private void contextualApiCall(final List<Concept> matches) {
        for (int i = 0; i < 3; i++) {
            final String currentName = matches.get(i).name();
            Log.d("MY", "current name" + currentName);
            Call<ContextualAPI> call = networkInterface.getReponse(currentName, 1);
            call.enqueue(new Callback<ContextualAPI>() {
                @Override
                public void onResponse(Call<ContextualAPI> call, Response<ContextualAPI> response) {

                    String imageUrl = response.body().getValue().get(0).getUrl();
                    Match match = new Match();
                    match.setUrl(imageUrl);
                    match.setName(currentName);
                    matchesAdapter.updateData(match);
                }

                @Override
                public void onFailure(Call<ContextualAPI> call, Throwable t) {

                }
            });
        }
    }
}
