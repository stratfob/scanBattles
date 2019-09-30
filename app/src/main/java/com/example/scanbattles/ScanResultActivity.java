package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;

import java.util.ArrayList;
import java.util.List;

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

            //Check if in User's tribe
            if (!AppDatabase.getAppDatabase(this).userDao().getTribe().equals(monster.tribe)) {
                //TODO: make this a battle check
                resultString = "This monster's tribe is: " + monster.tribe + ", yours is: " + AppDatabase.getAppDatabase(this).userDao().getTribe();
            }
            else {


                ArrayList<Monster> newMonster = (ArrayList<Monster>) AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{monster.id});

    //check to see if owned
                //not owned
                if (newMonster.size() == 0) {
                    //add monster to user monsters
                    monster.maxHP = 10; //TODO: make max HP different
                    monster.currentHP = monster.maxHP;
                    monster.level = 1;
                    AppDatabase.getAppDatabase(this).monsterDao().insertAll(monster);
                    resultString = "Added " + monster.name + " to your monsters!";
                }
                //owned
                else {
                    //heal monster
                    newMonster.get(0).currentHP = newMonster.get(0).maxHP;
                    AppDatabase.getAppDatabase(this).monsterDao().update(newMonster.get(0));
                    resultString = "Healed " + newMonster.get(0).name + "!";
                }

            }
        }

        else {
            resultString = "No monster found!";
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(resultString);

    }


}
