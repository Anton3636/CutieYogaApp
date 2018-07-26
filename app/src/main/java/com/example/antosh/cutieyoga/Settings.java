package com.example.antosh.cutieyoga;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class Settings extends AppCompatActivity {




    info.hoang8f.widget.FButton btnSave;
    RadioButton rdiEasy,rdiMedium,rdiHard;
    RadioGroup rdiGroup;
    Switch switchAlarm;
    TimePicker timePicker;
    int Hour,Minute;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //init view
        btnSave = findViewById(R.id.btnSave);
        btnSave.setButtonColor(getResources().getColor(R.color.colorPrimaryDark));
        rdiGroup = findViewById(R.id.rdiGroup);
        rdiEasy = findViewById(R.id.rdiEasy);
        rdiMedium = findViewById(R.id.rdiMedium);
        rdiHard = findViewById(R.id.rdiHard);
        switchAlarm = findViewById(R.id.switchAlarm);
        timePicker = findViewById(R.id.timePicker);
        final MediaPlayer mediaPlayer = MediaPlayer.create(Settings.this,R.raw.button_click);

        int mode = SettingMode.getSetting_mode();
        setRadioButton(mode);


        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    saveWorkoutMode();
                    mediaPlayer.start();
                    saveAlarm(switchAlarm.isChecked());
                Toasty.info(Settings.this,"Saved !!!", Toast.LENGTH_LONG).show();
                finish();
            }
        });




    }

    Intent getNotificationIntent()
    {
        Intent intent = new Intent(this,Settings.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    private void saveAlarm(boolean checked) {
        if(checked)
        {

            Intent intent;
            PendingIntent pendingIntent ;

            intent = new Intent(Settings.this,AlarmNotification.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


            if (Build.VERSION.SDK_INT >= 23 ) {

                 Hour =  timePicker.getHour();
                 Minute = timePicker.getMinute();

            }  else{

                 Minute = timePicker.getCurrentMinute();
                 Hour = timePicker.getCurrentHour();
            }


            Date dat = new Date();
            Calendar cal_alarm = Calendar.getInstance();
            Calendar cal_now = Calendar.getInstance();
            cal_now.setTime(dat);
            cal_alarm.setTime(dat);
            cal_alarm.set(Calendar.HOUR_OF_DAY,Hour);
            cal_alarm.set(Calendar.MINUTE,Minute);
            cal_alarm.set(Calendar.SECOND,10);
            if(cal_alarm.before(cal_now)){
                cal_alarm.add(Calendar.DATE,1);
            }



            manager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
        }
        else
        {
            //cancel alarm
            Intent intent = new Intent(Settings.this,AlarmNotification.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }

    private void saveWorkoutMode() {
        int selectedID = rdiGroup.getCheckedRadioButtonId();
        if(selectedID == rdiEasy.getId())
        {
            SettingMode.setSetting_mode(10);             }
        else if (selectedID == rdiMedium.getId() )
        {
            SettingMode.setSetting_mode(20);        }
        else if (selectedID == rdiHard.getId() )
        {
            SettingMode.setSetting_mode(30);
        }

    }

    private void setRadioButton(int mode) {
        if(mode == 10)
        {
            rdiGroup.check(R.id.rdiEasy);
        }else if ( mode == 20 )
        {
            rdiGroup.check(R.id.rdiMedium);
        }
        else if (mode == 30)
        {
            rdiGroup.check(R.id.rdiHard);
        }
    }


}
