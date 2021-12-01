package com.example.streamer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.streamer.VideoStreaming.homepage;
import com.example.streamer.custom.question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class profile extends AppCompatActivity
{

    ArrayList<Product> productList;
    TextView studentname,email,mobileno,studentid;

    String id="";
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        studentname=findViewById(R.id.studentname);
        email=findViewById(R.id.email);
        mobileno=findViewById(R.id.mobileno);
        studentid=findViewById(R.id.studentid);
        mQueue = Volley.newRequestQueue(profile.this);


        Intent intent = getIntent();
        id = intent.getStringExtra(homepage.homeToProfile);
        productList = new ArrayList<>();

        loadProducts();

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
                        Toast.makeText(profile.this,"Size is zero", Toast.LENGTH_LONG).show();
                    }


                    for(Product p : customQuestion2)
                    {
                       if ( id.compareTo(p.getStudid()) == 0 ) {

                           studentname.append(p.getName());
                           mobileno.append(p.getPhno());
                           email.append(p.getEmail());
                           studentid.append(p.getStudid());

                           Toast.makeText(profile.this,"Found", Toast.LENGTH_LONG).show();
                           break;

                       }
                        else {

                           Toast.makeText(profile.this,"Not Found", Toast.LENGTH_LONG).show();
                           continue;

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