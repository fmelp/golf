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

public class HoleVizSeventeen extends AppCompatActivity{

    private TextView fromWhiteText;
    private TextView fromYelText;
    private TextView frontGreenText;
    private TextView backGreenText;


    private LocationManager locationManager;
    private Location currentLocation;
    private LocationListener locationListener;

    private Location frontGreenLoc;
    private Location backGreenLoc;
    private Location fromWhiteLoc;
    private Location fromYelLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole_viz_seventeen);
//        header = (ImageView) findViewById(R.id.headerTwo);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        fromWhiteText = (TextView) findViewById(R.id.seventeenFromWhite);
        fromWhiteText.setText("from white: ");
        fromWhiteLoc = new Location("");
        fromWhiteLoc.setLatitude(40.88184749);
        fromWhiteLoc.setLongitude(17.39817959);

        fromYelText = (TextView) findViewById(R.id.seventeenFromYellow);
        fromYelText.setText("from yellow: ");
        fromYelLoc = new Location("");
        fromYelLoc.setLatitude(40.88177498);
        fromYelLoc.setLongitude(17.39812772);


        frontGreenText = (TextView) findViewById(R.id.seventeenFront);
        frontGreenText.setText("front:");
        frontGreenLoc = new Location("");
        frontGreenLoc.setLatitude(40.88070414);
        frontGreenLoc.setLongitude(17.39679632);

        backGreenText = (TextView) findViewById(R.id.seventeenBack);
        backGreenText.setText("back:");
        backGreenLoc = new Location("");
        backGreenLoc.setLatitude(40.88062497);
        backGreenLoc.setLongitude(17.39635413);





        locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                currentLocation = location;
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
