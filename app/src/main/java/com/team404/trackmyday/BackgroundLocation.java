package com.team404.trackmyday;


import android.app.Service;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Feyi Oyewole on 10/23/2016.
 */


//Create Service to run locator in background
public class BackgroundLocation extends Service {
    public BackgroundLocation() {
    }
    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "This toast is running in the background", Toast.LENGTH_SHORT).show();
        //Locator background ping will go here
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                Log.d("STATE", "TICK");
            }

            public void onFinish()
            {
                Log.d("STATE", "TIMERACTIVATE");
            }
        }.start();


        //Run even if when closed.
        onTaskRemoved(intent);



        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(),this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
        super.onTaskRemoved(rootIntent);
    }
}
