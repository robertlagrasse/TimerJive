package com.example.android.timerjive;

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

    Timer mTimer = new Timer();
    Boolean isRunning = false;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            try {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("TimerTask()", "Doing the thing!");

            timerHandler.postDelayed(this,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            Log.e("savedInstance", "isRunning value set to :" + isRunning.toString());

            isRunning = savedInstanceState.getBoolean("isRunning");
        } else {
            Log.e("savedInstance", "savedInstance was null.");
            isRunning = false;
        }
    }

    public void startStop(View v){
        if(isRunning){
            timerHandler.removeCallbacks(timerRunnable);
        } else {
            timerHandler.postDelayed(timerRunnable, 0);
        }
        isRunning = !isRunning;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean("isRunning", isRunning);
    }
}
