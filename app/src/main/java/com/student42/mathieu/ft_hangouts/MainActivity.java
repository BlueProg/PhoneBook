package com.student42.mathieu.ft_hangouts;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int color_choice = 0;
    Toolbar toolbar;
    public Integer[] ArrayColor = {
        Color.GRAY,
        Color.BLUE,
        Color.RED
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(ArrayColor[color_choice]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(MainActivity.this, "Resume !",
                Toast.LENGTH_LONG).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(MainActivity.this, "Result !",
                Toast.LENGTH_LONG).show();
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String choice = data.getStringExtra("ColorChoice");
                toolbar.setBackgroundColor(ArrayColor[Integer.parseInt(choice)]);
            }
        }
    }

    /* onClick */

    public void startUserActivity(View view) {

        Intent intent = new Intent(this, CreateUser.class);
        intent.putExtra("ColorChoice", color_choice + "");

        /* Material design transition */
        startActivityForResult(intent, 1, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
