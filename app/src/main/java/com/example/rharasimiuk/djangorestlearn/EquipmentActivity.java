package com.example.rharasimiuk.djangorestlearn;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EquipmentActivity extends AppCompatActivity {

    String player_name, player_id;

    int player_gold, player_capacity, player_eq_helmet, player_eq_armor, player_eq_legs,
            player_eq_boots, player_eq_weapon, player_eq_shield, player_eq_ring_left,
            player_eq_ring_right, player_eq_amulet, player_health_potions, player_mana_potions;

    int[] eq_items = new int[9];

    ProgressDialog loading;

    public String[] eq_images, eq_descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        loadGame();
        eq_items[0] = player_eq_helmet;
        eq_items[1] = player_eq_armor;
        eq_items[2] = player_eq_legs;
        eq_items[3] = player_eq_boots;
        eq_items[4] = player_eq_weapon;
        eq_items[5] = player_eq_shield;
        eq_items[6] = player_eq_ring_left;
        eq_items[7] = player_eq_ring_right;
        eq_items[8] = player_eq_amulet;
        new EquipmentActivity.ParseTask().execute();
        loading = ProgressDialog.show(EquipmentActivity.this, "Please wait...", "Loading...", false, false);

        fillEmptyTiles();
        ImageView health_potions_img = (ImageView) findViewById(R.id.imageViewHp);
        ImageView mana_potions_img = (ImageView) findViewById(R.id.imageViewMp);

        TextView hp = (TextView) findViewById(R.id.textViewHp);
        TextView mp = (TextView) findViewById(R.id.textViewMp);

        hp.setText(Integer.toString(player_health_potions)+"   ");
        mp.setText(Integer.toString(player_mana_potions) + "   ");
        if (player_health_potions > 0) {
            health_potions_img.setImageResource(getResources().getIdentifier("h_potion", "mipmap", "com.example.rharasimiuk.djangorestlearn"));
        }
        if (player_mana_potions > 0) {
            mana_potions_img.setImageResource(getResources().getIdentifier("m_potion", "mipmap", "com.example.rharasimiuk.djangorestlearn"));
        }
        //getEq();
    }

    public void fillEmptyTiles() {
        final GridView grid = (GridView) findViewById(R.id.gridView);
        grid.setAdapter(new ImageAdapter(this));

        for (int i = 0; i < 40; i++) {
            ((ImageAdapter) grid.getAdapter()).changeImage(i, R.mipmap.empty);
        }
    }

    public void imageClickHelmet(View view) {

    }

    public void imageClickArmor(View view) {

    }

    public void imageClickLegs(View view) {

    }

    public void imageClickBoots(View view) {

    }

    public void imageClickWeapon(View view) {

    }

    public void imageClickShield(View view) {

    }

    public void imageClickRingLeft(View view) {

    }

    public void imageClickRingRight(View view) {

    }

    public void imageClickAmulet(View view) {

    }

    public void imageClickHp(View view) {

    }

    public void imageClickMp(View view) {

    }

    //Action after tapping Back Key
    @Override
    public void onBackPressed() {

        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setMessage("Are you sure you want to exit the application?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveEquipment();
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

    public void back(View view) {

        //saveEquipment();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        ImageView helmet_img = (ImageView) findViewById(R.id.imageViewHelmet);
        ImageView armor_img = (ImageView) findViewById(R.id.imageViewArmor);
        ImageView legs_img = (ImageView) findViewById(R.id.imageViewLegs);
        ImageView boots_img = (ImageView) findViewById(R.id.imageViewBoots);
        ImageView weapon_img = (ImageView) findViewById(R.id.imageViewWeapon);
        ImageView shield_img = (ImageView) findViewById(R.id.imageViewShield);
        ImageView amulet_img = (ImageView) findViewById(R.id.imageViewAmulet);
        ImageView ring_left_img = (ImageView) findViewById(R.id.imageViewRingLeft);
        ImageView ring_right_img = (ImageView) findViewById(R.id.imageViewRingRight);
        String[] eq_images = new String[9];
        String[] eq_descriptions = new String[9];

        @Override
        protected String doInBackground(Void... params) {
            try {
                String site_url_json = "https://mundial2018.000webhostapp.com/getItemsImage.php";
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
                int j = 0;

                for (int i = 0;i < jsonarray.length();i++){
                    JSONObject jsonobj2 = jsonarray.getJSONObject(i);
                    if (jsonobj2.getString("id_item").equals(String.valueOf(eq_items[j]))){
                        if (jsonobj2.getString("image") == null) {
                            eq_images[j] = "null";
                        }else{
                            eq_images[j] = jsonobj2.getString("image");
                        }
                        eq_descriptions[j] = jsonobj2.getString("description");
                        j++;
                        i = 0;
                    }
                    if (j == 9) {
                        break;
                    }

                }
                loading.dismiss();
                if (!eq_images[0].equals("null")) {
                    helmet_img.setImageResource(getResources().getIdentifier(eq_images[0], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }
                if (!eq_images[1].equals("null")) {
                    armor_img.setImageResource(getResources().getIdentifier(eq_images[1], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }
                if (!eq_images[2].equals("null")) {
                    legs_img.setImageResource(getResources().getIdentifier(eq_images[2], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }
                if (!eq_images[3].equals("null")) {
                    boots_img.setImageResource(getResources().getIdentifier(eq_images[3], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }
                if (!eq_images[4].equals("null")) {
                    weapon_img.setImageResource(getResources().getIdentifier(eq_images[4], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }
                if (!eq_images[5].equals("null")) {
                    shield_img.setImageResource(getResources().getIdentifier(eq_images[5], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }
                if (!eq_images[6].equals("null")) {
                    ring_left_img.setImageResource(getResources().getIdentifier(eq_images[6], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }
                if (!eq_images[7].equals("null")) {
                    ring_right_img.setImageResource(getResources().getIdentifier(eq_images[7], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }
                if (!eq_images[8].equals("null")) {
                    amulet_img.setImageResource(getResources().getIdentifier(eq_images[8], "mipmap", "com.example.rharasimiuk.djangorestlearn"));
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    //Load game
    public void loadGame() {

        SharedPreferences loadE = getSharedPreferences("Save", MODE_PRIVATE);
        player_id = loadE.getString("id_player", "");
        player_name = loadE.getString("player_name", "");
        player_gold = loadE.getInt("player_gold", 0);
        player_capacity = loadE.getInt("player_eq_capacity", 0);
        player_eq_helmet = loadE.getInt("player_eq_helmet", 0);
        player_eq_armor = loadE.getInt("player_eq_armor", 0);
        player_eq_legs = loadE.getInt("player_eq_legs", 0);
        player_eq_boots = loadE.getInt("player_eq_boots", 0);
        player_eq_weapon = loadE.getInt("player_eq_weapon", 0);
        player_eq_shield = loadE.getInt("player_eq_shield", 0);
        player_eq_ring_left = loadE.getInt("player_eq_ring_left", 0);
        player_eq_ring_right = loadE.getInt("player_eq_ring_right", 0);
        player_eq_amulet = loadE.getInt("player_eq_amulet", 0);
        player_health_potions = loadE.getInt("player_health_potions", 0);
        player_mana_potions = loadE.getInt("player_mana_potions", 0);

    }
}
