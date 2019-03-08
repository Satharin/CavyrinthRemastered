package com.example.rharasimiuk.djangorestlearn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
    }

    //Go to MainActivity
    public void escape(View view) {
        //saveGame();
        //saveSkills();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        //afterRun = 1;
        //FightActivity.dataPacketFight.putInt("afterRun", afterRun);
        finish();
    }

    public void spell(View view) {

    }

    public void heal(View view) {

    }

    public void explore(View view) {

    }

    public void startFight(View view) {

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setMessage("Are you sure you want to exit the application?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveSkills();
                        //saveGame();
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
