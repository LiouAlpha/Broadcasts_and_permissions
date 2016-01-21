package jp.co.liou.broadcasts_and_permissions;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = MyBroadcastReceiver.class.getSimpleName();

    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 受信したBroadcastの詳細なデータはintent.getExtras()で取得できる。ここでは実装していません。
        Log.d(TAG, intent.getAction());

        int notificationId = 001;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(TAG);
        builder.setContentText(intent.getAction());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(notificationId, builder.build());
    }
}
