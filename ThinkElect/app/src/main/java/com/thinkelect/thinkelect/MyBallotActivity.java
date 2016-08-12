package com.thinkelect.thinkelect;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class MyBallotActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView addressView;
    OkHttpClient client;
    String response;
    ArrayList<String> bills;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    RecyclerView recyclercand;
    ArrayList<Candidate> candidates;
    RecyclerCandAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ballot);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ThinkElect");

        Intent intent = getIntent();
        String address = intent.getStringExtra("address");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        addressView = (TextView)findViewById(R.id.address_view);
        addressView.setText(address);
        recyclercand = (RecyclerView)findViewById(R.id.recyclerCand);
        candidates = new ArrayList<>();
        candidates.add(new Candidate("Hillary", "Clinton",
                "https://upload.wikimedia.org/wikipedia/commons/8/84/Hillary_Clinton_crop.jpg",
                "Democrat", "$374,585,440", "P"));
        candidates.add(new Candidate("Donald", "Trump",
                "http://media4.s-nbcnews.com/j/newscms/2016_04/1396246/donald-trump-mug_5fea106e0eb494469a75e60d8f2b18ea.nbcnews-fp-320-320.jpeg",
                "Republican", "$98,742,091", "P"));
        candidates.add(new Candidate("Jill", "Stein",
                "https://d229l5sflpl9cp.cloudfront.net/canphoto/35775_lg.jpg",
                "Green", "$859,155", "P"));
        candidates.add(new Candidate("Gary", "Johnson",
                "http://az616578.vo.msecnd.net/files/2016/04/24/6359711238064290441312406225_GJTwitter.png",
                "Libertarian", "$1,363,290", "P"));
        candidates.add(new Candidate("Timothy", "Kaine",
                "http://a2.files.biography.com/image/upload/c_fill,cs_srgb,dpr_1.0,g_face,h_300,q_80,w_300/MTQwMTk3NDIyNTI0NTQwMTM2.jpg",
                "Democrat", "$22,313,977", "VP"));
        candidates.add(new Candidate("Mike", "Pence",
                "http://www.in.gov/gov/images/Governor_Pence_Official_Headshot_high_Res.jpg",
                "Republican", "$354,395", "VP"));
        candidates.add(new Candidate("Steve", "Knight",
                "http://archive.senate.ca.gov/sites/archive.senate.ca.gov/files/rep/KNIGHT_2009.jpg",
                "Republican", "$1,038,018", "CA-25"));
        candidates.add(new Candidate("Bryan", "Caforio",
                "http://www.trbimg.com/img-5668e85f/turbine/la-pol-ca-bryan-caforio-steve-knight-20151210",
                "Democrat", "$582,281", "CA-25"));

        adapter = new RecyclerCandAdapter(candidates, this);
        recyclercand.setLayoutManager(new LinearLayoutManager(this));
        recyclercand.setAdapter(adapter);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bills = new ArrayList<>();

        client = new OkHttpClient();

//        DatabaseReference userRef = database.getReference("hillaryclinton");
//
//
//        ChildEventListener listener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String key = dataSnapshot.getKey();
//                // keys.add(key);
//
//                // This iterates through the different children of the given user and retrieves
//                // the values for the current Users likes
//
//                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
//                    String billnum = dataSnapshot.getValue(String.class);
//
//                    bills.add(billnum);
//
//
//                }
//
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//
//        };
//        userRef.addChildEventListener(listener);
//        for(String bill: bills) {
//            new DownloadUrlTask().execute(bill);
//        }



    }

//    private class DownloadUrlTask extends AsyncTask<String, Void, Void>
//
//    {
//
//        @Override
//        protected Void doInBackground(String... bill) {
//
//                try {
//                    response = run("https://www.govtrack.us/api/v2/vote/" + bill[0]);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            String jsonData = response.toString();
//            DatabaseReference userRef = database.getReference("steveknight");
//            try {
//                JSONObject wholeThing = new JSONObject(jsonData);
//                JSONArray objects = wholeThing.getJSONArray("objects");
//                for(int i = 0; i < objects.length(); i++) {
//                    JSONObject vote = objects.getJSONObject(i);
//                    JSONObject vote2 = vote.getJSONObject("vote");
//                    String id = vote2.getString("id");
//                    Log.d("TAG", "onCreate: " + id);
//                    votervotes.add(id);
//                    userRef.push().setValue(id);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }
//
//    String run(String url) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_ballot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(!item.isChecked()){
        item.setChecked(true);
        //item.setTitle(item.getTitle()+" *");
             }
        else if(item.isChecked()){
            item.setChecked(false);
           // item.setTitle(item.getTitle().toString().substring(0, item.getTitle().toString().length()-2));
        }


        if (id == R.id.nav_camera) {
            for(int i = 0; i < recyclercand.getChildCount(); i++)
            {
                final View child = recyclercand.getChildAt(i);
                child.findViewById(R.id.contributions).setVisibility(View.VISIBLE);

                // do something with the View. like setting the text to TextView
            }
            adapter.notifyDataSetChanged();
        } else if (id == R.id.nav_gallery) {

        }
//        else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        }
// else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
