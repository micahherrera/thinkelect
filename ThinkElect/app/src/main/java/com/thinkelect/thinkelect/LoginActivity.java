package com.thinkelect.thinkelect;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_item_card);

        CircleDisplay cd = (CircleDisplay) findViewById(R.id.circleDisplay);
        cd.setAnimDuration(3000);
        cd.setValueWidthPercent(30f);
        cd.setTextSize(16f);
        cd.setColor(Color.YELLOW);
        cd.setDrawText(true);
        cd.setDrawInnerCircle(true);
        cd.setFormatDigits(0);
        cd.setTouchEnabled(false);
        cd.setUnit("%");
        cd.setStepSize(0.5f);
        // cd.setCustomText(...); // sets a custom array of text
        cd.showValue(25f, 100f, true);



    }
}
