package com.example.streamer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.streamer.adapter.adapter_sub_category;
import com.example.streamer.adapter.adapter_subject;
import com.example.streamer.custom.main_category;
import com.example.streamer.custom.question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Subject_TO_Question extends AppCompatActivity
{

    String category_id;
    String sub_category_id;
    String subject_id;
    TextView start_page_no_of_ques;
    private RequestQueue mQueue;
    Button start;
    ArrayList<com.example.streamer.custom.question> customQuestion = new ArrayList<>();
    ArrayList<com.example.streamer.custom.question> finalQuestion = new ArrayList<>();
    public static final String stqToquiz = "stqToquiz";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtect_to_question);

        start_page_no_of_ques = findViewById(R.id.start_page_no_of_ques);
        mQueue = Volley.newRequestQueue(Subject_TO_Question.this);
        start = findViewById(R.id.start);


        Intent intent = getIntent();
        category_id = intent.getStringExtra(adapter_subject.SOQtoSTQ_cId);
        sub_category_id = intent.getStringExtra(adapter_subject.SOQtoSTQ_ScId);
        subject_id = intent.getStringExtra(adapter_subject.SOQtoSTQ_sId);

        jsonQuestionParse();

        //Toast.makeText(Subject_TO_Question.this,category_id + "        " + sub_category_id
              //  + "           " + subject_id,Toast.LENGTH_LONG).show();

    }


    public void jsonQuestionParse() {

        ArrayList<question> customQuestion2 = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://www.o2academia.org/demo_crud/jsonTable.php", null, new Response.Listener<JSONArray>()
        {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length();i++){
                        JSONObject obj = response.getJSONObject(i);

                        customQuestion2.add(new question(obj.getString("id"), obj.getString("category_id"),
                                obj.getString("subcategory_id"), obj.getString("subject_id"),
                                obj.getString("set_no"), obj.getString("question"),
                                obj.getString("option_one"), obj.getString("option_two"),
                                obj.getString("option_three"), obj.getString("option_four"),
                                obj.getString("answer"), obj.getString("solution"),
                                obj.getString("status")) );

                    }

                    int p = 0;

                    for(question q : customQuestion2)
                    {
                        if(q.getSubject_id().compareTo(subject_id) == 0 &&
                                q.getCategory_id().compareTo(category_id) == 0 &&
                                q.getSubcategory_id().compareTo(sub_category_id) == 0 &&
                                q.getStatus().contains("Y"))
                            customQuestion.add(q);

                        else if (q.getSubject_id().compareTo(subject_id) == 0 &&
                                q.getCategory_id().compareTo(category_id) == 0 &&
                                q.getSubcategory_id().compareTo(sub_category_id) == 0 &&
                                q.getStatus().contains("N"))
                            p++;
                    }

                    start_page_no_of_ques.append("" + customQuestion.size());

                    if(customQuestion.size() == 0)
                    {
                        start.setText("Coming Soon");
                        start.setBackgroundColor(R.color.coming_soon);
                        start_page_no_of_ques.setText("No.of Question's Remaining: " + p);
                        Toast.makeText(Subject_TO_Question.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        start.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(Subject_TO_Question.this, Quiz.class);
                                in.putExtra(stqToquiz, customQuestion);
                                startActivity(in);
                            }
                        });
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