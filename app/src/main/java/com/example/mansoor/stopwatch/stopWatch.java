package com.example.mansoor.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mansoor on 1/27/2018.
 */
public class stopWatch extends Activity {

    boolean running;
    int time;
    final static String TAG = "myMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch_main);
        if (savedInstanceState != null)
        {
            running = savedInstanceState.getBoolean("running");
            time = savedInstanceState.getInt("time");
        }
        Timer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("time",time);
        outState.putBoolean("running",running);
    }
    private void makeToast (String string)
    {
        Toast.makeText(stopWatch.this, string, Toast.LENGTH_SHORT).show();
    }
    public void startWatch(View view)
    {
        if (running == true)
        {
            makeToast("Watch already Start");
        }
        Log.i(TAG,"startWatch");
        running=true;
    }

    public void stopWatch(View view)
    {
        Log.i(TAG,"stopWatch");

        if (running == false)
        {
            makeToast("Watch already Stop");
        }
        running=false;
    }

    public void resetWatch(View view)
    {
        if (running == false && time == 0)
        {
            makeToast("Watch already Reset");
        }
        Log.i(TAG,"resetWatch");
        running=false;
        time = 0;
    }

    private void Timer()
    {
        final TextView v = (TextView) findViewById(R.id.timeTextView);
        // final int Msec;

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                // int Msec = time%(1000/60);
                int Msec = (time)% 60;
                int sec = ((time% (60*60)) / 60);
                int mint = (time/ (60*60));

                int hours = mint/60;
                mint = mint % 60;

                v.setText(String.format("%d:%02d:%02d:%02d",hours,mint,sec,Msec));

                if (running)
                {
                    Log.i(TAG,"Time++");
                    time++;
                }

                handler.postDelayed(this,1);
            }
        });

    }
}
