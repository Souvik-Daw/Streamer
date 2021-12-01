package com.example.streamer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.streamer.adapter.adapter_category;
import com.example.streamer.adapter.adapter_main_category;
import com.example.streamer.custom.category;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Category extends AppCompatActivity {

    private RequestQueue mQueue;
    ArrayList<category> customCategory = new ArrayList<>();
    RecyclerView recyclerView;
    TextView m;
    String mainCategory_id = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.cat);
        mQueue = Volley.newRequestQueue(Category.this);

        Intent intent = getIntent();
        mainCategory_id = intent.getStringExtra(adapter_main_category.maincattoCat);

        categoryParse();

    }


    public void categoryParse() {

        ArrayList<category> finalCategory = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://o2academia.org/demo_crud/JsonCategory.php", null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length();i++){
                        JSONObject obj1 = response.getJSONObject(i);


                        customCategory.add(new category(
                                obj1.getString("id"), obj1.getString("main_category"),
                                obj1.getString("category_name")
                        ));
                    }

                    for(category q : customCategory)
                    {
                        //customQuestion.add(q);

                        if(mainCategory_id != null && (q.getMain_category().compareTo(mainCategory_id)) == 0) {
                           // m.append(q.getId()+  ", " + q.getCategory_name() + "," +q.getMain_category() +  ", " +" \n");

                            finalCategory.add(q);
                        }

                        else {
                            //m.setText("Coming Soon");
                            //Toast.makeText(Category.this,"coming soon",Toast.LENGTH_LONG).show();
                        }
                    }

                    if(finalCategory.size() == 0)
                    {
                        Toast.makeText(Category.this,"Coming Soon",Toast.LENGTH_LONG).show();
                    }
                    else {
                        adapter_category adapter = new adapter_category(Category.this, finalCategory);
                        //recyclerView.setLayoutManager(new LinearLayoutManager(mainmenu.this));

                        int numberOfColumns = 1;
                        recyclerView.setLayoutManager(new GridLayoutManager(Category.this, numberOfColumns));
                        recyclerView.setAdapter(adapter);
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