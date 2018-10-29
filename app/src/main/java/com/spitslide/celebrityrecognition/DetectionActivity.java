package com.spitslide.celebrityrecognition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.spitslide.celebrityrecognition.contextualwebsearch.ContextualAPI;

import java.util.ArrayList;

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

        ArrayList<String> queries = new ArrayList<>();
        queries.add("dog");
        queries.add("cat");
        queries.add("duck");
        queries.add("chicken");
        queries.add("snake");

        for (String query : queries) {
            contextualApiCall(query);
        }



    }

    private void contextualApiCall(final String query) {
        Call<ContextualAPI> call = networkInterface.getReponse(query, 1);
        call.enqueue(new Callback<ContextualAPI>() {
            @Override
            public void onResponse(Call<ContextualAPI> call, Response<ContextualAPI> response) {
                String imageUrl = response.body().getValue().get(0).getUrl();
                Match match = new Match();
                match.setUrl(imageUrl);
                match.setName(query);
                matchesAdapter.updateData(match);
            }

            @Override
            public void onFailure(Call<ContextualAPI> call, Throwable t) {

            }
        });
    }
}
