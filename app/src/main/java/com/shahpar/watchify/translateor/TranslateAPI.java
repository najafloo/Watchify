package com.shahpar.watchify.translateor;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslateAPI {

    private TranslateListener listener;

    final static String TAG = "SHAHPAR";
    private String resp = null;
    private String url = null;
    private String sourceLanguage = Language.AUTO_DETECT;
    private String destinationLanguage = Language.ENGLISH;
    private String word = null;

    public TranslateAPI() {
    }

    public void setDestinationLanguage(String destinationLanguage) {
        this.destinationLanguage = destinationLanguage;
    }

    public void translate(String text, TranslateListener listener) {
        this.word = text;
        this.listener = listener;
        AsyncTranslator asyncTranslator = new AsyncTranslator();
        asyncTranslator.execute();
    }

    class AsyncTranslator extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                url = "https://translate.googleapis.com/translate_a/single?" + "client=gtx&" + "sl=" +
                        sourceLanguage + "&tl=" + destinationLanguage + "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                resp = response.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            String temp = "";

            if (resp == null) {
                listener.onFailure("Network Error");
            } else {
                try {
//                    Log.d(TAG, "onPostExecute: response = " + resp);
                    JSONArray main = new JSONArray(resp);
                    JSONArray total = (JSONArray) main.get(0);
                    for (int i = 0; i < total.length(); i++) {
                        JSONArray currentLine = (JSONArray) total.get(i);
                        temp = temp + currentLine.get(0).toString();
                    }
                    Log.d(TAG, "onPostExecute: " + temp);

                    if (temp.length() > 2) {
                        listener.onSuccess(temp);
                    } else {
                        listener.onFailure("Invalid Input String");
                    }
                } catch (JSONException e) {
                    listener.onFailure(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
    }
}
