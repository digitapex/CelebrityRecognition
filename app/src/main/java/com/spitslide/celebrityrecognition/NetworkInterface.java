package com.spitslide.celebrityrecognition;


import com.spitslide.celebrityrecognition.qwant.Qwant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("api/search/images")
    Call<Qwant> getReponse(@Query("count") int count, @Query("q") String q, @Query("t") String t, @Query("uiv") int uiv);
}
