package com.example.andrew.itstime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class NowActivity extends AppCompatActivity {
    private TextView txtDescription;
    private TextView txtTime;
    private Button gotIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now);


        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtTime = (TextView) findViewById(R.id.txtTime);
        gotIt = (Button) findViewById(R.id.btnGotit);

        gotIt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                fireNew(getIntent().getStringExtra("TO_FIRE"));
            }
        });



        fireNew(getIntent().getStringExtra("TO_FIRE"));
    }

    private void play(){
        Log.i("It's Time", "sound");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/liqid/tcheep-still-shining?in=liqid/sets/amal-full-album"));
        startActivity(intent);
    }

    private void showInfo(String currEvent) {
        Log.i("It's Time", "show info");
        txtDescription.setText(currEvent);
        Calendar c = Calendar.getInstance();
        txtTime.setText(c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + " " + c.get(Calendar.AM_PM));


    }

    private void fireNew(String currEvent){
        Log.i("It's Time", "fire new");

        AlarmManager alarmMgr;
        PendingIntent pendingIntent;
        Context context = getApplicationContext();
        Integer timeToGo = 1;
        Integer units = 60 * 1000;

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NowActivity.class);


        EatTimes times = EatTimes.valueOf(currEvent);

        switch (times){
            case AWAKE:

            case FIRST_BREAKFAST:
                intent.putExtra("TO_FIRE", EatTimes.SECOND_BREAKFAST.name());
                timeToGo = EatTimes.SECOND_BREAKFAST.getVal() * units;

                showInfo(getIntent().getStringExtra("TO_FIRE"));
                break;

            case SECOND_BREAKFAST:
                intent.putExtra("TO_FIRE", EatTimes.LUNCH.name());
                timeToGo = EatTimes.LUNCH.getVal() * units;

                showInfo(getIntent().getStringExtra("TO_FIRE"));
                play();
                break;

            case LUNCH:
                intent.putExtra("TO_FIRE", EatTimes.AFTER_LUNCH.name());
                timeToGo = EatTimes.AFTER_LUNCH.getVal() * units;

                showInfo(getIntent().getStringExtra("TO_FIRE"));
                play();
                break;

            case AFTER_LUNCH:
                intent.putExtra("TO_FIRE", EatTimes.AFTER_TRAIN.name());
                timeToGo = EatTimes.AFTER_TRAIN.getVal() * units;

                showInfo(getIntent().getStringExtra("TO_FIRE"));
                play();
                break;

            case AFTER_TRAIN:
                intent.putExtra("TO_FIRE", EatTimes.FIRST_DINNER.name());
                timeToGo = EatTimes.FIRST_DINNER.getVal() * units;

                showInfo(getIntent().getStringExtra("TO_FIRE"));
                play();
                break;

            case FIRST_DINNER:
                intent.putExtra("TO_FIRE", EatTimes.SECOND_DINNER.name());
                timeToGo = EatTimes.SECOND_DINNER.getVal() * units;

                showInfo(getIntent().getStringExtra("TO_FIRE"));
                play();
                break;

            case SECOND_DINNER:
                intent.putExtra("TO_FIRE", EatTimes.BEFORE_NIGHT.name());
                timeToGo = EatTimes.BEFORE_NIGHT.getVal() * units;

                showInfo(getIntent().getStringExtra("TO_FIRE"));
                play();
                break;

            case BEFORE_NIGHT:

                showInfo(getIntent().getStringExtra("TO_FIRE"));
                play();
                break;


        }

        Log.i("It's Time", "Next: " + intent.getStringExtra("TO_FIRE"));
        pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        if (intent.getStringExtra("TO_FIRE") != null) {
            alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() +
                            timeToGo, pendingIntent);
        }
    }
}
