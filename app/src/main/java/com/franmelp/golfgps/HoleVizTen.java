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

public class HoleVizTen extends AppCompatActivity{

    private TextView hazTwoText;
    private TextView hazThreeText;
    private TextView hazFourText;
    private TextView hazFiveText;
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
    private Location hazFiveLoc;
    private Location hazOneLoc;
    private Location frontGreenLoc;
    private Location backGreenLoc;
    private Location fromWhiteLoc;
    private Location fromYelLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole_viz_ten);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        fromWhiteText = (TextView) findViewById(R.id.tenFromWhite);
        fromWhiteText.setText("from white: ");
        fromWhiteLoc = new Location("");
        fromWhiteLoc.setLatitude(40.87679788);
        fromWhiteLoc.setLongitude(17.40069552);

        fromYelText = (TextView) findViewById(R.id.tenFromYellow);
        fromYelText.setText("from yellow: ");
        fromYelLoc = new Location("");
        fromYelLoc.setLatitude(40.87692380);
        fromYelLoc.setLongitude(17.40074754);

        hazOneText = (TextView) findViewById(R.id.tenHazOne);
        hazOneText.setText("1.");
        hazOneLoc = new Location("");
        hazOneLoc.setLatitude(40.87836700);
        hazOneLoc.setLongitude(17.39986244);

        hazTwoText = (TextView) findViewById(R.id.tenHazTwo);
        hazTwoText.setText("2.");
        hazTwoLoc = new Location("");
        hazTwoLoc.setLatitude(40.87849926);
        hazTwoLoc.setLongitude(17.39979567);

        hazThreeText = (TextView) findViewById(R.id.tenHazThree);
        hazThreeText.setText("3.");
        hazThreeLoc = new Location("");
        hazThreeLoc.setLatitude(40.87849355);
        hazThreeLoc.setLongitude(17.39918312);

        hazFourText = (TextView) findViewById(R.id.tenHazFour);
        hazFourText.setText("4.");
        hazFourLoc = new Location("");
        hazFourLoc.setLatitude(40.87860383);
        hazFourLoc.setLongitude(17.39914260);

        hazFiveText = (TextView) findViewById(R.id.tenHazFive);
        hazFiveText.setText("5.");
        hazFiveLoc = new Location("");
        hazFiveLoc.setLatitude(40.87955482);
        hazFiveLoc.setLongitude(17.39809086);

        frontGreenText = (TextView) findViewById(R.id.tenFront);
        frontGreenText.setText("front:");
        frontGreenLoc = new Location("");
        frontGreenLoc.setLatitude(40.88012928);
        frontGreenLoc.setLongitude(17.39775855);

        backGreenText = (TextView) findViewById(R.id.tenBack);
        backGreenText.setText("back:");
        backGreenLoc = new Location("");
        backGreenLoc.setLatitude(40.88036958);
        backGreenLoc.setLongitude(17.39759444);



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
                //hazFive
                String distHazFive = "5. " + calcDistance(hazFiveLoc);
                hazFiveText.setText(distHazFive);
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
