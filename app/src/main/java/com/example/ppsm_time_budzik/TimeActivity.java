package com.example.ppsm_time_budzik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeActivity extends AppCompatActivity {

    private EditText newYorkTime, londonTime, tokyoTime, mobileTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        newYorkTime = findViewById(R.id.newYorkValue);
        londonTime = findViewById(R.id.londonValue);
        tokyoTime = findViewById(R.id.tokyoValue);
        mobileTime = findViewById(R.id.mobileTimeValue);

        ScheduledExecutorService e = Executors.newSingleThreadScheduledExecutor();
        e.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LocalTime time = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                mobileTime.setText(time.format(formatter));

                time = LocalTime.now(ZoneId.of("Europe/London"));
                londonTime.setText(time.format(formatter));

                time = LocalTime.now(ZoneId.of("America/New_York"));
                newYorkTime.setText(time.format(formatter));

                time = LocalTime.now(ZoneId.of("Asia/Tokyo"));
                tokyoTime.setText(time.format(formatter));
            }
        }, 0, 1, TimeUnit.SECONDS);

    }
}
