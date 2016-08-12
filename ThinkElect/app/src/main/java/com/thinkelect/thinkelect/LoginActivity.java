package com.thinkelect.thinkelect;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements LoginStartFragment.StartScreen,
        LoginCredsFragment.LoginWithCreds, LoginAddressFragment.SetAddress {
    LoginStartFragment startFragment;
    LoginCredsFragment credsFragment;
    LoginAddressFragment addressFragment;
    FragmentManager fm;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String username;
    String address;
    String district;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fm = getSupportFragmentManager();
        startFragment = new LoginStartFragment();
        fm.beginTransaction().replace(R.id.login_holder, startFragment).commit();

//        CircleDisplay cd = (CircleDisplay) findViewById(R.id.circleDisplay);
//        cd.setAnimDuration(3000);
//        cd.setValueWidthPercent(30f);
//        cd.setTextSize(16f);
//        cd.setColor(Color.YELLOW);
//        cd.setDrawText(true);
//        cd.setDrawInnerCircle(true);
//        cd.setFormatDigits(0);
//        cd.setTouchEnabled(false);
//        cd.setUnit("%");
//        cd.setStepSize(0.5f);
//        // cd.setCustomText(...); // sets a custom array of text
//        cd.showValue(25f, 100f, true);

    }


    @Override
    public void loginClicked() {
        credsFragment = new LoginCredsFragment();
        fm.beginTransaction().replace(R.id.login_holder, credsFragment).commit();
    }

    @Override
    public void guestClicked() {

    }

    @Override
    public void loginWithCreds(String username, String password) {
        this.username = username;

        addressFragment = new LoginAddressFragment();
        fm.beginTransaction().replace(R.id.login_holder, addressFragment).commit();
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
        new DownloadUrlTask().execute();
    }

    private class DownloadUrlTask extends AsyncTask<Void, Void, Void>

    {

        @Override
        protected Void doInBackground(Void... voids) {
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

            DatabaseReference userRef = database.getReference("users").child(username);
            UserProfile profile = new UserProfile(username, district, state, address);
            userRef.push().setValue(profile);

            Intent intent = new Intent(LoginActivity.this, MyBallotActivity.class);
            intent.putExtra("address", address);
            startActivity(intent);



        }
    }
}
