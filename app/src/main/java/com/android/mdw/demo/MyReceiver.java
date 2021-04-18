package com.android.mdw.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "MYRECIVER", Toast.LENGTH_SHORT).show();
        Intent service = new Intent(context, ElServicio.class);
        Bundle b = new Bundle();
        Bundle extras = intent.getExtras();

        if (intent != null) {
            if (extras != null) {
                if (extras.getString(Main.MYDEFAULTSTRING_ID).equals("Iniciar Sonido")) {
                    Toast.makeText(context, "Intent Recibido ElReceptor - Inicio Sonido", Toast.LENGTH_SHORT).show();
                    b.putString(Main.MYDEFAULTSTRING_ID, "Iniciar Sonido");
                    service.putExtras(b);
                    context.startService(service);
                }
                if (extras.getString(Main.MYDEFAULTSTRING_ID).equals("Iniciar Canción")) {
                    Toast.makeText(context, "Intent Recibido ElReceptor - Inicio Canción", Toast.LENGTH_SHORT).show();
                    b.putString(Main.MYDEFAULTSTRING_ID, "Iniciar Canción");
                    service.putExtras(b);
                    context.startService(service);

                }
            } else {
                Toast.makeText(context, "Intent Recibido ElReceptor - Reproducción Detenida", Toast.LENGTH_SHORT).show();
                context.stopService(service);
            }
        }
    }
}