package my.application.newalarmclock;


////
import android.app.TimePickerDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
////


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button setAlarm;

    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDateTime = findViewById(R.id.currentDateTime);
        setInitialDateTime();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        setAlarm = findViewById(R.id.alarm_btn);

        setAlarm.setOnClickListener(v -> {


            new TimePickerDialog(MainActivity.this, t, dateAndTime.get(Calendar.HOUR_OF_DAY), dateAndTime
                    .get(Calendar.MINUTE), true)
                    .show();
        });
        TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateAndTime.set(Calendar.MINUTE, minute);
                setInitialDateTime();
            }
        };
    }

    //////////////////////////////////////

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(MainActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    //////////////////////////////////////

    private PendingIntent getAlarmInfoPendingIntent(){
        Intent alarmInfoIntent = new Intent(this, MainActivity.class);
        alarmInfoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return  PendingIntent.getActivity(this, 0,alarmInfoIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private PendingIntent getAlarmActionPendingintent(){
        Intent intent = new Intent(this, AlarmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(this,1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}