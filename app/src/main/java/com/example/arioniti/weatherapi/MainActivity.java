package com.example.arioniti.weatherapi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.arioniti.weatherapi.pojo.Weather;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arioniti on 20/01/18.
 */
public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    SQLiteDatabase articleDatabase;

    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String>coordList = new ArrayList<>();
    ArrayList<String>visibilityList= new ArrayList<>();

    TextView name,coord,visibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleDatabase = this.openOrCreateDatabase("Weather",MODE_PRIVATE,null);
        articleDatabase.execSQL("CREATE TABLE IF NOT EXISTS weather (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, coord VARCHAR, visibility VARCHAR)");

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

                String sql = "INSERT INTO weather  (name, coord, visibility) VALUES (?, ?, ?)";
                SQLiteStatement statement = articleDatabase.compileStatement(sql);
                statement.bindString(1,name.getText().toString());
                statement.bindString(2,coord.getText().toString());
                statement.bindString(3,visibility.getText().toString());

                statement.execute();
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
        updateListView();
    }
    public void updateListView(){
        Cursor c = articleDatabase.rawQuery("Select * from weather",null);

        int nameIndex= c.getColumnIndex("name");
        int coordIndex = c.getColumnIndex("coord");
        int visibilityIndex = c.getColumnIndex("visibility");

        if(c.moveToFirst()){
            nameList.clear();
            coordList.clear();
            visibilityList.clear();

            do{
                nameList.add(c.getString(nameIndex));
                coordList.add(c.getString(coordIndex));
                visibilityList.add(c.getString(visibilityIndex));
            }while(c.moveToNext());
        }

        name.setText(nameList.get(0).toString());
        visibility.setText(visibilityList.get(0).toString());
        coord.setText(coordList.get(0).toString());

    }
}
