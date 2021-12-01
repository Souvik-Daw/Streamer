package com.example.streamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.streamer.VideoStreaming.homepage;

public class Result2 extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView score, options ;
    Button backBtn, shareBtn ;
    int arr [ ];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        score = findViewById(R.id.score);
        options = findViewById(R.id.options);
        shareBtn = findViewById(R.id.shareBtn);
        backBtn = findViewById(R.id.backBtn);


        int correctAnswers = getIntent().getIntExtra("correct2", 0);
        int totalQuestions = getIntent().getIntExtra("total2", 0);
        Bundle extras = getIntent().getExtras();
        arr = extras.getIntArray("options");
        //Toast.makeText(Result.this,arr +"" , Toast.LENGTH_LONG).show();

        for ( int i = 0 ; i < arr.length; i++ )
        {
            if ( arr[i] == 0 )
            {
                options.append("Question " + ( i+1 ) + " is " + "correct" + "\n" + "\n" );
                //options.setTextColor(Color.parseColor("#4CAF50"));
            }

            else if ( arr[i] == 1 )
            {
                options.append("Question " + ( i+1 ) + " is " + "wrong" + "\n" + "\n" );
                //options.setTextColor(this.getResources().getColor(R.color.red1));
            }

            else if ( arr[i] == 2 )
            {
                options.append("Question " + ( i+1 ) + " is " + "Not Answered" + "\n" + "\n" );
                //options.setTextColor(this.getResources().getColor(R.color.shadow_color));
            }

            else
                Toast.makeText(Result2.this,"Exception" , Toast.LENGTH_LONG).show();
        }


        lottieAnimationView=findViewById(R.id.imageView6);
        lottieAnimationView.animate().setDuration(10000);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.setAnimation(R.raw.trophy);
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();


        score.setText(String.format("%d/%d", correctAnswers, totalQuestions));


    }

    public void share(View view) {
        Toast.makeText(Result2.this,"Share with your friends about your success.",
                Toast.LENGTH_LONG).show();
    }

    public void back(View view) {

        Intent intent = new Intent(Result2.this, homepage.class);
        startActivity(intent);
        Result2.this.finish();
    }
}