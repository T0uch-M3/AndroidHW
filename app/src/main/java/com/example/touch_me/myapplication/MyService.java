package com.example.touch_me.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class MyService extends Service {
    private String res;
    private String affiche;

    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle c = intent.getExtras();
        if (c != null) {
            res = c.getString("val1");
            affiche = "result from service " + res;
        }
        Toast.makeText(getBaseContext(), affiche, Toast.LENGTH_LONG).show();

        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra("message","withBroadcast"));
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
