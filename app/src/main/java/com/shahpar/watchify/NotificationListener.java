package com.shahpar.watchify;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.shahpar.watchify.translateor.TranslateListener;

import java.util.HashMap;
import java.util.Map;

public class NotificationListener extends NotificationListenerService {

    private String TAG = "SHAHPAR";

    CharSequence csTitle;
    CharSequence csText;
    private String myPackageName;

    private final Map<String, Long> lastNotWhens = new HashMap<>();

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

        out = out.replace("؟", "?");
        out = out.replace("٪", "%");
        out = out.replace("،", ",");
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
        myPackageName = getApplication().getPackageName();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        String pack = sbn.getPackageName();
        Bundle extras = sbn.getNotification().extras;

//        Log.d(TAG, "1 onNotificationPosted: package name : " + pack + ", number = " + sbn.getNotification().number
//                + ", publicVersion = " + sbn.getNotification().publicVersion
//                + ", flags = " + sbn.getNotification().flags
//                + ", when = " + sbn.getNotification().when
//                + ", tickerText = " + sbn.getNotification().tickerText);

        if ((sbn.getNotification().flags & Notification.FLAG_GROUP_SUMMARY) != 0) {
            return;
        }

        Long lastWhen = lastNotWhens.get(sbn.getPackageName());
        if (lastWhen != null && lastWhen >= sbn.getNotification().when)
            return;

        lastNotWhens.put(sbn.getPackageName(), sbn.getNotification().when);

        if (extras == null || pack.contains(myPackageName) || !sbn.isClearable())
            return;

//        Log.d(TAG, "2 onNotificationPosted: "/* + extras.toString()*/);

        csTitle = extras.getCharSequence(Notification.EXTRA_TITLE);
        csText = extras.getCharSequence(Notification.EXTRA_TEXT);

        if (csTitle == null || csText == null)
            return;

        MyApplication.getTranslator().translate(csText.toString(), new TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {

                String title = mapString(csTitle.toString());
                String text = mapString("\n" + translatedText);
                MyApplication.getNotificationRepresnter().notifyMessage(title, text);
            }

            @Override
            public void onFailure(String ErrorText) {

                String title = mapString(csTitle.toString());
                String text = mapString(csText.toString());

                MyApplication.getNotificationRepresnter().notifyMessage(title, text);
            }
        });
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
