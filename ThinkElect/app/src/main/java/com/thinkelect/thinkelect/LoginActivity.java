package com.thinkelect.thinkelect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements LoginStartFragment.StartScreen,
        LoginCredsFragment.LoginWithCreds, LoginAddressFragment.SetAddress {
    LoginStartFragment startFragment;
    LoginCredsFragment credsFragment;
    LoginAddressFragment addressFragment;
    FragmentManager fm;

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
        addressFragment = new LoginAddressFragment();
        fm.beginTransaction().replace(R.id.login_holder, addressFragment).commit();
    }

    @Override
    public void setAddress() {
        Intent intent = new Intent(this, MyBallotActivity.class);
        startActivity(intent);

    }
}
