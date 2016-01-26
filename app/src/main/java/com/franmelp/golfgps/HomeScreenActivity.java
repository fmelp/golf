package com.franmelp.golfgps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeScreenActivity extends AppCompatActivity {

    private static Button buttonOne;
    private static Button buttonTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHoleOne = new Intent(HomeScreenActivity.this, HoleVizOne.class);
                startActivity(goToHoleOne);

            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHoleTwo = new Intent(HomeScreenActivity.this, HoleVizTwo.class);
                startActivity(goToHoleTwo);

            }
        });

    }
}
