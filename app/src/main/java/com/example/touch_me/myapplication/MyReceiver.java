package com.example.touch_me.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Toast.makeText(context, "Intent Detected", Toast.LENGTH_LONG).show();
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
