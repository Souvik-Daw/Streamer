package com.example.streamer.VideoStreaming;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.streamer.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeVideo extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    //Initialize variables
    PlayerView playerView;
    ProgressBar progressBar;
    ImageView btFullscreen;
    SimpleExoPlayer simpleExoPlayer;
    boolean flag = false;

    VideoView video;
    String collectionName ="" , docName ="" , position ="" ;
    Map<String, Object> map = new HashMap<>();
    ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_video);

        //Assign variable
        playerView = findViewById(R.id.player_view);
        progressBar = findViewById(R.id.progress_bar);
        btFullscreen = playerView.findViewById(R.id.bt_fullscreen);

        Intent intent = getIntent();
        collectionName = intent.getStringExtra(MyListAdapter.collectionExport);
        docName = intent.getStringExtra(MyListAdapter.docExport);
        position = intent.getStringExtra(MyListAdapter.positionExport);
        docName+="_video";


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
                        list = (ArrayList) map.get("video_url");
                    }

                   /* MediaController mediaController=new MediaController(MainActivity.this);
                    video=findViewById(R.id.vid);
                    video.setMediaController(mediaController);
                    mediaController.setAnchorView(video);
                    Uri uri=Uri.parse(list.get(Integer.parseInt(position)));
                    video.setVideoURI(uri);
                    video.start();*/

                    //make activity full screen
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                            , WindowManager.LayoutParams.FLAG_FULLSCREEN);

                    //Video url
                    Uri videoUrl = Uri.parse(list.get(Integer.parseInt(position)));


                    //Initialize load cntrol
                    LoadControl loadControl = new DefaultLoadControl();

                    //Initialize band width mter
                    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

                    //Initialize track selector
                    TrackSelector trackSelector = new DefaultTrackSelector(
                            new AdaptiveTrackSelection.Factory(bandwidthMeter)
                    );
                    //Initialize simple exo player
                    simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(
                            HomeVideo.this,trackSelector,loadControl
                    );
                    //Initialize data source factory
                    DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory(
                            "exoplayer_video"
                    );
                    //Intialize extractors factory
                    ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

                    //Initialize media source
                    MediaSource mediaSource = new ExtractorMediaSource(videoUrl
                            , factory,extractorsFactory,null,null);

                    //set Player
                    playerView.setPlayer(simpleExoPlayer);
                    //Keep Screen on
                    playerView.setKeepScreenOn(true);
                    //Prepare media
                    simpleExoPlayer.prepare(mediaSource);
                    //Play video when ready
                    simpleExoPlayer.setPlayWhenReady(true);
                    simpleExoPlayer.addListener(new Player.EventListener() {
                        @Override
                        public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

                        }

                        @Override
                        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                        }

                        @Override
                        public void onLoadingChanged(boolean isLoading) {

                        }

                        @Override
                        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                            //Check condition
                            if(playbackState == Player.STATE_BUFFERING){
                                //When bufering
                                //Show progress bar
                                progressBar.setVisibility(View.VISIBLE);
                            }else if (playbackState == Player.STATE_READY){
                                //when ready hide progress bar
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onRepeatModeChanged(int repeatMode) { }

                        @Override
                        public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) { }

                        @Override
                        public void onPlayerError(ExoPlaybackException error) { }

                        @Override
                        public void onPositionDiscontinuity(int reason) { }

                        @Override
                        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) { }

                        @Override
                        public void onSeekProcessed() { }
                    });

                    btFullscreen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Check Condition
                            if(flag){
                                //When flag is true set Enter fullscreen image
                                btFullscreen.setImageDrawable(getResources()
                                        .getDrawable(R.drawable.fullscreen));

                                //Set portrait orientation
                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                                //Set flag value is false
                                flag = false;
                            }else {
                                //When flag is false set exit full screen image
                                btFullscreen.setImageDrawable(getResources()
                                        .getDrawable(R.drawable.fullscreen_exit));

                                //Set landscape orientation
                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                                //Set flag value is true
                                flag = true;
                            }

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Stop video when ready
        simpleExoPlayer.setPlayWhenReady(false);
        //Get playback state
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //play video when ready
        simpleExoPlayer.setPlayWhenReady(true);
        //Get playback state
        simpleExoPlayer.getPlaybackState();
    }


    // double press of back button to exit from videoplaying screen.
    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(), "Press Back Again to Exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}