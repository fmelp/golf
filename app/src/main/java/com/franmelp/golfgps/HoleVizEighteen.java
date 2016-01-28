package com.franmelp.golfgps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class HoleVizEighteen extends AppCompatActivity{

    private TextView hazTwoText;
    private TextView hazThreeText;
    private TextView hazFourText;
    private TextView hazOneText;
    private TextView fromWhiteText;
    private TextView fromYelText;
    private TextView frontGreenText;
    private TextView backGreenText;


    private LocationManager locationManager;
    private Location currentLocation;
    private LocationListener locationListener;

    private Location hazTwoLoc;
    private Location hazThreeLoc;
    private Location hazFourLoc;
    private Location hazOneLoc;
    private Location frontGreenLoc;
    private Location backGreenLoc;
    private Location fromWhiteLoc;
    private Location fromYelLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole_viz_eighteen);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        fromWhiteText = (TextView) findViewById(R.id.eighteenFromWhite);
        fromWhiteText.setText("white: ");
        fromWhiteLoc = new Location("");
        fromWhiteLoc.setLatitude(40.88011250);
        fromWhiteLoc.setLongitude(17.39679799);

        fromYelText = (TextView) findViewById(R.id.eighteenFromYellow);
        fromYelText.setText("yellow: ");
        fromYelLoc = new Location("");
        fromYelLoc.setLatitude(40.87984833);
        fromYelLoc.setLongitude(17.39713961);

        hazOneText = (TextView) findViewById(R.id.eighteenHazOne);
        hazOneText.setText("1.");
        hazOneLoc = new Location("");
        hazOneLoc.setLatitude(40.87855302);
        hazOneLoc.setLongitude(17.39855453);

        hazTwoText = (TextView) findViewById(R.id.eighteenHazTwo);
        hazTwoText.setText("2.");
        hazTwoLoc = new Location("");
        hazTwoLoc.setLatitude(40.87851305);
        hazTwoLoc.setLongitude(17.39861823);

        hazThreeText = (TextView) findViewById(R.id.eighteenHazThree);
        hazThreeText.setText("3.");
        hazThreeLoc = new Location("");
        hazThreeLoc.setLatitude(40.87802393);
        hazThreeLoc.setLongitude(17.39868031);

        hazFourText = (TextView) findViewById(R.id.eighteenHazFour);
        hazFourText.setText("4.");
        hazFourLoc = new Location("");
        hazFourLoc.setLatitude(40.87758126);
        hazFourLoc.setLongitude(17.39935795);

        frontGreenText = (TextView) findViewById(R.id.eighteenFront);
        frontGreenText.setText("front:");
        frontGreenLoc = new Location("");
        frontGreenLoc.setLatitude(40.87692524);
        frontGreenLoc.setLongitude(17.39980453);

        backGreenText = (TextView) findViewById(R.id.eighteenBack);
        backGreenText.setText("back:");
        backGreenLoc = new Location("");
        backGreenLoc.setLatitude(40.87667075);
        backGreenLoc.setLongitude(17.39998781);



        locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                currentLocation = location;
                //hazOne
                String distHazOne = "1. " + calcDistance(hazOneLoc);
                hazOneText.setText(distHazOne);
                //hazTwo
                String distHazTwo = "2. " + calcDistance(hazTwoLoc);
                hazTwoText.setText(distHazTwo);
                //hazThree
                String distHazThree = "3. " + calcDistance(hazThreeLoc);
                hazThreeText.setText(distHazThree);
                //hazFour
                String distHazFour = "4. " + calcDistance(hazFourLoc);
                hazFourText.setText(distHazFour);
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

}