package com.android.mdw.demo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Main extends Activity implements OnClickListener {

    private Intent in;
    public static String MYDEFAULTSTRING_ID;
    public static String uriresult;
    public static String STRING_VIA_D;
    private MyReceiver receiver;
    private Intent broadIni;
    private Intent broadSong;
    private Intent broadStop;
    private Intent selected;
    private Bundle partD;
    private Bundle partE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Button btnInicio = (Button) findViewById(R.id.btnInicio);
        Button btnFin = (Button) findViewById(R.id.btnFin);
        Button btnSongIni = (Button) findViewById(R.id.song);
        Button btnSelec = (Button) findViewById(R.id.btnSelAudio);
        Button btnRepCancion = (Button) findViewById(R.id.RepCancion);
        partD = new Bundle();
        partE = new Bundle();

        btnInicio.setOnClickListener(this);
        btnFin.setOnClickListener(this);
        btnSongIni.setOnClickListener(this);
        btnSelec.setOnClickListener(this);
        btnRepCancion.setOnClickListener(this);

        broadIni = new Intent(this, MyReceiver.class);
        broadSong = new Intent(this, MyReceiver.class);
        broadStop = new Intent(this, MyReceiver.class);
        selected = new Intent(this, ElServicio.class);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View src) {

        Bundle b = new Bundle();
        switch (src.getId()) {
            case R.id.btnInicio:
                b.putString(MYDEFAULTSTRING_ID, "Iniciar Sonido");
                broadIni.putExtras(b);
                sendBroadcast(broadIni);
                break;
            case R.id.song:
                b.putString(MYDEFAULTSTRING_ID, "Iniciar Canción");
                broadSong.putExtras(b);
                sendBroadcast(broadSong);
                break;
            case R.id.btnFin:
                sendBroadcast(broadStop);
                stopService(selected);
                break;
            case R.id.btnSelAudio:
                Intent saba = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(saba, 111);
                break;
            case R.id.RepCancion:
                Toast.makeText(this, "HE SIGUT PITXAT", Toast.LENGTH_SHORT).show();
                partD.putString(STRING_VIA_D, "Iniciar Canción via Uri");
                selected.putExtra("partD", partD);
                startService(selected);
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 111) {

            partD.putString(STRING_VIA_D, "Iniciar Canción via Uri");
            Uri intentUri = data.getData();
            String stringUri = intentUri.toString();

            partE.putString(uriresult, stringUri);
            selected.putExtra("partD", partD);
            selected.putExtra("partE", partE);
            startService(selected);
            partE.clear();
            Toast.makeText(this, "SERVICE STARTED", Toast.LENGTH_SHORT).show();
        }

    }
}