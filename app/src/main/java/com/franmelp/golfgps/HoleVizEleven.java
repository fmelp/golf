package com.franmelp.golfgps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HoleVizEleven extends AppCompatActivity{

    private TextView hazOneText;
    private TextView fromWhiteText;
    private TextView fromYelText;
    private TextView frontGreenText;
    private TextView backGreenText;


    private LocationManager locationManager;
    private Location currentLocation;
    private LocationListener locationListener;

    private Location hazOneLoc;
    private Location frontGreenLoc;
    private Location backGreenLoc;
    private Location fromWhiteLoc;
    private Location fromYelLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole_viz_eleven);
//        header = (ImageView) findViewById(R.id.headerTwo);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        fromWhiteText = (TextView) findViewById(R.id.elevenFromWhite);
        fromWhiteText.setText("from white: ");
        fromWhiteLoc = new Location("");
        fromWhiteLoc.setLatitude(40.88077401);
        fromWhiteLoc.setLongitude(17.39779382);

        fromYelText = (TextView) findViewById(R.id.elevenFromYellow);
        fromYelText.setText("from yellow: ");
        fromYelLoc = new Location("");
        fromYelLoc.setLatitude(40.88070051);
        fromYelLoc.setLongitude(17.39787590);

        hazOneText = (TextView) findViewById(R.id.elevenHazOne);
        hazOneText.setText("1.");
        hazOneLoc = new Location("");
        hazOneLoc.setLatitude(40.88016584);
        hazOneLoc.setLongitude(17.39874605);


        frontGreenText = (TextView) findViewById(R.id.elevenFront);
        frontGreenText.setText("front:");
        frontGreenLoc = new Location("");
        frontGreenLoc.setLatitude(40.88003123);
        frontGreenLoc.setLongitude(17.39893403);

        backGreenText = (TextView) findViewById(R.id.elevenBack);
        backGreenText.setText("back:");
        backGreenLoc = new Location("");
        backGreenLoc.setLatitude(40.87978167);
        backGreenLoc.setLongitude(17.39914492);





        locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                currentLocation = location;
                //hazeleven
                String distHazeleven = "1. " + calcDistance(hazOneLoc);
                hazOneText.setText(distHazeleven);
                //frontGreen
                String distFront = "front: " + calcDistance(frontGreenLoc);
                frontGreenText.setText(distFront);
                //backGreen
                String distBack = "back: " + calcDistance(backGreenLoc);
                backGreenText.setText(distBack);
                //from white tee
                String distWhite = "from white: " + calcDistance(fromWhiteLoc);
                fromWhiteText.setText(distWhite);
                //from yellow tee
                String distYel = "from yellow: " + calcDistance(fromYelLoc);
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
