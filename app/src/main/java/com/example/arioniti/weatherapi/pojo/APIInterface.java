package com.example.arioniti.weatherapi.pojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Arioniti on 19/01/18.
 */

public interface APIInterface {
        @GET("data/2.5/weather")
        Call<Weather> getWeather(@Query("id") String userLanguage,@Query("appid") String appID);
}
