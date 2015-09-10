package com.example.jeansmits.jsonexercise.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.jeansmits.jsonexercise.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListOfPicturesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final String TAG = "MyActivity";
    private Spinner spinnerWithPictures;
    File[] listOfFiles;
    List<String> listOfNamesImages;
    ImageView selectedImage;
    Button deleteImage;
    Button goBackToTakePhotos;
    AlertDialog.Builder dlgAlert;
    int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_pictures);

        dlgAlert  = new AlertDialog.Builder(this);
        selectedImage = (ImageView) findViewById(R.id.selected_image);
        deleteImage = (Button) findViewById(R.id.delete_picture);
        deleteImage.setOnClickListener(this);
        goBackToTakePhotos = (Button) findViewById(R.id.go_back_to_take_photos);
        goBackToTakePhotos.setOnClickListener(this);
        spinnerWithPictures = (Spinner) findViewById(R.id.spinner_for_pictures);
        spinnerWithPictures.setOnItemSelectedListener(this);

        File fileDirectory = makeAlbumStorageDir("photos");
        listOfFiles = fileDirectory.listFiles();

        if (listOfFiles.length == 0) {
            spinnerWithPictures.setVisibility(View.INVISIBLE);
            selectedImage.setVisibility(View.INVISIBLE);
            deleteImage.setVisibility(View.INVISIBLE);
            dlgAlert.setMessage("There are no images in the list");
            dlgAlert.show();
        } else {
            addImagesToLists();
            addItemsOnSpinnerWithPictures();
        }
//        addListenerOnButton();
//        addListenerOnSpinnerItemSelection();
    }

    public void addImagesToLists() {
        listOfNamesImages = new ArrayList<>();
        for (File f : listOfFiles) {
            listOfNamesImages.add(f.getName());
            ImageView x = new ImageView(this);
            Picasso.with(this).load("file://" + f.getAbsolutePath()).into(x);
        }
    }

    public void addItemsOnSpinnerWithPictures() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfNamesImages);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWithPictures.setAdapter(dataAdapter);
    }

    private File makeAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
        File f = listOfFiles[position];
        Picasso.with(this).load("file://" + f.getAbsolutePath()).into(selectedImage);
        selectedImage.setVisibility(View.VISIBLE);
        deleteImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_except_take_photos, menu);
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
        } else if (id == R.id.database_exercise) {
            Intent intent = new Intent(this, SaveInDatabaseActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        if (v == deleteImage) {
            File f = listOfFiles[position];
            f.delete();
            Intent intent = new Intent(this, ListOfPicturesActivity.class);
            startActivity(intent);
        } else if (v == goBackToTakePhotos) {
            Intent intent = new Intent(this, TakePhotosActivity.class);
            startActivity(intent);
        }

    }
}