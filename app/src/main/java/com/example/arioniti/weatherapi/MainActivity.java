package com.example.arioniti.weatherapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.openweathermap.org/";
    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
