package com.example.arioniti.weatherapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.arioniti.weatherapi.pojo.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arioniti on 20/01/18.
 */
public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;

    TextView name,coord,visibility,clouds,base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView)findViewById(R.id.nameInput);
        coord = (TextView)findViewById(R.id.coordInput);
        visibility = (TextView)findViewById(R.id.visibilityInput);
        apiInterface = RetrofitClient.getClient().create(APIInterface.class);

        Call<Weather> call = apiInterface.getWeather("2172797","2d16334731df0891ec0aae3edf3d73af");
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                name.setText(response.body().getName());
                visibility.setText(response.body().getCod()+"");
                coord.setText(response.body().getVisibility()+"");
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

    }
}
