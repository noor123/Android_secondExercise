package com.example.jeansmits.jsonexercise.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeansmits.jsonexercise.R;
import com.example.jeansmits.jsonexercise.models.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.GET;
import retrofit.http.Path;
import timber.log.Timber;

public class ListOfMoviesActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String BASE_URL = "http://172.30.68.16:3000";
    private static final String TAG = "MyActivity";
    private List<Movie> movieList;
    private List<Movie> filteredMovieList;
    RecyclerView recList;
    LinearLayoutManager llm;
    Button filterMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_movies);

        filterMovies = (Button) findViewById(R.id.search_for_id);
        filterMovies.setOnClickListener(this);

        movieList = new ArrayList<>();
        recList = (RecyclerView) findViewById(R.id.movieList);
        recList.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();
        MyApiEndPointInterface apiService =
                restAdapter.create(MyApiEndPointInterface.class);
        apiService.getAllMovies(new Callback<List<Movie>>() {
            @Override
            public void success(List<Movie> movies, retrofit.client.Response response) {
                setMovieList(movies);
                setMovieAdapter(movieList);
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.v(TAG, "There is a failure.");
            }
        });
    }

    private void setMovieAdapter(List<Movie> movies) {
        MovieAdapter ca = new MovieAdapter(movies, this);
        recList.setAdapter(ca);
        recList.setLayoutManager(llm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_except_all_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_something) {
            Intent intent = new Intent(this, SaveSomethingActivity.class);
            startActivity(intent);
        } else if (id == R.id.take_photos) {
            Intent intent = new Intent(this, TakePhotosActivity.class);
            startActivity(intent);
        } else if (id == R.id.database_exercise) {
            Intent intent = new Intent(this, SaveInDatabaseActivity.class);
            startActivity(intent);
        } else if (id == R.id.fragments_exercise) {
            Intent intent = new Intent(this, ExerciseOnFragmentsActivity.class);
            startActivity(intent);
        } else if (id == R.id.drawer_exercise) {
            Intent intent = new Intent(this, ExerciseOnDrawerActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        EditText searchedId = (EditText) findViewById(R.id.givenId);
        TextView faultMessage = (TextView) findViewById(R.id.fault_message);

        boolean b = true;
        try {
            int i = Integer.parseInt(searchedId.getText().toString());
            b = false;
            filteredMovieList = new ArrayList<Movie>();
            for (Movie movie: movieList) {
                if (i == movie.getId()) {
                    filteredMovieList.add(movie);
                }
            }
            if (filteredMovieList.size() == 0) {
                faultMessage.setText("There is no movie with this id");
            } else {
                faultMessage.setText("");
            }
            setMovieAdapter(filteredMovieList);
        } catch(Exception exception) {
            faultMessage.setText("Please fill in a number");
        }

    }

    public interface MyApiEndPointInterface {
        // Request method and URL specified in the annotation
        // Callback for the parsed response is the last parameter

        @GET("/movieArray/{id}")
        void getMovie(@Path("id") Integer id, Callback<Movie> cb);

        @GET("/movieArray")
        void getAllMovies(Callback<List<Movie>> movies);

    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
