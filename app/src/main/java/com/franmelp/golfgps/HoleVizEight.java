package com.franmelp.golfgps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HoleVizEight extends AppCompatActivity{

    private TextView hazTwoText;
    private TextView hazThreeText;
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
    private Location hazOneLoc;
    private Location frontGreenLoc;
    private Location backGreenLoc;
    private Location fromWhiteLoc;
    private Location fromYelLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole_viz_eight);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        fromWhiteText = (TextView) findViewById(R.id.eightFromWhite);
        fromWhiteText.setText("white: ");
        fromWhiteLoc = new Location("");
        fromWhiteLoc.setLatitude(40.88217817);
        fromWhiteLoc.setLongitude(17.39426532);

        fromYelText = (TextView) findViewById(R.id.eightFromYellow);
        fromYelText.setText("yellow: ");
        fromYelLoc = new Location("");
        fromYelLoc.setLatitude(40.88199761);
        fromYelLoc.setLongitude(17.39448513);

        hazOneText = (TextView) findViewById(R.id.eightHazOne);
        hazOneText.setText("1.");
        hazOneLoc = new Location("");
        hazOneLoc.setLatitude(40.88058282);
        hazOneLoc.setLongitude(17.39588147);

        hazTwoText = (TextView) findViewById(R.id.eightHazTwo);
        hazTwoText.setText("2.");
        hazTwoLoc = new Location("");
        hazTwoLoc.setLatitude(40.88008842);
        hazTwoLoc.setLongitude(17.39585960);

        hazThreeText = (TextView) findViewById(R.id.eightHazThree);
        hazThreeText.setText("3.");
        hazThreeLoc = new Location("");
        hazThreeLoc.setLatitude(40.88002796);
        hazThreeLoc.setLongitude(17.39556595);

        frontGreenText = (TextView) findViewById(R.id.eightFront);
        frontGreenText.setText("front:");
        frontGreenLoc = new Location("");
        frontGreenLoc.setLatitude(40.87990254);
        frontGreenLoc.setLongitude(17.39587117);

        backGreenText = (TextView) findViewById(R.id.eightBack);
        backGreenText.setText("back:");
        backGreenLoc = new Location("");
        backGreenLoc.setLatitude(40.87963038);
        backGreenLoc.setLongitude(17.39610285);



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
