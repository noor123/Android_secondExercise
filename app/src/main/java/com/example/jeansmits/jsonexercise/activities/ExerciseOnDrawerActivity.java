package com.example.jeansmits.jsonexercise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jeansmits.jsonexercise.R;

public class ExerciseOnDrawerActivity extends AppCompatActivity {
    private ListView myDrawerList;
    private ArrayAdapter<String> myAdapter;
    private String[] myItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_on_drawer);

        /* Get the array in the map "res", document "colors.xml" */
        myItems = getResources().getStringArray(R.array.string_array);

        /* Getting reference to the DrawerLayout */
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        myDrawerList = (ListView) findViewById(R.id.drawer_list);

        /* Creating an ArrayAdapter to add items to mDrawerList */
        myAdapter = new ArrayAdapter(this, R.layout.counting_layout, myItems);

        /* Setting the adapter to mDrawerList */
        myDrawerList.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_except_drawer_exercise, menu);
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
        } else if (id == R.id.fragments_exercise) {
            Intent intent = new Intent(this, ExerciseOnFragmentsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
