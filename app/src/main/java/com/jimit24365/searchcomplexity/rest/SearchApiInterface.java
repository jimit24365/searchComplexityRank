package com.jimit24365.searchcomplexity.rest;


import com.jimit24365.searchcomplexity.models.SearchResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jimit on 23/10/16.
 */
public interface SearchApiInterface {
    @GET("v1")
    Call<SearchResponse> searchInternet(@Query("cx") String cx, @Query("key") String apiKey, @Query("q") String lecture);
}
