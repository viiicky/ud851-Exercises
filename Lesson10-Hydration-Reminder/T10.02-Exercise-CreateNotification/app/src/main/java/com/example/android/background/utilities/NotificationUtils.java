package com.example.android.background.utilities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

import static android.app.Notification.DEFAULT_VIBRATE;
import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.graphics.BitmapFactory.decodeResource;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;
import static android.os.Build.VERSION_CODES.O;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;
import static android.support.v4.content.ContextCompat.getColor;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    private static final int WATER_REMINDER_PENDING_INTENT_ID = 1000;

    private static final String WATER_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";

    private static final int WATER_REMINDER_NOTIFICATION_ID = 3000;

    public static void remindUserBecauseCharging(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (SDK_INT >= O) {
            NotificationChannel mNotificationChannel = new NotificationChannel(WATER_REMINDER_NOTIFICATION_CHANNEL_ID, context.getString(R.string.main_notification_channel_name), IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mNotificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, WATER_REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        if (SDK_INT >= JELLY_BEAN && SDK_INT < O) {
            notificationBuilder.setPriority(PRIORITY_HIGH);
        }

        notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, notificationBuilder.build());

    }

    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, WATER_REMINDER_PENDING_INTENT_ID, intent, FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon (Context context) {
        return decodeResource(context.getResources(), R.drawable.ic_local_drink_black_24px);
    }
}
