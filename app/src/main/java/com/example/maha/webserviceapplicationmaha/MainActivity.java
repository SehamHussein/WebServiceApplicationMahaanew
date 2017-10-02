package com.example.maha.webserviceapplicationmaha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.maha.webserviceapplicationmaha.DataModel.MovieModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ListView movieList;
    Button search;
    String sortByVariable;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (Button) findViewById(R.id.buttonSearch);
        movieList = (ListView) findViewById(R.id.movieList);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButtonPop:
                        sortByVariable = "popularity.asc";
                        break;
                    case R.id.radioButtonRelease:
                        sortByVariable = "release_date.asc";
                        break;
                }
                String url = "https://api.themoviedb.org/3/discover/movie?api_key=365f12b479aefadba7d0ba147412b338&sort_by=" + sortByVariable + "&language=en-US&page=1";
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

                    MovieModel[] movieModels;
                    movieModels = new Gson().fromJson(jsonArray.toString(), MovieModel[].class);

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