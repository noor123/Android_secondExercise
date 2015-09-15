package com.example.jeansmits.jsonexercise.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeansmits.jsonexercise.R;
import com.example.jeansmits.jsonexercise.models.Person;
import com.example.jeansmits.jsonexercise.utils.MySQLiteHelper;

public class SaveInDatabaseActivity extends AppCompatActivity implements View.OnClickListener {
    MySQLiteHelper db;
    Button savePerson;
    Button searchOnId;
    AlertDialog.Builder dlgAlert;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_in_database);

        dlgAlert  = new AlertDialog.Builder(this);
        db = new MySQLiteHelper(this);
        savePerson = (Button) findViewById(R.id.save_in_database);
        savePerson.setOnClickListener(this);
        searchOnId = (Button) findViewById(R.id.do_a_search);
        searchOnId.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_except_database_exercise, menu);
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
        } else if (id == R.id.fragments_exercise) {
            Intent intent = new Intent(this, ExerciseOnFragmentsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        if (v == savePerson) {
            EditText givenFirstName = (EditText) findViewById(R.id.give_in_firstname);
            EditText givenLastName = (EditText) findViewById(R.id.give_in_lastname);
            EditText givenFavoriteNumberName = (EditText) findViewById(R.id.give_in_favorite_number);
            if (givenFirstName.getText().toString().equals("")) {
                dlgAlert.setMessage("Please give in a firstname");
                dlgAlert.show();
            } else if (givenLastName.getText().toString().equals("")) {
                dlgAlert.setMessage("Please give in a lastname");
                dlgAlert.show();
            } else {
                try {
                    int number = Integer.parseInt(givenFavoriteNumberName.getText().toString());
                    Person person = new Person(givenFirstName.getText().toString(), givenLastName.getText().toString(), number);
                    db.addPerson(person);
                    dlgAlert.setMessage("This person is saved");
                    dlgAlert.show();
                    givenFirstName.setText("");
                    givenLastName.setText("");
                    givenFavoriteNumberName.setText("");
                } catch (NumberFormatException exception) {
                    dlgAlert.setMessage("Please give in a correct number");
                    dlgAlert.show();
                }
            }

        } else if (v == searchOnId) {
            TextView showFirstName = (TextView) findViewById(R.id.show_firstname);
            TextView showLastName = (TextView) findViewById(R.id.show_lastname);
            TextView showFavoriteNumber = (TextView) findViewById(R.id.show_favorite_number);
            try {
                EditText idToSearchFor = (EditText) findViewById(R.id.id_to_search_for);
                Person person = db.getPerson(Integer.parseInt(idToSearchFor.getText().toString()));
                showFirstName.setText("Firstname: " + person.getFirstName());
                showLastName.setText("Lastname: " + person.getLastName());
                showFavoriteNumber.setText("Favorite number: " + String.valueOf(person.getFavoriteNumber()));
            } catch(RuntimeException exception) {
                dlgAlert.setMessage("There is no person with this id");
                dlgAlert.show();
                showFirstName.setText("");
                showLastName.setText("");
                showFavoriteNumber.setText("");
            }

        }

    }

}
