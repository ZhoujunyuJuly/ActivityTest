package com.example.downloadpage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class RunningService extends Service {
    public RunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Notify.DownloadAlarm(getApplicationContext());
                Log.d("zjy", "service is 1");

            }
        }).start();


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int interval = 25*1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + interval;
        Intent i = new Intent(this, RunningService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        alarmManager.cancel(pi);

        if (Build.VERSION.SDK_INT < 19) {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,  triggerAtTime, pi);
        } else {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        }

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("zjy", "onCreate: service");
    }
}
