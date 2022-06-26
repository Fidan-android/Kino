package com.example.kino.tasks;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AllTheatreGetTask extends AsyncTask<Void, Void, ArrayList<String[]>> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<String[]> doInBackground(Void... params) {
        ArrayList<String[]> res = new ArrayList<>();
        HttpURLConnection myConnection = null;
        try {
            URL mySite = new URL("http://a91745zj.beget.tech/kino/?id=3");
            myConnection =
                    (HttpURLConnection) mySite.openConnection();
            myConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        try {
            i = myConnection.getResponseCode();
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
            InputStreamReader responseBodyReader = null;
            try {
                responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            JsonReader jsonReader;
            jsonReader = null;
            jsonReader = new JsonReader(responseBodyReader);
            try {
                jsonReader.beginArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String key = null;
            String value = null;

            while (true) {
                try {
                    if (!jsonReader.hasNext()) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    jsonReader.beginObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
                String[] str = new String[2];
                int n = 0;
                while (true) {
                    try {
                        if (!jsonReader.hasNext()) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        key = jsonReader.nextName();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                        sb.append("\r\n : " +key);
                    try {
                        value = jsonReader.nextString();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
//                        sb.append("\r\n : " +value);
                    str[n] = value;
                    n++;
                }
                try {
                    jsonReader.endObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                res.add(str);
            }
            try {
                jsonReader.endArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myConnection.disconnect();

        return res;
    }

    @Override
    protected void onPostExecute(ArrayList<String[]> result) {
        super.onPostExecute(result);
    }
}
