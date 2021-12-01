package com.example.streamer.VideoStreaming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.streamer.R;

public class course_available extends AppCompatActivity {

    LinearLayout select_subject_topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_available_list_structure);

        select_subject_topic = findViewById(R.id.select_subject_topic);

        select_subject_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_available.this, subjectTopic_redirect.class);
                startActivity(intent);
            }
        });
    }
}