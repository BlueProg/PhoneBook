package com.student42.mathieu.ft_hangouts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUser extends AppCompatActivity {

    private int color_choice = 0;
    private Integer[] ArrayColor = {
            Color.GRAY,
            Color.BLUE,
            Color.RED
    };
    private DbHelper    mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        color_choice = Integer.parseInt(getIntent().getStringExtra("ColorChoice"));
        toolbar.setBackgroundColor(ArrayColor[color_choice]);

        mydb = new DbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /* Header Color */
    public void changeColorHeader(MenuItem item) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        color_choice++;
        if (color_choice > 2)
            color_choice = 0;
        toolbar.setBackgroundColor(ArrayColor[color_choice]);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("ColorChoice", color_choice + "");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    protected void onStop () {
        super.onStop();

    }

    // onclick

    public void cancelCreateUser(View  view) {
        Intent intent = new Intent();
        intent.putExtra("ColorChoice", color_choice + "");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void CreateNewUser(View  view) {
        mydb.insertContact(
                ((EditText)findViewById(R.id.create_user_name)).getText().toString(),
                ((EditText)findViewById(R.id.create_user_fname)).getText().toString(),
                ((EditText)findViewById(R.id.create_user_phone)).getText().toString(),
                ((EditText)findViewById(R.id.create_user_email)).getText().toString(),
                ((EditText)findViewById(R.id.create_user_home)).getText().toString()
        );
        Toast.makeText(CreateUser.this, "New Contact add:",
                Toast.LENGTH_LONG).show();
        finish();
    }
}
