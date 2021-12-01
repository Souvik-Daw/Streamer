package com.example.streamer.VideoStreaming;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streamer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class mainmenu extends AppCompatActivity {


    String collectionName = "";
    String a[ ];
    //ArrayList<String> list;
    ArrayList<String> arr;
    RecyclerView recyclerView;
    ArrayAdapter<String> itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        arr = new ArrayList<>();
        Intent intent = getIntent();
        collectionName = intent.getStringExtra(subjectAdapter.subExport);
        recyclerView = (RecyclerView) findViewById(R.id.subjects);



        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(collectionName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        String id = document.getId();
                        if(id.contains("_"))
                        {
                            continue;
                        }
                        else
                        {
                            list.add(id);
                        }
                    }
                    for(int i=0;i<list.size();i++)
                    {
                        arr.add(list.get(i));
                    }

                    a = new String[arr.size()];
                    for (int i=0;i<arr.size();i++)
                    {
                        a[i] = arr.get(i);
                    }

                    if(a.length != 0)
                    {
                    Toast.makeText(mainmenu.this,arr.toString(),Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(mainmenu.this,"coming soon",Toast.LENGTH_LONG).show();
                    }
                    mainmenuAdapter adapter = new mainmenuAdapter(mainmenu.this,a, collectionName);
                    //recyclerView.setLayoutManager(new LinearLayoutManager(mainmenu.this));

                    int numberOfColumns = 1;
                    recyclerView.setLayoutManager(new GridLayoutManager(mainmenu.this, numberOfColumns));
                    recyclerView.setAdapter(adapter);

                    /*itemsAdapter = new ArrayAdapter<String>(mainmenu.this, android.R.layout.simple_list_item_1, arr);
                    listView.setAdapter(itemsAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String sub = ((TextView)view).getText().toString();
                            Toast.makeText(mainmenu.this,sub + "",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(mainmenu.this,videolist_incoming.class);
                            in.putExtra(collectionExport, collectionName);
                            in.putExtra(subExport, sub);
                            startActivity(in);
                        }
                    });*/
                }
                else
                {
                    Toast.makeText(mainmenu.this,"coming soon",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}