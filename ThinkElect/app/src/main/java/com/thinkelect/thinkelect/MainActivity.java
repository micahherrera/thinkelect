package com.thinkelect.thinkelect;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    static final int REQUEST_LOCATION = 0;
    boolean isConnected = false;
    PlaceDetectionApi placesApi;
    PlaceAutocompleteFragment autocompleteFragment;
    double latitude;
    double longitude;
    String address;
    OkHttpClient client;
    String district;
    String state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkConnection();

        //TODO: put in button onclick for autofind location
        checkPermissions();
        if(isConnected){
            getLocation();
            new DownloadUrlTask().execute();
        }

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                address = place.getAddress().toString();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });

        client= new OkHttpClient();
        //check apis


    }

    public void checkConnection(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("SEARCH", "onCreate: You are connected");
           isConnected = true;

        } else {
            Log.d("SEARCH", "onCreate: You are not connected");
            isConnected = false;
        }

    }

    public void getLocation(){
        Intent getLocation = new Intent(MainActivity.this, LocationService.class);
        getLocation.putExtra("location", "location");
        startService(getLocation);
    }

    public void checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
        }


    }

    private class DownloadUrlTask extends AsyncTask<Void, Void, Void>

    {

        @Override
        protected Void doInBackground(Void... voids) {
            address = "804+grant+st+burleson+tx+76028";
            String response2 = null;
            try {
                response2 = GetGoogleStuff.run("https://www.googleapis.com/civicinfo/v2/" +
                        "representatives?address="+address+"&key=AIzaSyBabQoPD1C5-99sB3R7bPB_rBkn-4o7FHY");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("TEST", "doInBackground2: "+ response2.toString());
            Pattern p = Pattern.compile("ocd-division/country:us/state:../cd:..");
            Matcher m = p.matcher(response2.toString());
            while(m.find()) {
                state = m.group(0).substring(m.group(0).length()-8, m.group(0).length()-6);
                district = m.group(0).substring((m.group(0).length()-2), (m.group(0).length()));
                Log.d("TEST", "doInBackground: " + state + district);
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
           new MatchTask().execute();


        }
    }

    private class MatchTask extends AsyncTask<Void, Void, Void>

    {

        @Override
        protected Void doInBackground(Void... voids) {

            String response = null;
            try {
                response = GetGoogleStuff.run("https://api.open.fec.gov/v1/candidates/" +
                        "?district="+district+"&sort=name&election_year=2016&state="+state+"&per_page=20&page=1&" +
                        "api_key=NbBUNjn5rNon0kO6gsLS4QMWLOhOzhZ6mirMAJUj");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("TEST", "doInBackground: "+ response.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



        }
    }

}
