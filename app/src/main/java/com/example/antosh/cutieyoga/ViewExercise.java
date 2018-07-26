package com.example.antosh.cutieyoga;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antosh.cutieyoga.Utils.Common;

import es.dmoral.toasty.Toasty;

public class ViewExercise extends AppCompatActivity {

    int image_id;
    String name;

    TextView timer,title;
    ImageView detail_image;
    ImageView clock;
    Button btnStart;
    Boolean isRunning = false;
    int tick = 1000;
    int timeCount = 20000;
    CountDownTimer cdTimer;
    info.hoang8f.widget.FButton btn;
    boolean manualFinishing = false;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(manualFinishing)
        {
            clock.setVisibility(View.GONE);
            Toasty.info(ViewExercise.this, "You Leave The Exercise", Toast.LENGTH_LONG, true).show();
            cdTimer.cancel();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);
        clock = findViewById(R.id.clock);
        btn = findViewById(R.id.btnStart);
        btn.setButtonColor(getResources().getColor(R.color.colorPrimaryDark));
        timer = findViewById(R.id.timer);
        title = findViewById(R.id.titttle);
        detail_image = findViewById(R.id.detail_image);
        btnStart = findViewById(R.id.btnStart);
        final MediaPlayer player = MediaPlayer.create(ViewExercise.this,R.raw.endtimer);
        final MediaPlayer mediaPlayer = MediaPlayer.create(ViewExercise.this,R.raw.button_click);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                manualFinishing = true;
                if(!isRunning)
                {
                    clock.setVisibility(View.VISIBLE);
                    btnStart.setText("Done");
                    int  timeLimit = 0;
                    if(SettingMode.getSetting_mode() == 10) {
                        timeLimit = Common.TIME_LIMIT_EASY;
                        timeCount = timeLimit;
                    }
                        if (SettingMode.getSetting_mode() == 20 ) {
                            timeLimit = Common.TIME_LIMIT_MEDIUM;
                            timeCount = timeLimit;
                        }
                        if (SettingMode.getSetting_mode() == 30 ) {
                            timeLimit = Common.TIME_LIMIT_HARD;
                            timeCount = timeLimit;
                        }
                    cdTimer = new CountDownTimer(timeLimit,tick)
                    {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            timeCount-=1000;

                            timer.setText(""+timeCount/1000);
                        }

                        @Override
                        public void onFinish() {
                            player.start();
                            clock.setVisibility(View.GONE);
                            manualFinishing = false;
                            Toasty.success(ViewExercise.this, "Finished!!!", Toast.LENGTH_LONG, true).show();                            finish();
                        }
                    }.start();
                }else
                {
                    player.start();
                    clock.setVisibility(View.GONE);
                    manualFinishing = false;
                    Toasty.success(ViewExercise.this, "Finished!!!", Toast.LENGTH_LONG, true).show();
                    cdTimer.cancel();
                    finish();
                }
                isRunning = !isRunning;

            }
        });



        if(getIntent() != null)
        {
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);

        }

    }
}
