package com.example.arioniti.weatherapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arioniti.weatherapi.pojo.APIInterface;
import com.example.arioniti.weatherapi.pojo.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.openweathermap.org/";
    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();





    }
}
