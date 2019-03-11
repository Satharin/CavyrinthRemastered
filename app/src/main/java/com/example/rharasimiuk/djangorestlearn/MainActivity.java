package com.example.rharasimiuk.djangorestlearn;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Character statistics and equipment
    String player_name, player_id;

    int player_hp, player_mp, player_level, player_magic_level, player_experience, player_gold,
            player_capacity, player_attack, player_defence, player_critical, player_critical_chance,
            player_skill_points, player_magic_experience, player_eq_helmet, player_eq_armor, player_eq_legs,
            player_eq_boots, player_eq_weapon, player_eq_shield, player_eq_ring_left, player_eq_ring_right,
            player_eq_amulet, player_used_hp_sp, player_used_mp_sp, player_used_attack_sp,
            player_used_defence_sp, player_used_critical_sp, player_used_critical_chance_sp,
            player_health_potions, player_mana_potions;

    ProgressDialog loading;
    ArrayList<Integer> expTable = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ParseTask().execute();
        loading = ProgressDialog.show(MainActivity.this, "Please wait...", "Loading...", false, false);
    }

    //Go to hunt activity
    public void hunt(View view) {

        //Clear textView from MainActivity
        TextView mainActivity = (TextView) findViewById(R.id.showtext);
        mainActivity.clearComposingText();
        mainActivity.scrollTo(0, 0);

        startActivity(new Intent(getApplicationContext(), HuntActivity.class));
        finish();

    }

    //Go to quests activity
    public void quests(View view) {

        //Clear textView from MainActivity
        TextView mainActivity = (TextView) findViewById(R.id.showtext);
        mainActivity.clearComposingText();
        mainActivity.scrollTo(0, 0);

        startActivity(new Intent(getApplicationContext(), QuestsActivity.class));
        finish();

    }

    //Go to equipment activity
    public void equipment(View view) {

        //Clear textView from MainActivity
        TextView mainActivity = (TextView) findViewById(R.id.showtext);
        mainActivity.clearComposingText();
        mainActivity.scrollTo(0, 0);
        saveGame();

        startActivity(new Intent(getApplicationContext(), EquipmentActivity.class));
        finish();

    }

    //Go to shop activity
    public void shop(View view) {

        //Clear textView from MainActivity
        TextView mainActivity = (TextView) findViewById(R.id.showtext);
        mainActivity.clearComposingText();
        mainActivity.scrollTo(0, 0);

        startActivity(new Intent(getApplicationContext(), ShopActivity.class));
        finish();

    }

    //Go to skills activity
    public void skills(View view) {

        //Clear textView from MainActivity
        TextView mainActivity = (TextView) findViewById(R.id.showtext);
        mainActivity.clearComposingText();
        mainActivity.scrollTo(0, 0);

        startActivity(new Intent(getApplicationContext(), SkillsActivity.class));
        finish();

    }

    //Go to depot activity
    public void depot(View view) {

        //Clear textView from MainActivity
        TextView mainActivity = (TextView) findViewById(R.id.showtext);
        mainActivity.clearComposingText();
        mainActivity.scrollTo(0, 0);

        startActivity(new Intent(getApplicationContext(), DepotActivity.class));
        finish();

    }

    //Display character's statistics
    public void statistics(View view) {
        getPlayerIdStats();
    }

    //Show exp table
    public void exptable(View view) {
        TextView mainActivity = (TextView) findViewById(R.id.showtext);
        mainActivity.setMovementMethod(new ScrollingMovementMethod());
        StringBuilder experience = new StringBuilder("");
        fillExperienceTable();

        for (int i = 1; i < expTable.size(); i++) {
            experience.append("Level " + i + ": " + expTable.get(i).toString() + "\n");
            mainActivity.setText(experience);
        }
    }

    //Experience table
    public void fillExperienceTable() {

        for (int i = 0; i < 101; i++){
            if(i == 0 || i == 1) {
                expTable.add(i-1);
            }
            else{
                expTable.add((50*(i*i)-(150*i)+200)+expTable.get(i-1));
            }
        }

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
                        player_id = jsonobj2.getString("id_player");
                        player_hp = jsonobj2.getInt("hp");
                        player_mp = jsonobj2.getInt("mp");
                        player_level = jsonobj2.getInt("level");
                        player_magic_level = jsonobj2.getInt("magic_level");
                        player_attack = jsonobj2.getInt("attack");
                        player_defence = jsonobj2.getInt("defence");
                        player_experience = jsonobj2.getInt("experience");
                        player_gold = jsonobj2.getInt("gold");
                        player_capacity = jsonobj2.getInt("capacity");
                        player_critical = jsonobj2.getInt("critical");
                        player_critical_chance = jsonobj2.getInt("critical_chance");
                        player_eq_helmet = jsonobj2.getInt("eq_helmet");
                        player_eq_armor = jsonobj2.getInt("eq_armor");
                        player_eq_legs = jsonobj2.getInt("eq_legs");
                        player_eq_boots = jsonobj2.getInt("eq_boots");
                        player_eq_weapon = jsonobj2.getInt("eq_weapon");
                        player_eq_shield = jsonobj2.getInt("eq_shield");
                        player_eq_ring_left = jsonobj2.getInt("eq_ring_left");
                        player_eq_ring_right = jsonobj2.getInt("eq_ring_right");
                        player_eq_amulet = jsonobj2.getInt("eq_amulet");
                        player_skill_points = jsonobj2.getInt("skill_points");
                        player_used_hp_sp = jsonobj2.getInt("used_hp_sp");
                        player_used_mp_sp = jsonobj2.getInt("used_mp_sp");
                        player_used_attack_sp = jsonobj2.getInt("used_attack_sp");
                        player_used_defence_sp = jsonobj2.getInt("used_defence_sp");
                        player_used_critical_sp = jsonobj2.getInt("used_critical_sp");
                        player_used_critical_chance_sp = jsonobj2.getInt("used_critical_chance_sp");
                        player_health_potions = jsonobj2.getInt("health_potions");
                        player_mana_potions = jsonobj2.getInt("mana_potions");
                        player_magic_experience = jsonobj2.getInt("magic_experience");
                        loading.dismiss();
                        //saveGame();
                        break;
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    //Check if player really wants to leave the game
    public void exitGame(View view) {

        AlertDialog.Builder exit = new AlertDialog.Builder(this);

        exit.setMessage("Are you sure you want to exit the application?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                    finish();
                     System.exit(0);
                     }
                    })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    }
                    })
                .create();
        exit.show();

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setMessage("Are you sure you want to exit the application?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        exit.show();

    }

    public void saveGame() {

        SharedPreferences saveGame = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor save = saveGame.edit();
        save.putString("player_name", player_name);
        save.putString("player_id", player_id);
        save.putInt("player_hp", player_hp);
        save.putInt("player_mp", player_mp);
        save.putInt("player_level", player_level);
        save.putInt("player_magic_level", player_magic_level);
        save.putInt("player_attack", player_attack);
        save.putInt("player_defence", player_defence);
        save.putInt("player_experience", player_experience);
        save.putInt("player_gold", player_gold);
        save.putInt("player_capacity", player_capacity);
        save.putInt("player_critical", player_critical);
        save.putInt("player_critical_chance", player_critical_chance);
        save.putInt("player_eq_helmet", player_eq_helmet);
        save.putInt("player_eq_armor", player_eq_armor);
        save.putInt("player_eq_legs", player_eq_legs);
        save.putInt("player_eq_boots", player_eq_boots);
        save.putInt("player_eq_weapon", player_eq_weapon);
        save.putInt("player_eq_shield", player_eq_shield);
        save.putInt("player_eq_ring_left", player_eq_ring_left);
        save.putInt("player_eq_ring_right", player_eq_ring_right);
        save.putInt("player_eq_amulet", player_eq_amulet);
        save.putInt("player_skill_points", player_skill_points);
        save.putInt("player_used_hp_sp", player_used_hp_sp);
        save.putInt("player_used_mp_sp", player_used_mp_sp);
        save.putInt("player_used_attack_sp", player_used_attack_sp);
        save.putInt("player_used_defence_sp", player_used_defence_sp);
        save.putInt("player_used_critical_sp", player_used_critical_sp);
        save.putInt("player_used_critical_chance_sp", player_used_critical_chance_sp);
        save.putInt("player_health_potions", player_health_potions);
        save.putInt("player_mana_potions", player_mana_potions);
        save.putInt("player_magic_experience", player_magic_experience);
        save.apply();

    }

}