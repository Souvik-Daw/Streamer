package com.example.streamer.VideoStreaming;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.streamer.MainActivity;
import com.example.streamer.Product;
import com.example.streamer.R;
import com.example.streamer.SessionManager;
import com.example.streamer.aboutUs;
import com.example.streamer.login;
import com.example.streamer.profile;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class homepage extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    RecyclerView recyclerView;
    Map<String, Object> map = new HashMap<>();
    ArrayList<String> list = new ArrayList<>();
    String a[ ] ;

    RecyclerView recyclerView2;
    Map<String, Object> map2 = new HashMap<>();
    ArrayList<String> list2 = new ArrayList<>();
    String a2[ ] ;
    String id = "";

    Button feedbckbtn , quiz, videos ;
    FloatingActionButton fabprofile;
    TextView greetings, profile_fetching_name ;
    TextView tvToolbar;
    private AppBarLayout appBarLayout;


    ProgressBar progressBar;
    ShimmerFrameLayout shimmerFrameLayout;
    ShimmerFrameLayout shimmerFrameLayout_edu;

    public static final String homeToProfile = "homeToProfile";
    private RequestQueue mQueue;

    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.rv_educator);
        recyclerView2 = (RecyclerView) findViewById(R.id.rv_topic);
        feedbckbtn = findViewById(R.id.feedbckbtn);
        fabprofile = findViewById(R.id.fabprofile);
        greetings = findViewById(R.id.greetings);
        videos = findViewById(R.id.videos);
        quiz = findViewById(R.id.quiz);
        profile_fetching_name = findViewById(R.id.profile_fetching_name);
        mQueue = Volley.newRequestQueue(homepage.this);
        tvToolbar = findViewById(R.id.txt_toolbar);
        appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.bringToFront();
        tvToolbar.setVisibility(View.VISIBLE);



        //Assign variable
        //nestedScrollView = findViewById(R.id.scrollView);
        progressBar = findViewById(R.id.progress_bar);
        shimmerFrameLayout = findViewById(R.id.shimmer_layout);
        shimmerFrameLayout_edu = findViewById(R.id.shimmer_layout_edu);

        //profile fetching
        Intent intent = getIntent();
        id = intent.getStringExtra(login.UserId);


        //Start shimmer effect
        shimmerFrameLayout_edu.startShimmer();
        //Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        //Initialize session manager
        sessionManager = new SessionManager(getApplicationContext());



        loadProducts();

        Date dt = new Date();
        int hours = dt.getHours();

        if(hours>=20 && hours<24)
        {
            greetings.setText( "Have a Great Night !" );
        }

        else if(hours>=17 && hours<20)
        {
            greetings.setText( "Good Evening !" );
        }

        else if(hours>=14 && hours<17)
        {
            greetings.setText( "Good Afternoon !" );
        }

        else if( hours>=12 && hours<14 )
        {
            greetings.setText( "Good Noon !" );
        }

        else if( hours>=0 && hours<12 )
        {
            greetings.setText( "Good Morning !" );
        }

        else
        {
            greetings.setText( "Welcome, Have a Great Day" );
        }



        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference db =  firestore.collection("Educator").document("educator_img");
        db.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();
                    if (document.exists())
                    {
                        map = document.getData();
                        list = (ArrayList) map.get("img");
                    }
                    a = new String[list.size()];
                    int i = 0;
                    for(String p:list) {
                        a[ i ] = p;
                        i++;
                    }

                    //Hide progress bar
                    progressBar.setVisibility(View.GONE);
                    //Stop shimmer effect
                    shimmerFrameLayout_edu.stopShimmer();
                    //Hide shimmer layout
                    shimmerFrameLayout_edu.setVisibility(View.GONE);


                    homeAdapter adapter = new homeAdapter(homepage.this,a);
                    recyclerView.setLayoutManager(new LinearLayoutManager(homepage.this,LinearLayoutManager.HORIZONTAL, false));

                    //int numberOfColumns = 1;
                    //recyclerView.setLayoutManager(new GridLayoutManager(homepage.this, numberOfColumns));

                    recyclerView.setAdapter(adapter);
                }
            }
        });


        //topic fetching

        //Start shimmer effect
        shimmerFrameLayout.startShimmer();
        //Show progress bar
        progressBar.setVisibility(View.VISIBLE);


        FirebaseFirestore firestore2 = FirebaseFirestore.getInstance();
        DocumentReference db2 =  firestore2.collection("topic").document("topic_title");
        db2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();
                    if (document.exists())
                    {
                        map2 = document.getData();
                        list2 = (ArrayList) map2.get("title");
                    }
                    a2 = new String[list2.size()];
                    int i = 0;
                    for(String p:list2) {
                        a2[ i ] = p;
                        i++;
                    }

                    //Hide progress bar
                    progressBar.setVisibility(View.GONE);
                    //Stop shimmer effect
                    shimmerFrameLayout.stopShimmer();
                    //Hide shimmer layout
                    shimmerFrameLayout.setVisibility(View.GONE);


                    topicAdapter adapter = new topicAdapter(homepage.this,a2);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(homepage.this,LinearLayoutManager.HORIZONTAL, false));

                    //int numberOfColumns = 1;
                    //recyclerView.setLayoutManager(new GridLayoutManager(homepage.this, numberOfColumns));

                    recyclerView2.setAdapter(adapter);
                }
            }
        });

        feedbckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sr = name.getText().toString();
                Intent intent = new Intent(homepage.this, feedback.class);
                //intent.putExtra(logintocollection, sr);
                startActivity(intent);
                //startActivity(new Intent(login.this, mainmenu.class).putExtra("logintocollection",sr));
            }
        });

        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Snackbar.make(v, "Opening your Goal", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(homepage.this, course_available.class));
                    }
                },2000);*/

                Intent intent = new Intent(homepage.this, course_available.class);
               startActivity(intent);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(homepage.this, MainActivity.class);
                startActivity(intent);
            }
        });


        fabprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Opening your Profile", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent in=new Intent(getApplicationContext(), profile.class);
                        in.putExtra(homeToProfile,id);
                        startActivity(in);

                    }
                },2000);


                //Intent intent = new Intent(homepage.this, course_available.class);
               // startActivity(intent);
            }
        });



    }

    //homepage notification functionality

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_us) {

            startActivity(new Intent(homepage.this, aboutUs.class));
            //Toast.makeText(homepage.this, " Coming Soon ", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.logout)
        {
            //Set login false
            sessionManager.setLogin(false);
            //Set username empty
            sessionManager.setUsername("");

            startActivity(new Intent(homepage.this, login.class));
            Toast.makeText(homepage.this, " LOGOUT ", Toast.LENGTH_LONG).show();
            homepage.this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // double press of back button to exit from app.
    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(), "Press Back Again to Exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    public void loadProducts()
    {
        ArrayList<Product> customQuestion2 = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://www.o2academia.org/demo_crud/jsonstudentmaster.php", null, new Response.Listener<JSONArray>()
        {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length();i++){
                        JSONObject obj = response.getJSONObject(i);

                        customQuestion2.add(new Product(
                                obj.getString("id"),
                                obj.getString("studentname"),
                                obj.getString("mobileno"),
                                obj.getString("email"),
                                obj.getString("studentid") ));

                    }

                    if ( customQuestion2.size() == 0)
                    {
                        Toast.makeText(homepage.this,"Size is zero", Toast.LENGTH_LONG).show();
                    }


                    for(Product p : customQuestion2)
                    {
                        if ( id.compareTo(p.getStudid()) == 0 ) {

                            profile_fetching_name.append(p.getName());

                        }
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(jsonArrayRequest);

        jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 40000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 40000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

    }
}