package com.example.andrew.itstime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button btnAwake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("It's Time", "Awake activity");
        btnAwake = (Button) findViewById(R.id.btnAwake);
        btnAwake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Log.i("It's Time", "AWAKE button click");
                AlarmManager alarmMgr;
                PendingIntent alarmIntent;
                Context context = getApplicationContext();
                Integer timeToGo = 5000;
                Integer units = 60 * 1000;

                alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this, NowActivity.class);

                intent.putExtra("TO_FIRE", EatTimes.FIRST_BREAKFAST.name());
//                timeToGo = EatTimes.FIRST_BREAKFAST.getVal() * units;



                alarmIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime()+timeToGo, alarmIntent);
            }
            });
    }
}
