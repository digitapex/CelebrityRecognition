package com.spitslide.celebrityrecognition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.spitslide.celebrityrecognition.qwant.Qwant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetectionActivity extends AppCompatActivity {

    private MatchesAdapter matchesAdapter;
    private String apiKey = BuildConfig.API_KEY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<String> imageUrls = new ArrayList<>();
        matchesAdapter = new MatchesAdapter(imageUrls);
        recyclerView.setAdapter(matchesAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.qwant.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);




    }

    private void qwantCall(String query, final ArrayList<String> imageUrls, NetworkInterface networkInterface) {
        Call<Qwant> call = networkInterface.getReponse(1, query, "images", 1);
        call.enqueue(new Callback<Qwant>() {
            @Override
            public void onResponse(Call<Qwant> call, Response<Qwant> response) {
                String imageUrl = response.body().getData().getResult().getItems().get(0).getMedia();
                matchesAdapter.updateData(imageUrl);
            }

            @Override
            public void onFailure(Call<Qwant> call, Throwable t) {

            }
        });
    }
}
