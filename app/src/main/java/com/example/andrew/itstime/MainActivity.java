package com.example.andrew.itstime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAwake = R.id.btnAwake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAwake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlarmManager alarmMgr;
                PendingIntent alarmIntent;
                Context context = getApplicationContext();
                Integer timeToGo = 0;
                Integer units = 60 * 1000;

                alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(context, NowActivity.class);

                intent.putExtra("TO_FIRE", EatTimes.FIRST_BREAKFAST.name());
                timeToGo = EatTimes.FIRST_BREAKFAST.getVal() * units;

                alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() +
                                timeToGo, alarmIntent);
            }
            });
    }
}
