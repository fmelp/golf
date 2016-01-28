package com.franmelp.golfgps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HoleVizTwo extends AppCompatActivity{

    private ImageView header;
    private TextView hazOneText;
    private TextView frontGreenText;
    private TextView backGreenText;
    private LocationManager locationManager;
    private Location currentLocation;
    private LocationListener locationListener;
    private Location hazOneLoc;
    private Location frontGreenLoc;
    private Location backGreenLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole_viz_two);
//        header = (ImageView) findViewById(R.id.headerTwo);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        hazOneText = (TextView) findViewById(R.id.hazOne);
        hazOneText.setText("1.");
        hazOneLoc = new Location("");
        hazOneLoc.setLatitude(40.87222462);
        hazOneLoc.setLongitude(17.40200090);

        backGreenText = (TextView) findViewById(R.id.backTwo);
        backGreenText.setText("back:");
        backGreenLoc = new Location("");
        backGreenLoc.setLatitude(40.87323471);
        backGreenLoc.setLongitude(17.40517958);

        frontGreenText = (TextView) findViewById(R.id.frontTwo);
        frontGreenText.setText("front:");
        frontGreenLoc = new Location("");
        frontGreenLoc.setLatitude(40.87305311);
        frontGreenLoc.setLongitude(17.40486093);


        locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                currentLocation = location;
                //hazOne
                String distHazOne = "1. " + calcDistance(hazOneLoc);
                hazOneText.setText(distHazOne);
                //frontGreen
                String distFront = "front: " + calcDistance(frontGreenLoc);
                frontGreenText.setText(distFront);
                //backGreen
                String distBack = "back: " + calcDistance(backGreenLoc);
                backGreenText.setText(distBack);

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
        float distanceMeters = currentLocation.distanceTo(location);
        String dMetersString = Float.toString(distanceMeters);
        return dMetersString;

    }

}
