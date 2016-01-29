package com.franmelp.golfgps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeScreenActivity extends AppCompatActivity {

    private static Button selectHoleButton;
    private static Button startOneButton;
    private static Button startTenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        selectHoleButton = (Button) findViewById(R.id.allHoleViewerButton);
        startTenButton = (Button) findViewById(R.id.startTenButton);
        startOneButton = (Button) findViewById(R.id.startOneButton);

        selectHoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllHoles = new Intent(HomeScreenActivity.this, AllHoleViewer.class);
                startActivity(goToAllHoles);

            }
        });

        startOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startOne = new Intent(HomeScreenActivity.this, HoleVizOne.class);
                startActivity(startOne);

            }
        });

        startTenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startTen = new Intent(HomeScreenActivity.this, HoleVizTen.class);
                startActivity(startTen);

            }
        });



    }
}
