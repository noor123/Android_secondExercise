package com.example.jeansmits.jsonexercise.activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.example.jeansmits.jsonexercise.R;
import android.widget.Toast;

import java.io.File;

public class TakePhotosActivity extends AppCompatActivity implements View.OnClickListener {
    File fileDirectory;
    Button takePhoto;
    EditText namePhoto;
    Button savePhoto;
    File fileWithPhoto;
    Button deletePhoto;
    Button gotoListPhotos;
                    // Op andere pagina drop-down box met images en dan één image eronder.
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_photos);

        takePhoto = (Button) findViewById(R.id.take_photo);
        takePhoto.setOnClickListener(this);
        deletePhoto = (Button) findViewById(R.id.delete_photo);
        deletePhoto.setOnClickListener(this);
        gotoListPhotos = (Button) findViewById(R.id.go_to_list_photos);
        gotoListPhotos.setOnClickListener(this);
        savePhoto = (Button) findViewById(R.id.save_image);
        savePhoto.setOnClickListener(this);
        namePhoto = (EditText) findViewById(R.id.name_image);

        boolean ExternalStorageAccessible = isExternalStorageWritable();
        if (ExternalStorageAccessible) {
            fileDirectory = makeAlbumStorageDir("photos");
            fileWithPhoto = new File(fileDirectory, "photosTakenByUser.jpg");
        }
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == takePhoto) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileWithPhoto));
            startActivityForResult(i, 0);
            namePhoto.setText("");
        } else if (v == deletePhoto) {
            fileWithPhoto.delete();
            ImageView showPhoto = (ImageView) findViewById(R.id.photo);
            showPhoto.setVisibility(View.INVISIBLE);
            deletePhoto.setVisibility(View.INVISIBLE);
        } else if (v == gotoListPhotos) {
            Intent intent = new Intent(this, ListOfPicturesActivity.class);
            startActivity(intent);
        } else if (v == savePhoto) {
            File f = new File(fileDirectory, namePhoto.getText().toString() + ".jpg");
            fileWithPhoto.renameTo(f);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView showPhoto = (ImageView) findViewById(R.id.photo);
        Picasso.with(this).load("file://" + fileWithPhoto.getAbsolutePath()).into(showPhoto);
        deletePhoto.setVisibility(View.VISIBLE);
        gotoListPhotos.setVisibility(View.VISIBLE);
        savePhoto.setVisibility(View.VISIBLE);
        namePhoto.setVisibility(View.VISIBLE);
        takePhoto.setText("Take another picture");
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
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

    private void toastMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
