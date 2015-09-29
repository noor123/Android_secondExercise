package com.example.jeansmits.jsonexercise.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.jeansmits.jsonexercise.R;

public class ClassWithMenu extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.overview_movies) {
            Intent intent = new Intent(this, MainActivityListOfMoviesActivity.class);
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
        } else if (id == R.id.drawer_exercise) {
            Intent intent = new Intent(this, ExerciseOnDrawerActivity.class);
            startActivity(intent);
        } else if (id == R.id.camera_exercise) {
            Intent intent = new Intent(this, ExerciseOnCameraActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
