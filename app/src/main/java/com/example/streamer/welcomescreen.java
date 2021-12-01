package com.example.streamer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.example.streamer.VideoStreaming.homepage;

public class welcomescreen extends AppCompatActivity {

    ImageView splash;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_welcomescreen);

        //splash=findViewById(R.id.image);
        lottieAnimationView=findViewById(R.id.lottiechocopie);

        //splash.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(2000).setStartDelay(3000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(welcomescreen.this, introductoryPage.class));
                welcomescreen.this.finish();
            }
        },3000);
    }
}