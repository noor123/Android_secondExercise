package com.example.jeansmits.jsonexercise.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeansmits.jsonexercise.R;
import com.example.jeansmits.jsonexercise.models.Movie;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.GET;
import retrofit.http.Path;
import timber.log.Timber;

public class MainActivityListOfMoviesActivity extends ClassWithMenu implements View.OnClickListener {
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

        createNotification();

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

        ParseObject testObject = new ParseObject("Person");
        testObject.put("first name", "hello");
        testObject.put("last name", "world");
        testObject.put("city", "Leuven");
        testObject.saveInBackground();

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

    private void toastMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void createNotification() {
        // Create a notification
        NotificationCompat.Builder myBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.uitroepteken)
                        .setContentTitle("This is a notification!")
                        .setContentText("Notification: you're using my app now!");

        // Sets an ID for the notification
        int myNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager myNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        myNotifyMgr.notify(myNotificationId, myBuilder.build());

        // Define actions
        Intent resultIntent = new Intent(this, MainActivityListOfMoviesActivity.class);
        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        myBuilder.setContentIntent(resultPendingIntent);

    }
}
