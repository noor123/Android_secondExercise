package com.example.jeansmits.jsonexercise.activities;

import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.jeansmits.jsonexercise.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListOfPicturesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "MyActivity";
    private Spinner spinnerWithPictures;
    File[] listOfFiles;
    List<String> listOfNamesImages;
    ImageView selectedImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_pictures);

        selectedImage = (ImageView) findViewById(R.id.selected_image);
        addImagesToLists();
        addItemsOnSpinnerWithPictures();
//        addListenerOnButton();
//        addListenerOnSpinnerItemSelection();
    }

    public void addImagesToLists() {
        File fileDirectory = makeAlbumStorageDir("photos");
        listOfFiles = fileDirectory.listFiles();
        listOfNamesImages = new ArrayList<>();
        for (File f : listOfFiles) {
            listOfNamesImages.add(f.getName());
            ImageView x = new ImageView(this);
            Picasso.with(this).load("file://" + f.getAbsolutePath()).into(x);
        }
    }

    public void addItemsOnSpinnerWithPictures() {
        spinnerWithPictures = (Spinner) findViewById(R.id.spinner_for_pictures);
        spinnerWithPictures.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listOfNamesImages);
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
        File f = listOfFiles[position];
        Picasso.with(this).load("file://" + f.getAbsolutePath()).into(selectedImage);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
