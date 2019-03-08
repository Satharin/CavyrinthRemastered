package com.example.rharasimiuk.djangorestlearn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

public class EquipmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        fillEmptyTiles();

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
}
