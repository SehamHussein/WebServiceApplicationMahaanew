package com.example.maha.webserviceapplicationmaha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.maha.webserviceapplicationmaha.DataModel.MovieModel;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    TextView tvItemTitle;
    TextView releaseDate;
    TextView overview;
    RatingBar ratingBar;
    ImageView imageViewItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        MovieModel movieModel = (MovieModel) getIntent().getSerializableExtra("MovieModel");

        ///////////////////////////////////////////////////////////////
        tvItemTitle = (TextView) findViewById(R.id.tvItemTitle);

        releaseDate = (TextView) findViewById(R.id.releaseDate);

        overview = (TextView) findViewById(R.id.overview);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        imageViewItem = (ImageView) findViewById(R.id.imageViewItem);
        /////////////////////////////////////////////////////////////////////

        tvItemTitle.setText(movieModel.getTitle());
        releaseDate.setText(movieModel.getRelease_date());
        overview.setText(movieModel.getOverview());

       float rate =(float)movieModel.getVote_average();
       rate= rate / 2;
        ratingBar.setRating(rate);


        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path()).into( imageViewItem);

       // String url= "http://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path();








    }
}
