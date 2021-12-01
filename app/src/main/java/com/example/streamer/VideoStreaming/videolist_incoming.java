package com.example.streamer.VideoStreaming;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.streamer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class videolist_incoming extends AppCompatActivity {

    String collectionName = "", docName = "" , picture ="" ;
    Map<String, Object> map = new HashMap<>();
    Map<String, Object> map2 = new HashMap<>();
    ArrayList<String> list = new ArrayList<>();
    String a[ ] ;
    String doc = "";
    ImageView imageView;
    TextView chapTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist_incoming);

        chapTitle = findViewById(R.id.chapTitle);
        Intent intent = getIntent();
        collectionName = intent.getStringExtra(mainmenuAdapter.collectionExport);
        docName = intent.getStringExtra(mainmenuAdapter.subExport);
        doc = docName ;
        chapTitle.setText(doc);
        docName+= "_image";
        imageView = findViewById(R.id.fixedimage);



        FirebaseFirestore fire = FirebaseFirestore.getInstance();
        DocumentReference db2 =  fire.collection(collectionName).document(doc);
        db2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();
                    if (document.exists())
                    {
                        map2 = document.getData();
                        picture = String.valueOf(map2.get("picture"));
                    }
                    Glide.with(videolist_incoming.this)
                            .load(picture)
                            .into(imageView);
                }
            }
        });


        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference db =  firestore.collection(collectionName).document(docName);
        db.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();
                    if (document.exists())
                    {
                        map = document.getData();
                        list = (ArrayList) map.get("image_url");
                    }
                    Toast.makeText(videolist_incoming.this,list.toString(),Toast.LENGTH_LONG).show();
                    a = new String[list.size()];
                    int i = 0;
                    for(String p:list) {
                        a[ i ] = p;
                        i++;
                    }

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rview);
                    MyListAdapter adapter = new MyListAdapter(videolist_incoming.this,a, collectionName, doc);
                    recyclerView.setLayoutManager(new LinearLayoutManager(videolist_incoming.this));
                    recyclerView.setAdapter(adapter);
                }
            }
        });

    }
}