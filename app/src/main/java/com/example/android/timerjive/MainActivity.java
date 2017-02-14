package com.example.android.timerjive;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer mTimer = new Timer();
    Boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startStop(View v){
        if(isRunning){
            timerStop();
        } else {
            timerStart();
        }
    }

    public void timerStart(){
        // mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {

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
                                       }

                                   },
                //Set how long before to startButton calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                2000);
        isRunning = true;
        return;
    }

    public void timerStop(){
        isRunning = false;
        mTimer.cancel();
        mTimer.purge();
    }
}
