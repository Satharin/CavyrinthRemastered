package com.example.rharasimiuk.djangorestlearn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    int player_gold, player_health_potions, player_mana_potions, toPay, quantityHP, quantityMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }

    public void back(View view) {

        //saveShop();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    public void leftHealthPotion(View view) {

        TextView textViewQuantityHP = (TextView) findViewById(R.id.textViewQuantityHP);
        quantityHP = Integer.parseInt(textViewQuantityHP.getText().toString());

        if (quantityHP > 0 && quantityHP < 101) {

            textViewQuantityHP.setText(Integer.toString(quantityHP - 1));

        }

        check();

    }

    public void rightHealthPotion(View view) {

        TextView textViewQuantityHP = (TextView) findViewById(R.id.textViewQuantityHP);
        quantityHP = Integer.parseInt(textViewQuantityHP.getText().toString());

        if (quantityHP >= 0 && quantityHP < 100) {
            textViewQuantityHP.setText(Integer.toString(quantityHP + 1));
        }

        check();

    }

    public void leftManaPotion(View view) {

        TextView textViewQuantityMP = (TextView) findViewById(R.id.textViewQuantityMP);
        quantityMP = Integer.parseInt(textViewQuantityMP.getText().toString());

        if (quantityMP > 0 && quantityMP < 101) {
            textViewQuantityMP.setText(Integer.toString(quantityMP - 1));
        }

        check();

    }

    public void rightManaPotion(View view) {

        TextView textViewQuantityMP = (TextView) findViewById(R.id.textViewQuantityMP);
        quantityMP = Integer.parseInt(textViewQuantityMP.getText().toString());

        if (quantityMP >= 0 && quantityMP < 100) {
            textViewQuantityMP.setText(Integer.toString(quantityMP + 1));
        }

        check();

    }

    public void check() {

        Button leftHP = (Button) findViewById(R.id.buttonLeftHP);
        Button rightHP = (Button) findViewById(R.id.buttonRightHP);
        Button leftMP = (Button) findViewById(R.id.buttonLeftMP);
        Button rightMP = (Button) findViewById(R.id.buttonRightMP);
        Button buy = (Button) findViewById(R.id.buttonBuy);

        TextView textViewQuantityHP = (TextView) findViewById(R.id.textViewQuantityHP);
        quantityHP = Integer.parseInt(textViewQuantityHP.getText().toString());

        TextView textViewQuantityMP = (TextView) findViewById(R.id.textViewQuantityMP);
        quantityMP = Integer.parseInt(textViewQuantityMP.getText().toString());

        if (quantityHP > 0 || quantityMP > 0) {
            buy.setClickable(true);
            buy.setEnabled(true);
        } else {
            buy.setClickable(false);
            buy.setEnabled(false);
        }

        if (quantityHP == 0) {
            leftHP.setClickable(false);
            leftHP.setEnabled(false);
        } else {
            leftHP.setClickable(true);
            leftHP.setEnabled(true);
        }

        if (quantityHP == 99) {
            rightHP.setClickable(false);
            rightHP.setEnabled(false);
        } else {
            rightHP.setClickable(true);
            rightHP.setEnabled(true);
        }

        if (quantityMP == 0) {
            leftMP.setClickable(false);
            leftMP.setEnabled(false);
        } else {
            leftMP.setClickable(true);
            leftMP.setEnabled(true);
        }

        if (quantityMP == 99) {
            rightMP.setClickable(false);
            rightMP.setEnabled(false);
        } else {
            rightMP.setClickable(true);
            rightMP.setEnabled(true);
        }

        toPay();

    }

    public void refresh() {

        TextView textViewGold = (TextView) findViewById(R.id.textViewGold);
        TextView textViewQuantityHP = (TextView) findViewById(R.id.textViewQuantityHP);
        TextView textViewQuantityMP = (TextView) findViewById(R.id.textViewQuantityMP);

        textViewGold.setText("Gold: " + Integer.toString(player_gold));
        textViewQuantityHP.setText("0");
        textViewQuantityMP.setText("0");

    }

    public void toPay() {

        TextView textViewHP = (TextView) findViewById(R.id.textViewHP);
        int hpPrice = Integer.parseInt(textViewHP.getText().toString());

        TextView textViewMP = (TextView) findViewById(R.id.textViewMP);
        int mpPrice = Integer.parseInt(textViewMP.getText().toString());

        TextView textViewQuantityHP = (TextView) findViewById(R.id.textViewQuantityHP);
        quantityHP = Integer.parseInt(textViewQuantityHP.getText().toString());

        TextView textViewQuantityMP = (TextView) findViewById(R.id.textViewQuantityMP);
        quantityMP = Integer.parseInt(textViewQuantityMP.getText().toString());

        TextView textViewFinalPrice = (TextView) findViewById(R.id.textViewFinalPrice);
        toPay = (hpPrice * quantityHP) + (mpPrice * quantityMP);
        textViewFinalPrice.setText("To pay: " + Integer.toString(toPay));

    }

    public void buy(View view) {

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setMessage("Are you sure you want to exit the application?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveShop();
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
