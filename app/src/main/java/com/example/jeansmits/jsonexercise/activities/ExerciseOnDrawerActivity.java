package com.example.jeansmits.jsonexercise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jeansmits.jsonexercise.R;

public class ExerciseOnDrawerActivity extends ClassWithMenu implements View.OnClickListener {
    Button letRotate;
    ImageView imageToRotate;
    private ListView myDrawerList;
    private ArrayAdapter<String> myAdapter;
    private String[] myItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_on_drawer);

        letRotate = (Button) findViewById(R.id.let_rotate);
        letRotate.setOnClickListener(this);
        imageToRotate = (ImageView) findViewById(R.id.image_that_should_rotate);

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
    public void onClick(View v) {
        Animation myRotateAnimation = AnimationUtils.loadAnimation(ExerciseOnDrawerActivity.this, R.anim.rotate);
        myRotateAnimation.setRepeatCount(5);

        imageToRotate.startAnimation(myRotateAnimation);
    }
}
