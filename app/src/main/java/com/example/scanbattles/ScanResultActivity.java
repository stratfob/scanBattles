package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;

import java.util.ArrayList;

public class ScanResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.SCAN_KEY);

        Scan scan = new Scan(message);
        Monster monster = scan.scan();

        String resultString;
        //monster returned by scan
        if(monster != null) {

            ArrayList<Monster> newMonster = (ArrayList<Monster>) AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{monster.id});

            //check to see if owned

            //owned
            if (newMonster.size() != 0) {
                //heal monster
                newMonster.get(0).currentHP = newMonster.get(0).maxHP;
                AppDatabase.getAppDatabase(this).monsterDao().update(newMonster.get(0));
                resultString = "Healed " + newMonster.get(0).name + "!";
            }

            //Check if in User's tribe
            else if (!AppDatabase.getAppDatabase(this).userDao().getTribe().equals(monster.tribe)) {
                fightDialogue(monster);
                resultString = "This monster's tribe is: " + monster.tribe + ", yours is: " + AppDatabase.getAppDatabase(this).userDao().getTribe();
            }

            //not owned
            else {
                //add monster to user monsters
                monster.currentHP = monster.maxHP;
                AppDatabase.getAppDatabase(this).monsterDao().insertAll(monster);
                resultString = "Added " + monster.name + " to your monsters!";
            }

        }

        else {
            resultString = "No monster found!";
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(resultString);

    }

    private void fightDialogue(final Monster monster) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ScanResultActivity.this);
        builder.setTitle("Fight " + monster.tribe + " monster?");
        builder.setPositiveButton("Let's go!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ScanResultActivity.this, FightActivity.class);
                intent.putExtra("monsterID", monster.id);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void MenuOption(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ScanAgainOption(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

}
