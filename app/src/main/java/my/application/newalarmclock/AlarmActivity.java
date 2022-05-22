package my.application.newalarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

    Ringtone ringtone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm);

        Uri notficationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this, notficationUri);
        if(ringtone == null){
            notficationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            ringtone = RingtoneManager.getRingtone(this, notficationUri);
        }
        if(ringtone != null){
            ringtone.play();
        }
    }

    @Override
    protected void onDestroy() {
        if(ringtone != null && ringtone.isPlaying()){
            ringtone.stop();
        }
        super.onDestroy();
    }
}