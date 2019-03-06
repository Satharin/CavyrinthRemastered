package com.example.rharasimiuk.djangorestlearn;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    //Character statistics and equipment
    String player_name, id_player;
    int player_hp, player_mp, player_level, player_magic_level, player_experience, player_gold,
            player_capacity, player_attack, player_defence, player_critical, player_critical_chance,
            player_skill_points, player_magic_experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ParseTask().execute();
    }

    //Display character's statistics
    public void statistics(View view) {
        getPlayerIdStats();
    }

    public void getPlayerIdStats(){
        TextView textView = (TextView)findViewById(R.id.showtext);
        StringBuilder statistics = new StringBuilder("");

        statistics.append("Name: " + player_name + "\n");
        statistics.append("Health points: " + player_hp + "\n");
        statistics.append("Mana points: " + player_mp + "\n");
        statistics.append("Level: " + player_level + "\n");
        statistics.append("Magic level: " + player_magic_level + "\n");
        statistics.append("Attack: " + player_attack + "\n");
        statistics.append("Defence: " + player_defence + "\n");
        //statistics.append("Armor: " + jsonobj2.getString("name") + "\n");
        statistics.append("Experience: " + player_experience + "\n");
        statistics.append("Gold: " + player_gold);
        textView.setText(statistics);
    }

    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                String site_url_json = "https://mundial2018.000webhostapp.com/getPlayerID.php?name=Satharin";
                URL url = new URL(site_url_json);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();

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
                //JSONObject jsonobj2 = jsonarray.getJSONObject(1);

                for (int i = 0;i < jsonarray.length();i++){
                    JSONObject jsonobj2 = jsonarray.getJSONObject(i);
                    if (jsonobj2.getString("name").equals("Satharin")){
                        player_name = jsonobj2.getString("name");
                        player_hp = jsonobj2.getInt("hp");
                        player_mp = jsonobj2.getInt("mp");
                        player_level = jsonobj2.getInt("level");
                        player_magic_level = jsonobj2.getInt("magic_level");
                        player_attack = jsonobj2.getInt("attack");
                        player_defence = jsonobj2.getInt("defence");
                        player_experience = jsonobj2.getInt("experience");
                        player_gold = jsonobj2.getInt("gold");
                        break;
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}