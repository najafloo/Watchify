package com.shahpar.watchify;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.shahpar.watchify.translateor.Language;
import com.shahpar.watchify.translateor.TranslateAPI;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationListener extends NotificationListenerService {

    private String TAG = "SHAHPAR";
    private final static String DESTINATION_LANGUAGE = "english";

    private PowerManager m_powerManager;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder = null;
    private NotificationCompat.Builder notification = null;
    private NotificationCompat.Builder progressBuilder = null;
    private NotificationChannel mChannel;
    private NotificationChannel channelLow;
    private NotificationChannel channelHigh;
    private NotificationChannel groupChannel;
    private int mCurrentId = 0;
    private int mClearId = 0;
    private String desLanguage = Language.ENGLISH;
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    CharSequence csTitle;
    CharSequence csText;

    final Handler mHandler = new Handler();

    String mapString(String input) {
        String out = input;

        out = out.replace("ُ", "o");
        out = out.replace("َ", "a");
        out = out.replace("ٍِ", "e");
        out = out.replace("ٌ", "o");
        out = out.replace("ً", "an");
        out = out.replace("اً", "an");
        out = out.replace("أ", "a");
        out = out.replace("إ", "a");
        out = out.replace("ا", "a");
        out = out.replace("آ", "a");
        out = out.replace("ب", "b");
        out = out.replace("پ", "p");
        out = out.replace("ت", "t");
        out = out.replace("تَ", "ta");
        out = out.replace("تِ", "te");
        out = out.replace("تُ", "to");
        out = out.replace("ث", "s");
        out = out.replace("ج", "j");
        out = out.replace("چ", "ch");
        out = out.replace("ح", "h");
        out = out.replace("خ", "kh");
        out = out.replace("د", "d");
        out = out.replace("ذ", "z");
        out = out.replace("ر", "r");
        out = out.replace("ز", "z");
        out = out.replace("ژ", "zh");
        out = out.replace("س", "s");
        out = out.replace("ش", "sh");
        out = out.replace("ص", "s");
        out = out.replace("ض", "z");
        out = out.replace("ط", "t");
        out = out.replace("ﻁْ", ".");
        out = out.replace("ظ", "z");
        out = out.replace("ع", "a");
        out = out.replace("غ", "gh");
        out = out.replace("ف", "f");
        out = out.replace("ق", "gh");
        out = out.replace("ک", "k");
        out = out.replace("ك", "k");
        out = out.replace("گ", "g");
        out = out.replace("ل", "l");
        out = out.replace("م", "m");
        out = out.replace("ن", "n");
        out = out.replace("و", "v");
        out = out.replace("ؤ", "o");
        out = out.replace("ه", "h");
        out = out.replace("ۀ", "a");
        out = out.replace("ة", "h");
        out = out.replace("ی", "y");
        out = out.replace("ي", "y");
        out = out.replace("ئ", "a");
        out = out.replace("ء", "a");
        out = out.replace("﷼", " Rial ");

        out = out.replace("۰", "0");
        out = out.replace("۱", "1");
        out = out.replace("۲", "2");
        out = out.replace("۳", "3");
        out = out.replace("۴", "4");
        out = out.replace("۵", "5");
        out = out.replace("۶", "6");
        out = out.replace("۷", "7");
        out = out.replace("۸", "8");
        out = out.replace("۹", "9");

        out = out.replace(" ", " ");
        out = out.replace("؟", "?");
        out = out.replace("٪", "%");
        out = out.replace("،", ",");
        out = out.replace(".", ".");
        out = out.replace("؛", ";");
        out = out.replace("ـ", "-");
        out = out.replace("؍", "/");
        out = out.replace("×", "*");
        out = out.replace("«", "<<");
        out = out.replace("»", ">>");

        out = out.replace("☺️", " :) ");
        out = out.replace("🙂", " :) ");
        out = out.replace("😊", " :) ");
        out = out.replace("😀", " :) ");
        out = out.replace("😁", " :) ");
        out = out.replace("🙃", " (: ");
        out = out.replace("🤗", " :)| ");
        out = out.replace("😃", " :D ");
        out = out.replace("😄", " :D ");
        out = out.replace("😆", " :D ");
        out = out.replace("😍", " :D ");
        out = out.replace("😅", " ':D ");
        out = out.replace("☹️", " :( ");
        out = out.replace("🙁", " :( ");
        out = out.replace("😠", " :( ");
        out = out.replace("😡", " :( ");
        out = out.replace("😞", " :( ");
        out = out.replace("😟", " :( ");
        out = out.replace("😣", " :( ");
        out = out.replace("😖", " :( ");
        out = out.replace("😬", " :| ");
        out = out.replace("😢", " :'( ");
        out = out.replace("😭", " :'( ");
        out = out.replace("😂", " :') ");
        out = out.replace("😨", " D: ");
        out = out.replace("😧", " D: ");
        out = out.replace("😦", " D: ");
        out = out.replace("😱", " D: ");
        out = out.replace("😫", " D: ");
        out = out.replace("😩", " D: ");
        out = out.replace("😮", " :O ");
        out = out.replace("😯", " :O ");
        out = out.replace("😲", " :O ");
        out = out.replace("😗", " :* ");
        out = out.replace("😙", " :* ");
        out = out.replace("😚", " :* ");
        out = out.replace("😘", " :* ");
        out = out.replace("😍", " :* ");
        out = out.replace("😉", " ;) ");
        out = out.replace("😜", " ;) ");
        out = out.replace("😘", " ;) ");
        out = out.replace("😛", " :P ");
        out = out.replace("😝", " :P ");
        out = out.replace("😜", " :P ");
        out = out.replace("🤑", " :P ");
        out = out.replace("😜", " ;P ");
        out = out.replace("🤔", " :/ ");
        out = out.replace("😕", " :/ ");
        out = out.replace("😟", " :/ ");
        out = out.replace("😐", " :| ");
        out = out.replace("😑", " :| ");
        out = out.replace("😳", " :$| ");
        out = out.replace("😞", " :$| ");
        out = out.replace("😖", " :$| ");
        out = out.replace("🤬", " :$ ");
        out = out.replace("🤐", " :X ");
        out = out.replace("😶", " :X ");
        out = out.replace("😇", " O:) ");
        out = out.replace("👼", " O:) ");
        out = out.replace("😈", " }:) ");
        out = out.replace("👹", " }:) ");
        out = out.replace("👺", " }:) ");
        out = out.replace("😎", " |;) ");
        out = out.replace("😪", " |;) ");
        out = out.replace("😏", " :J ");
        out = out.replace("😒", " :J ");
        out = out.replace("😵", " %) ");
        out = out.replace("😕", " %) ");
        out = out.replace("🤕", " %) ");
        out = out.replace("🤒", " :###. ");
        out = out.replace("😷", " :###. ");
        out = out.replace("🤢", " :###. ");
        out = out.replace("🌹", " @};- ");
        out = out.replace("🎅", " *<|:) ");
        out = out.replace("💔", " </3 ");
        out = out.replace("❤️", " <3 ");
        out = out.replace("💜", " <3 ");
        out = out.replace("💙", " <3 ");
        out = out.replace("💚", " <3 ");
        out = out.replace("💛", " <3 ");
        out = out.replace("❣", " <3 ");
        out = out.replace("💕", " <3 ");
        out = out.replace("💞", " <3 ");
        out = out.replace("💓", " <3 ");
        out = out.replace("💗", " <3 ");
        out = out.replace("💖", " <3 ");
        out = out.replace("💝", " <3 ");
        out = out.replace("💘", " </3 ");
        out = out.replace("🍆", " _|_ ");
        out = out.replace("🖕", " _|_ ");
        out = out.replace("🌮", " (|) ");
        out = out.replace("🍑", " (() ");
        out = out.replace("🍪", " (*) ");
        out = out.replace("🍩", " (O) ");
        out = out.replace("💩", " |$| ");
        out = out.replace("☠", " OX ");
        out = out.replace("💀", " OX ");
        out = out.replace("🙏", " || ");
        out = out.replace("👌", " OK ");
        out = out.replace("👍", " OK ");
        out = out.replace("🖐", " VV/ ");
        out = out.replace("✌", " V- ");
        return out;
    }

    @Override
    public void onCreate() {
        m_powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mCurrentId = 0;
        mClearId = 0;
        mNotificationManager.cancelAll();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null && intent.getExtras() != null) {
            boolean isEnglish = intent.getBooleanExtra(DESTINATION_LANGUAGE, true);
            setDestinationLanguage(isEnglish);
            Log.d(TAG, "!!!!!!!!!!!!!!!!!!!!! onStartCommand isEnglish = " + isEnglish);
        }

        return START_STICKY;
    }

    private void setDestinationLanguage(boolean isEnglish) {
        if(isEnglish)
            desLanguage = Language.ENGLISH;
        else
            desLanguage = Language.GERMAN;
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        Log.d(TAG, "@@@@@@@@@@@@@@@@@@@@@@@@ onNotificationPosted: ");

        String pack = sbn.getPackageName();
        String ticker = "";
        Bundle extras = sbn.getNotification().extras;

        if (extras == null || pack.contains("com.shahpar.watchify") || !sbn.isClearable())
            return;

        csTitle = extras.getCharSequence(Notification.EXTRA_TITLE);
        csText = extras.getCharSequence(Notification.EXTRA_TEXT);
        if (csTitle == null || csText == null)
            return;

        TranslateAPI translateApi = new TranslateAPI(Language.AUTO_DETECT, desLanguage, csText.toString());
        translateApi.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                Log.d(TAG, "==================== onSuccess: " + translatedText);
                String title = "";
                String text = "";

                title = mapString(csTitle.toString());
                text = mapString("\n" + translatedText);
            }

            @Override
            public void onFailure(String ErrorText) {
                String title = "";
                String text = "";

                Log.d("SHAHPAR", "Error on listerner");

                title = mapString(csTitle.toString());
                text = mapString(csText.toString());

                notifyMessage(title, text);
            }
        });
    }

    @SuppressLint("RestrictedApi")
    public void notifyMessage(String sender, String content) {
        Log.d(TAG, "######################## notifyMessage: ");

        if (sender.equals("") || content.equals(""))
            return;

        Log.i(TAG, "######## :" + sender + "\n" + content);

        if (mBuilder == null) {
            String channelId = getPackageName();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                if (mChannel == null) {
                    mChannel = mNotificationManager.getNotificationChannel(channelId);
                    if (mChannel == null) {
                        mChannel = new NotificationChannel(channelId, "NT", importance);
                        mChannel.setDescription(content);
                        mNotificationManager.createNotificationChannel(mChannel);
                    }
                }
            }

            mBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                mBuilder.setChannelId(channelId);
            else
                mBuilder.setPriority(Notification.PRIORITY_HIGH);

            mBuilder.setSmallIcon(R.drawable.icon) // required
                    .setAutoCancel(true)
                    .setGroup("Group")
                    .setGroupSummary(true)
                    .setColor(0xff2196F3);
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
                    mHandler.post(new Runnable() {
                        public void run() {
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
                        }
                    });
                }
            };
            mTimer.schedule(mTimerTask, 1000, 1000);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
//        super.onNotificationRemoved(sbn);
    }
}
