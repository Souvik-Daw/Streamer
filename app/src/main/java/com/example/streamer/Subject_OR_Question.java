package com.example.streamer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.streamer.adapter.adapter_sub_category;
import com.example.streamer.adapter.adapter_subject;
import com.example.streamer.custom.sub_category;
import com.example.streamer.custom.subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Subject_OR_Question extends AppCompatActivity {


    String category_id;
    String sub_category_id;
    private RequestQueue mQueue;
    ArrayList<subject> customSubject = new ArrayList<>();
    RecyclerView recyclerView;
    public static final String SOQtoQ_cId = "SOQtoQ_cId";
    public static final String SOQtoQ_ScId = "SOQtoQ_ScId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_or_question);

        recyclerView = findViewById(R.id.subject);
        mQueue = Volley.newRequestQueue(Subject_OR_Question.this);


        Intent intent = getIntent();
        category_id = intent.getStringExtra(adapter_sub_category.subToSOQ_Cid);
        sub_category_id = intent.getStringExtra(adapter_sub_category.subToSOQ_SCid);

        //Toast.makeText(Subject_OR_Question.this,category_id + "        " + sub_category_id,Toast.LENGTH_LONG).show();


        subjectParse();

    }


    public void subjectParse() {

        ArrayList<subject> finalsubjectCategory = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://o2academia.org/demo_crud/JsonSubject.php", null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length();i++){
                        JSONObject obj1 = response.getJSONObject(i);

                        customSubject.add(new subject(
                                obj1.getString("id"), obj1.getString("subcat_id"),
                                obj1.getString("subject")
                        ));
                    }

                    for(subject q : customSubject)
                    {
                        //customQuestion.add(q);

                        if(sub_category_id != null && (q.getSubcat_id().compareTo(sub_category_id)) == 0) {


                            finalsubjectCategory.add(q);
                        }

                        else {
                            //m.setText("Coming Soon");
                            //Toast.makeText(Category.this,"coming soon",Toast.LENGTH_LONG).show();
                        }
                    }

                    if(finalsubjectCategory.size() == 0)
                    {
                        Intent in = new Intent(Subject_OR_Question.this, Question.class);
                        in.putExtra(SOQtoQ_ScId, sub_category_id);
                        in.putExtra(SOQtoQ_cId,category_id);
                        startActivity(in);
                        Subject_OR_Question.this.finish();

                        //Toast.makeText(Subject_OR_Question.this,"Coming Soon",Toast.LENGTH_LONG).show();
                    }
                    else {

                        recyclerView.setVisibility(View.VISIBLE);
                        adapter_subject adapter = new adapter_subject(Subject_OR_Question.this, finalsubjectCategory, category_id, sub_category_id);
                        //recyclerView.setLayoutManager(new LinearLayoutManager(mainmenu.this));

                        int numberOfColumns = 1;
                        recyclerView.setLayoutManager(new GridLayoutManager(Subject_OR_Question.this, numberOfColumns));
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