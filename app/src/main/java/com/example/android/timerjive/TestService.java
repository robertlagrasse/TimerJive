package com.example.android.timerjive;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * Created by robert on 2/14/17.
 */

public class TestService extends IntentService {

    private Boolean isRunning = false;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {

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

    public TestService() {
        super("TestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("TestService", "onHandleIntent");


    }

    private void flip(){
        Log.e("TestService", "flip()");
        if(isRunning){
            timerHandler.removeCallbacks(timerRunnable);
        } else {
            timerHandler.postDelayed(timerRunnable, 0);
        }
        isRunning = !isRunning;
    }

    public static class TestReceiver extends BroadcastReceiver {

        public TestReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("TestReceiver", "onReceive()");


            //// I can't use this method, and I can't make the receiver non-static...


            // flip();
        }
    }
}
