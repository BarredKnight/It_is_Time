package com.example.andrew.itstime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NowActivity extends AppCompatActivity {
    TextView txtDescription = R.id.txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now);
        Intent nextEvent = new Intent(this, NowActivity.class);

        doDeal(getIntent().getStringExtra("TO_FIRE"));
        fireNew(getIntent().getStringExtra("TO_FIRE"));
    }

    private void doDeal(String currEvent) {
        txtDescription.setText(currEvent);
    }

    private void fireNew(String currEvent){

        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
        Context context = getApplicationContext();
        Integer timeToGo = 0;
        Integer units = 60 * 1000;

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NowActivity.class);


        EatTimes times = EatTimes.valueOf(currEvent);

        switch (times){
            case FIRST_BREAKFAST:
                intent.putExtra("TO_FIRE", EatTimes.SECOND_BREAKFAST.name());
                timeToGo = EatTimes.SECOND_BREAKFAST.getVal() * units;
                break;

            case SECOND_BREAKFAST:
                intent.putExtra("TO_FIRE", EatTimes.LUNCH.name());
                timeToGo = EatTimes.LUNCH.getVal() * units;

                break;

            case LUNCH:
                intent.putExtra("TO_FIRE", EatTimes.AFTER_LUNCH.name());
                timeToGo = EatTimes.AFTER_LUNCH.getVal() * units;

                break;

            case AFTER_LUNCH:
                intent.putExtra("TO_FIRE", EatTimes.AFTER_TRAIN.name());
                timeToGo = EatTimes.AFTER_TRAIN.getVal() * units;

                break;

            case AFTER_TRAIN:
                intent.putExtra("TO_FIRE", EatTimes.FIRST_DINNER.name());
                timeToGo = EatTimes.FIRST_DINNER.getVal() * units;

                break;

            case FIRST_DINNER:
                intent.putExtra("TO_FIRE", EatTimes.SECOND_DINNER.name());
                timeToGo = EatTimes.SECOND_DINNER.getVal() * units;

                break;

            case SECOND_DINNER:
                intent.putExtra("TO_FIRE", EatTimes.BEFORE_NIGHT.name());
                timeToGo = EatTimes.BEFORE_NIGHT.getVal() * units;

                break;

            case BEFORE_NIGHT:
                break;


        }
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        timeToGo, alarmIntent);
    }
}
