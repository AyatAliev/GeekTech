package com.geektech.geektech;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {

    private static final String NOTIFICATION_CHANNEL = "channel notification";

    public static void CreateNatification(Context context) {

        CreateNotificationChannel(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.logo_geek)
                .setContentTitle("GeekTech")
                .setAutoCancel(false)
                .setContentText("Нужен ментор")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Нужен ментор"))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }

    public static void CreateNatificationOpen(Context context) {

        CreateNotificationChannel(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.logo_geek)
                .setContentTitle("GeekTech")
                .setAutoCancel(false)
                .setContentText("Офис открыт")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Офис открыт"))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }

    public static void CreateNatificationClose(Context context) {

        CreateNotificationChannel(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.logo_geek)
                .setContentTitle("GeekTech")
                .setAutoCancel(false)
                .setContentText("Офис закрыт")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Офис закрыт"))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }


    private static void CreateNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "test title";
            String description = "test desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
