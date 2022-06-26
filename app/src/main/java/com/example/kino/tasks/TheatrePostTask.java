package com.example.kino.tasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TheatrePostTask extends AsyncTask<String, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String line = null;
        String total = null;
        HttpURLConnection myConnection = null;
        try {
            URL mySite = new URL("http://a91745zj.beget.tech/kino/");
            myConnection =
                    (HttpURLConnection) mySite.openConnection();
            myConnection.setRequestMethod("POST");
            myConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            myConnection.setRequestProperty("Accept",
                    "application/vnd.github.v3+json");
            myConnection.setRequestProperty("Contact-Me",
                    "hathibelagal@example.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        myConnection.setDoOutput(true);
        try {
            myConnection.getOutputStream().write(("id=1&name=" + params[0] + "&address=" + params[1]).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        try {
            i = myConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(i);
        try {
            System.out.println(myConnection.getResponseMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (i == 200) {
            InputStream responseBody = null;
            try {
                responseBody = myConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(responseBody));
            while (true) {
                try {
                    if ((line = r.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                total = line;
            }
        }
        return total;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
