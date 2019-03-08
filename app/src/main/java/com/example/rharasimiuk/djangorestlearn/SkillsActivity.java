package com.example.rharasimiuk.djangorestlearn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SkillsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        TextView textViewSP = (TextView) findViewById(R.id.textViewSP);
        textViewSP.setText("Skill points: 0");
    }

    //Reset skill points
    public void resetSp(View view) {

    }

    public void back(View view) {

        //saveSkills();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    public void addHp(View view) {

    }
    public void addMana(View view) {

    }

    public void addAtk(View view) {

    }

    public void addDef(View view) {

    }

    public void addCrit(View view) {

    }

    public void addCritChance(View view) {

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setMessage("Are you sure you want to exit the application?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveSkills();
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
