package com.example.streamer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.streamer.adapter.adapter_main_category;
import com.example.streamer.custom.main_category;
import com.example.streamer.custom.question;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    ArrayList<question> customQuestion = new ArrayList<>();
    ArrayList<main_category> customMain = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.main);
        mQueue = Volley.newRequestQueue(MainActivity.this);

        mainParse();

    }

    public void mainParse() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://o2academia.org/demo_crud/JsonMaincategory.php", null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length();i++){
                        JSONObject obj = response.getJSONObject(i);


                        customMain.add(new main_category(
                                obj.getString("id"), obj.getString("main_category") ));

                    }

                    adapter_main_category adapter = new adapter_main_category(MainActivity.this,customMain);
                    //recyclerView.setLayoutManager(new LinearLayoutManager(mainmenu.this));

                    int numberOfColumns = 1;
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, numberOfColumns));
                    recyclerView.setAdapter(adapter);

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
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

    }
}