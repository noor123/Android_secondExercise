package com.example.jeansmits.jsonexercise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jeansmits.jsonexercise.R;
import com.example.jeansmits.jsonexercise.models.Movie;

public class DetailPageMoviesActivity extends AppCompatActivity {
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpage_movies);

        TextView title = (TextView) findViewById(R.id.detail_title);
        TextView length = (TextView) findViewById(R.id.detail_length);
        TextView rating = (TextView) findViewById(R.id.detail_rating);
        TextView description = (TextView) findViewById(R.id.detail_description);

        String g_id = getIntent().getStringExtra("movie_id");
        String g_title = getIntent().getStringExtra("movie_title");
        int g_length = getIntent().getIntExtra("movie_length", 1);
        double g_rating = getIntent().getDoubleExtra("movie_rating", 0.00);
        String g_description = getIntent().getStringExtra("movie_description");

        title.setText(g_title);
        length.setText("Length: " + Integer.toString(g_length) + " minutes");
        rating.setText("Rating: " + Double.toString(g_rating) + "/10");
        description.setText("Description: " + g_description);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.overview_movies) {
            Intent intent = new Intent(this, ListOfMoviesActivity.class);
            startActivity(intent);
        } else if (id == R.id.save_something) {
            Intent intent = new Intent(this, SaveSomethingActivity.class);
            startActivity(intent);
        } else if (id == R.id.take_photos) {
            Intent intent = new Intent(this, TakePhotosActivity.class);
            startActivity(intent);
        } else if (id == R.id.database_exercise) {
            Intent intent = new Intent(this, SaveInDatabaseActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
