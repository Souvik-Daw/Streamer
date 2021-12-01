package com.example.streamer.VideoStreaming;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.streamer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class feedback extends AppCompatActivity {

    ImageView BackBtn;
    EditText NameBox , SMSBox , SubjectBox , BatchBox , EmailBox ;
    Button ButtonSendFeedback;
    Map<String, Object> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        BackBtn = findViewById(R.id.BackBtn);
        NameBox = findViewById(R.id.NameBox);
        SMSBox = findViewById(R.id.SMSBox);
        SubjectBox = findViewById(R.id.SubjectBox);
        BatchBox = findViewById(R.id.BatchBox);
        EmailBox = findViewById(R.id.EmailBox);
        ButtonSendFeedback = findViewById(R.id.ButtonSendFeedback);


        ButtonSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = SMSBox.getText().toString();
                if( s.length() == 0 )
                {
                    //Toast.makeText(feedback.this, "Message Section can't be Blank", Toast.LENGTH_LONG).show();
                    SMSBox.setError("Message Section can't be Blank");
                }
                else
                {
                    // Create a new user with a first, middle, and last name
                    user.put("Name", NameBox.getText().toString());
                    user.put("EmailId", EmailBox.getText().toString());
                    user.put("Batch", BatchBox.getText().toString());
                    user.put("Subject", SubjectBox.getText().toString());
                    user.put("FeedBack Message", SMSBox.getText().toString());


                    // Add a new document with a generated ID
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("feedback")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference)
                                {
                                    //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(feedback.this, "Feedback Sent", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    //Log.w(TAG, "Error adding document", e);
                                    Toast.makeText(feedback.this, "Feedback Sent Failure", Toast.LENGTH_LONG).show();
                                }
                            });

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            feedback.this.finish();
                        }
                    },3000);

                }




            }
        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback.this.finish();
            }
        });

    }
}