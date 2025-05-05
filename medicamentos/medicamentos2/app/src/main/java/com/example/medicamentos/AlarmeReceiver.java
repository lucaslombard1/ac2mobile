package com.example.medicamentos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

public class AlarmeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String nome = intent.getStringExtra("nome");
        String descricao = intent.getStringExtra("descricao");

        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "canal_medicamento",
                    "Lembretes de Medicamentos",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Canal para notificações de medicamentos");
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.enableVibration(true);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

      
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "canal_medicamento")
                .setSmallIcon(R.drawable.ic_add)
                .setContentTitle("Hora do medicamento: " + nome)
                .setContentText(descricao)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}
