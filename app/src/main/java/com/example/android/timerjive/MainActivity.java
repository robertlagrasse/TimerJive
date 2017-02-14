package com.example.android.timerjive;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public void launchTestService(){
        Intent i = new Intent(this, TestService.class);
        startService(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            Log.e("savedInstance", "We're already running. Not Starting Service.");
        } else {
            Log.e("savedInstance", "Not Running. Starting Service.");
            launchTestService();
        }
    }


    public void startStop(View v){
        Log.e("MainActivity", "startStop()");
        Intent intent = new Intent();
        intent.setAction("com.example.android.timerjive.STARTSTOP");
        sendBroadcast(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
