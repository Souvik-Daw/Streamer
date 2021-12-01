package com.example.streamer.VideoStreaming;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streamer.R;

public class subjectTopic_redirect extends AppCompatActivity {

    RecyclerView recyclerView;
    String a[ ] ;
    ImageView cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_topic_redirect);

        cancelBtn = findViewById(R.id.cancelBtn);

        recyclerView = findViewById(R.id.rv_sub);
        a = new String[ 7 ];
        a[ 0 ] = "1" ;
        a[ 1 ] = "2" ;
        a[ 2 ] = "3" ;
        a[ 3 ] = "4" ;
        a[ 4 ] = "5" ;
        a[ 5 ] = "6" ;
        a[ 6 ] = "UPSC" ;


        subjectAdapter adapter = new subjectAdapter(subjectTopic_redirect.this,a);
        //recyclerView.setLayoutManager(new LinearLayoutManager(mainmenu.this));

        int numberOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(subjectTopic_redirect.this, numberOfColumns));
        recyclerView.setAdapter(adapter);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectTopic_redirect.this.finish();
            }
        });

    }
}