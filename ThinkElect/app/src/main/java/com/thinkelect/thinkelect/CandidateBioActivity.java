package com.thinkelect.thinkelect;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class CandidateBioActivity extends AppCompatActivity {

    ImageView img;
    ListView listView;
    List<String> contributors;
    OkHttpClient client;
    String response2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_bio);
        listView = (ListView)findViewById(R.id.contrList);
        setTitle("Steve Knight");

        img = (ImageView)findViewById(R.id.imagebio);
        Picasso.with(this)
                .load("http://image.dailynews.com/storyimage/LA/20140508/LOCAL1/140509544/AR/0/AR-140509544.jpg&maxh=400&maxw=667")
                .into(img);

        contributors = new ArrayList<>();
        client = new OkHttpClient();
        new DownloadUrlTask().execute();
    }

    private class DownloadUrlTask extends AsyncTask<Void, Void, Void>

    {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                response2 = GetGoogleStuff.run("http://www.opensecrets.org/api/?method=" +
                        "candContrib&cid=N00035820&output=json&apikey=" +
                        "25f2a79021ce9c48044f2f7de8dfa68f");
            } catch (IOException e) {
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                JSONObject main = new JSONObject(response2);
                JSONObject response = main.getJSONObject("response");
                JSONObject contributors2 = response.getJSONObject("contributors");
                JSONArray contributor = contributors2.getJSONArray("contributor");
                for(int i = 0; i < contributor.length();i++) {
                    JSONObject contri = contributor.getJSONObject(i);
                    JSONObject attr = contri.getJSONObject("@attributes");
                    String name = attr.getString("org_name");
                    String cont = attr.getString("total");
                    contributors.add(name + " - $" + cont);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }




            listView.setAdapter(new ArrayAdapter(CandidateBioActivity.this, android.R.layout.simple_list_item_1, contributors));


        }
    }
}
