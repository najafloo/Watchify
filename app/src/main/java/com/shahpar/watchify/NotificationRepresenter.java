package com.shahpar.watchify;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationRepresenter {

    private final NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder = null;
    private NotificationChannel mChannel;
    private int mClearId = 0;
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private int mCurrentId = 0;
    final Handler mHandler = new Handler();

    public NotificationRepresenter() {

        mNotificationManager = (NotificationManager) MyApplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        mCurrentId = 0;
        mClearId = 0;
    }

    @SuppressLint("RestrictedApi")
    public void notifyMessage(String sender, String content) {
        if (sender.equals("") || content.equals(""))
            return;

        if (mBuilder == null) {
            String channelId = MyApplication.getContext().getPackageName();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                if (mChannel == null) {
                    mChannel = mNotificationManager.getNotificationChannel(channelId);
                    if (mChannel == null) {
                        mChannel = new NotificationChannel(channelId, "Nt", importance);
                        mChannel.setDescription(content);
                        mNotificationManager.createNotificationChannel(mChannel);
                    }
                }
            }

            mBuilder = new NotificationCompat.Builder(MyApplication.getContext(), channelId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                mBuilder.setChannelId(channelId);
            else
                mBuilder.setPriority(Notification.PRIORITY_HIGH);

            mBuilder.setSmallIcon(R.drawable.icon_notif) // required
                    .setAutoCancel(true)
                    .setGroup("Group")
                    .setColor(0xff3700B3);
        }

        int defaults = 0;

        mBuilder.mActions.clear();
        mBuilder.setContentTitle(sender)
                .setDefaults(defaults);

        mBuilder.setContentText(content);

        mNotificationManager.notify(++mCurrentId, mBuilder.build());

        if (mTimer == null) {
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                public void run() {
                    mHandler.post(() -> {
                        if (mClearId < mCurrentId) {
                            mNotificationManager.cancel(++mClearId);
                        } else {
                            mCurrentId = 0;
                            mClearId = 0;
                            if (mTimer != null) {
                                mTimer.cancel();
                                mTimer = null;
                                mTimerTask = null;
                            }
                        }
                    });
                }
            };
            mTimer.schedule(mTimerTask, 2000, 1000);
        }
    }
}
