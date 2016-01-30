package com.franmelp.golfgps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HoleVizFour extends AppCompatActivity{

    private TextView hazOneText;
    private TextView hazTwoText;
    private TextView fromWhiteText;
    private TextView fromYelText;
    private TextView frontGreenText;
    private TextView backGreenText;


    private LocationManager locationManager;
    private Location currentLocation;
    private LocationListener locationListener;

    private Location hazOneLoc;
    private Location hazTwoLoc;
    private Location frontGreenLoc;
    private Location backGreenLoc;
    private Location fromWhiteLoc;
    private Location fromYelLoc;

    private Button prevButton;
    private Button nextButton;
    private Button mainMenuButton;

//    private ImageView holePic;

    private LinearLayout textHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hole_viz_four);
//        header = (ImageView) findViewById(R.id.headerTwo);
//        holePic = (ImageView) findViewById(R.id.fourHolePic);
//        loadBitmap(holePic, this.getApplicationContext(), R.drawable.hole4_rimpicc);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        fromWhiteText = (TextView) findViewById(R.id.fourFromWhite);
        fromWhiteText.setText("white: ");
        fromWhiteLoc = new Location("");
        fromWhiteLoc.setLatitude(40.87550439);
        fromWhiteLoc.setLongitude(17.40364092);

        fromYelText = (TextView) findViewById(R.id.fourFromYellow);
        fromYelText.setText("yellow: ");
        fromYelLoc = new Location("");
        fromYelLoc.setLatitude(40.87532344);
        fromYelLoc.setLongitude(17.40361930);

        hazOneText = (TextView) findViewById(R.id.fourHazOne);
        hazOneText.setText("1.");
        hazOneLoc = new Location("");
        hazOneLoc.setLatitude(40.87387500);
        hazOneLoc.setLongitude(17.40247732);

        hazTwoText = (TextView) findViewById(R.id.fourHazTwo);
        hazTwoText.setText("2.");
        hazTwoLoc = new Location("");
        hazTwoLoc.setLatitude(40.87365148);
        hazTwoLoc.setLongitude(17.40230240);


        frontGreenText = (TextView) findViewById(R.id.fourFront);
        frontGreenText.setText("front:");
        frontGreenLoc = new Location("");
        frontGreenLoc.setLatitude(40.87297538);
        frontGreenLoc.setLongitude(17.40144743);

        backGreenText = (TextView) findViewById(R.id.fourBack);
        backGreenText.setText("back:");
        backGreenLoc = new Location("");
        backGreenLoc.setLatitude(40.87276649);
        backGreenLoc.setLongitude(17.40126640);

        prevButton = (Button) findViewById(R.id.fourPrev);
        nextButton = (Button) findViewById(R.id.fourNext);
        mainMenuButton = (Button) findViewById(R.id.fourMainMenu);


        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextHole = new Intent(HoleVizFour.this, HoleVizThree.class);
                startActivity(goToNextHole);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextHole = new Intent(HoleVizFour.this, HoleVizFive.class);
                startActivity(goToNextHole);
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainMenu = new Intent(HoleVizFour.this, HomeScreenActivity.class);
                startActivity(goToMainMenu);
            }
        });


        textHolder = (LinearLayout) frontGreenText.getParent();

        float myTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                18F, this.getApplicationContext().getResources().getDisplayMetrics());

        int color = Integer.parseInt("32cd32", 16)+0xFF000000;

        for (int i = 0; i < textHolder.getChildCount(); i++){
            if (textHolder.getChildAt(i) instanceof TextView){
                TextView t = (TextView) textHolder.getChildAt(i);
                t.setTextSize(TypedValue.COMPLEX_UNIT_SP, myTextSize);
            }
//            Button b = (Button) textHolder.getChildAt(i);
//            b.setTextSize(TypedValue.COMPLEX_UNIT_SP, myTextSize+10);
//            b.setTextColor(color);
        }
        prevButton.setTextSize(myTextSize);
        nextButton.setTextSize(myTextSize);
        fromWhiteText.setTextColor(Color.WHITE);
        fromYelText.setTextColor(Color.YELLOW);


        locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                currentLocation = location;
                //hazOne
                String distHazOne = "1. " + calcDistance(hazOneLoc);
                hazOneText.setText(distHazOne);
                //hazTwo
                String distHazTwo = "2. " + calcDistance(hazTwoLoc);
                hazTwoText.setText(distHazTwo);
                //frontGreen
                String distFront = "front: " + calcDistance(frontGreenLoc);
                frontGreenText.setText(distFront);
                //backGreen
                String distBack = "back: " + calcDistance(backGreenLoc);
                backGreenText.setText(distBack);
                //white tee
                String distWhite = "white: " + calcDistance(fromWhiteLoc);
                fromWhiteText.setText(distWhite);
                //yellow tee
                String distYel = "yellow: " + calcDistance(fromYelLoc);
                fromYelText.setText(distYel);

            }
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // NA
            }
            public void onProviderEnabled(String provider) {
                // NA
            }
            public void onProviderDisabled(String provider) {
                // NA
            }
        };

    }

    @Override
    protected void onResume(){
        super.onResume();
        locationManager.getProvider(LocationManager.GPS_PROVIDER);
        try{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    500, 1, locationListener);

        }catch(SecurityException e){

        }

    }

    @Override
    protected void onPause(){
        super.onPause();

        try{
            locationManager.removeUpdates(locationListener);

        }catch(SecurityException e){

        }
    }

    private String calcDistance(Location location){
        int distanceMeters = java.lang.Math.round(currentLocation.distanceTo(location));
        return Integer.toString(distanceMeters);

    }
//
//    private void loadBitmap(ImageView imageView, Context context, int resId){
//        LoadHolePic loadPicTask = new LoadHolePic(imageView, context);
//        loadPicTask.execute(resId);
//    }

}
