package com.example.maha.webserviceapplicationmaha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.maha.webserviceapplicationmaha.Adapters.MovieAdapter;
import com.example.maha.webserviceapplicationmaha.DataModel.MovieModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    ListView movieList;
    Button search;
    String sortByVariable;
    RadioGroup radioGroup;
    EditText year;
    //String yearNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (Button) findViewById(R.id.buttonSearch);
        movieList = (ListView) findViewById(R.id.movieList);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        year = (EditText) findViewById(R.id.year);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButtonPop:
                        sortByVariable = "&sort_by=popularity.asc";
                        //yearNo =year.getText().toString();
                        break;
                    case R.id.radioButtonRelease:
                        sortByVariable = "&sort_by=release_date.asc";
                        //yearNo =year.getText().toString();
                        break;
                }
                String url = "http://api.themoviedb.org/3/discover/movie?api_key=365f12b479aefadba7d0ba147412b338" + sortByVariable + "&language=en-US&page=1";//&year=+yearNo
                executeWebService(url);
            }
        });


    }//main


    void executeWebService(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    final MovieModel[] movieModels;
                    movieModels = new Gson().fromJson(jsonArray.toString(), MovieModel[].class);

                    MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, movieModels);
                    movieList.setAdapter(movieAdapter);
                    movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent intent = new Intent(MainActivity.this, Details.class);

                            intent.putExtra("MovieModel",movieModels[i]);
                            startActivity(intent);



                        }


                    });

////////////////////////////////////////////////////////////

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

}