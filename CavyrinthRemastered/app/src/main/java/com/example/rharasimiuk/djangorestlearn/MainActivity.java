package com.example.rharasimiuk.djangorestlearn;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ParseTask().execute();
    }

    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {

                String site_url_json = "https://mundial2018.000webhostapp.com/getDepotByID.php?id=5000";
                URL url = new URL(site_url_json);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }


        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            try {
                JSONObject jsonobj = new JSONObject(strJson);
                JSONArray jsonarray = jsonobj.getJSONArray("result");
                JSONObject jsonobj2 = jsonarray.getJSONObject(1);

                TextView textView = (TextView)findViewById(R.id.showtext);

                StringBuilder statistics = new StringBuilder("");
                statistics.append("Name: " + jsonobj2.getString("name") + "\n");
                statistics.append("Type: " + jsonobj2.getString("type") + "\n");
                statistics.append("Description: " + jsonobj2.getString("description") + "\n");
                statistics.append("Quantity: " + jsonobj2.getString("quantity") + "\n");
                statistics.append("Weight: " + jsonobj2.getString("weight") + "\n");
                statistics.append("Price: " + jsonobj2.getString("price") + "\n");
                statistics.append("Image: " + jsonobj2.getString("image") + "\n");
                statistics.append("ID: " + jsonobj2.getString("id_item") + "\n");
                textView.setText(statistics);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}