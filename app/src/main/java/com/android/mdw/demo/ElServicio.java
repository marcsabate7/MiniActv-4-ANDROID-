package com.android.mdw.demo;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.IOException;

import static com.android.mdw.demo.Main.MYDEFAULTSTRING_ID;
import static com.android.mdw.demo.Main.STRING_VIA_D;
import static com.android.mdw.demo.Main.uriresult;

public class ElServicio extends Service {

    private MediaPlayer player1;
    private MediaPlayer player2;
    private MediaPlayer selectable;
    private MediaPlayer defaultt;
    private Uri uri;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show();
        player1 = MediaPlayer.create(this, R.raw.train);
        player2 = MediaPlayer.create(this, R.raw.doiwannaknow);
        player1.setLooping(true);
        player2.setLooping(true);
        selectable = new MediaPlayer();
        defaultt = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show();
        if (player1.isPlaying())
            player1.stop();
        if (player2.isPlaying())
            player2.stop();
        if (selectable.isPlaying()) {
            selectable.stop();
        }
        if (defaultt.isPlaying()) {
            defaultt.stop();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        Toast.makeText(this, R.string.iniserv, Toast.LENGTH_LONG).show();
        selectable.reset();
        defaultt.reset();

        if (intent.getBundleExtra("partD") != null) {

            if (intent.getBundleExtra("partE") == null || intent.getBundleExtra("partE").isEmpty()) {
                
                defaultt = MediaPlayer.create(this, Uri.parse("content://media/external/audio/media/238489"));
                defaultt.start();

            } else if (intent.getBundleExtra("partD").getString(STRING_VIA_D).equals("Iniciar Canción via Uri")) {
                String saba2 = intent.getBundleExtra("partE").getString(uriresult);
                uri = Uri.parse(saba2);

                selectable = MediaPlayer.create(this, uri);
                selectable.start();
            }


        } else {


            if (intent.getExtras().getString(Main.MYDEFAULTSTRING_ID).equals("Iniciar Sonido")) {
                Toast.makeText(this, "Servicio Sonido Activado", Toast.LENGTH_LONG).show();
                player1.start();

            } else if (intent.getExtras().getString(Main.MYDEFAULTSTRING_ID).equals("Iniciar Canción")) {
                Toast.makeText(this, "Servicio Canción Activado", Toast.LENGTH_LONG).show();
                player2.start();
            }
        }
        return startid;

    }
}

