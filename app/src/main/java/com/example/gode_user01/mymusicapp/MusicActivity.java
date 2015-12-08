package com.example.gode_user01.mymusicapp;

/**
 * Created by gode_user01 on 12/7/2015.
 */
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import java.io.IOException;

public class MusicActivity extends Service {

    public static final String ACTION_PLAY = "play";
    public static final String ACTION_STOP = "stop";

    private MediaPlayer player;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getAction();

        if (action.equals(ACTION_PLAY)) {
            String file = intent.getStringExtra("file");

            if (player != null && player.isPlaying()) {
                player.stop();
                player.release();
            }

            try {
                AssetFileDescriptor afd;
                afd = getAssets().openFd(file);
                player = new MediaPlayer();
                player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                player.prepare();
                player.setLooping(true);
                player.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (action.equals(ACTION_STOP)) {

            if (player != null && player.isPlaying()) {
                player.stop();

            }
        }
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "My Music Service Stopped", Toast.LENGTH_SHORT).show();
        player.stop();
        player.release();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}