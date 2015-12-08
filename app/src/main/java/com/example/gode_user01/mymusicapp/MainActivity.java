package com.example.gode_user01.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] filenames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Play();
    }


    private void Play() {
        ListView lv = (ListView) findViewById(R.id.song_list);
        filenames = getResources().getStringArray(R.array.filenames);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.songs, filenames);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                intent.putExtra("file", filenames[index]);
                intent.setAction(MusicActivity.ACTION_PLAY);
                startService(intent);
            }
        });
    }


    public void onClickStop(View view) {
        Intent intent = new Intent(this, MusicActivity.class);
        intent.setAction(MusicActivity.ACTION_STOP);
        startService(intent);
    }
}