package com.example.jeansmits.jsonexercise.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jeansmits.jsonexercise.R;

public class SaveSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    Button saveValue;
    Button retrieveValue;
    Button clearValue;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_something);

        saveValue = (Button) findViewById(R.id.save_value);
        saveValue.setOnClickListener(this);
        retrieveValue = (Button) findViewById(R.id.retrieve_value);
        retrieveValue.setOnClickListener(this);
        clearValue = (Button) findViewById(R.id.clear_value_in_memory);
        clearValue.setOnClickListener(this);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
    }

    @Override
    public void onClick(View v) {
        EditText textUserGaveIn = (EditText) findViewById(R.id.text_to_save);
        if (v == saveValue) {
            editor.putString("valueOfUser", textUserGaveIn.getText().toString());
            editor.commit();
        } else if (v == retrieveValue) {
            textUserGaveIn.setText(pref.getString("valueOfUser", null));
        } else if (v == clearValue) {
            editor.remove("valueOfUser");
            editor.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_except_shared_preferences, menu);
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
