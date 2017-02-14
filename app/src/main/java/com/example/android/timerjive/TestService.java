package com.example.android.timerjive;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * Created by robert on 2/14/17.
 */

public class TestService extends IntentService {
//    IntentFilter filter = new IntentFilter();
//    startStopReceiver startStopReceiver = new startStopReceiver();

    private static Boolean isRunning = false;
    private static Context mContext;

    private static Handler timerHandler = new Handler();
    private static Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            try {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(mContext, notification);
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
    public void onCreate() {
        super.onCreate();
        Log.e("Test Service", "onCreate()");

    }

    @Override
    public void onDestroy() {
        Log.e("Test Service", "onDestroy()");
        super.onDestroy();    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("TestService", "onHandleIntent");
    }

    private static void flip(){
        Log.e("TestService", "flip()");
        if(isRunning){
            timerHandler.removeCallbacks(timerRunnable);
        } else {
            timerHandler.postDelayed(timerRunnable, 0);
        }
        isRunning = !isRunning;
    }

    public static class startStopReceiver extends BroadcastReceiver {
        public startStopReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("startStopReceiver", "onreceive()");
            mContext = context;
            flip();
        }
    }
}
