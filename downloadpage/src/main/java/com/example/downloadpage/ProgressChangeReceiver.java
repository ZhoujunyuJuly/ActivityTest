package com.example.downloadpage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ProgressChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Toast.makeText(context,"发送广播",Toast.LENGTH_LONG).show();
        //Log.d("zjy", "收到广播");

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
