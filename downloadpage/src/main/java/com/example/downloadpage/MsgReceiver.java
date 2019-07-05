package com.example.downloadpage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public class MsgReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"收到广播",Toast.LENGTH_LONG).show();
        Intent i = new Intent(context, RunningService.class);

        //判断是否是8.0版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //这是8.0以后的版本需要这样跳转
            context.startForegroundService(i);
        } else {
            context.startService(i);
        }

        throw new UnsupportedOperationException("Not yet implemented");

    }
}
