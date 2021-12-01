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
import com.example.streamer.adapter.adapter_sub_category;
import com.example.streamer.custom.category;
import com.example.streamer.custom.sub_category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sub_Category extends AppCompatActivity {

    String category_id;
    private RequestQueue mQueue;
    ArrayList<sub_category> customSubCategory = new ArrayList<>();
    RecyclerView recyclerView;
    TextView m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        recyclerView = findViewById(R.id.sub_cat);
        mQueue = Volley.newRequestQueue(Sub_Category.this);

        Intent intent = getIntent();
        category_id = intent.getStringExtra(adapter_category.cattoSubcat);

        subCategoryParse();

    }

    public void subCategoryParse() {

        ArrayList<sub_category> finalsubCategory = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://o2academia.org/demo_crud/Jsonsubcat.php", null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length();i++){
                        JSONObject obj1 = response.getJSONObject(i);

                        customSubCategory.add(new sub_category(
                                obj1.getString("id"), obj1.getString("sub_category"),
                                obj1.getString("category_id"), obj1.getString("amount"),
                                obj1.getString("image"), obj1.getString("time_duration")
                        ));
                    }

                    for(sub_category q : customSubCategory)
                    {
                        //customQuestion.add(q);

                        if(category_id != null && (q.getCategory_id().compareTo(category_id)) == 0) {
                            // m.append(q.getId()+  ", " + q.getCategory_name() + "," +q.getMain_category() +  ", " +" \n");

                            finalsubCategory.add(q);
                        }

                        else {
                            //m.setText("Coming Soon");
                            //Toast.makeText(Category.this,"coming soon",Toast.LENGTH_LONG).show();
                        }
                    }

                    if(finalsubCategory.size() == 0)
                    {
                        Toast.makeText(Sub_Category.this,"Coming Soon",Toast.LENGTH_LONG).show();
                    }
                    else {
                        adapter_sub_category adapter = new adapter_sub_category(Sub_Category.this, finalsubCategory, category_id);
                        //recyclerView.setLayoutManager(new LinearLayoutManager(mainmenu.this));

                        int numberOfColumns = 1;
                        recyclerView.setLayoutManager(new GridLayoutManager(Sub_Category.this, numberOfColumns));
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