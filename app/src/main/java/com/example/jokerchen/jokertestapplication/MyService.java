package com.example.jokerchen.jokertestapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    public static void start(Context context){
        try {
            Intent intent = new Intent(context,MyService.class);
            context.startService(intent);
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"oncreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public IBinder onBind(Intent intent) {
        return new WemusicBinder();
    }

    public class WemusicBinder extends Binder {

    }
}
