package com.example.rharasimiuk.djangorestlearn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

public class DepotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depot);
        fillEmptyTiles();

    }

    public void fillEmptyTiles() {
        final GridView grid = (GridView) findViewById(R.id.depo);
        grid.setAdapter(new ImageAdapter(this));

        for (int i = 0; i < 40; i++) {
            ((ImageAdapter) grid.getAdapter()).changeImage(i, R.mipmap.empty);
            ((ImageAdapter) grid.getAdapter()).changeText(i, "");
        }
    }

    //Display all weapon type items
    public void showWeapon(View view) {

    }

    //Display all shield type items
    public void showShield(View view) {

    }

    //Display all armor type items
    public void showArmor(View view) {

    }

    //Display all jewelerry type items
    public void showJewellery(View view) {

    }

    //Display all craft type items
    public void showCraft(View view) {

    }

    //Display all other type items
    public void showOther(View view) {

    }

    //Display all items
    public void showAll(View view) {

    }

    //Go to MainActivity
    public void back(View view) {

        //saveGold();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    //Action after tapping Back Key
    @Override
    public void onBackPressed() {
        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setMessage("Are you sure you want to exit the application?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveGold();
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
}
