package com.spitslide.celebrityrecognition;


import com.spitslide.celebrityrecognition.contextualwebsearch.ContextualAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("api/Search/ImageSearchAPI")
    Call<ContextualAPI> getReponse(@Query("q") String q, @Query("count") int count);
}
