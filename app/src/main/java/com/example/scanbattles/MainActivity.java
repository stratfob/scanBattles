package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;
import com.example.scanbattles.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String SCAN_KEY = "com.example.scanbattles.SCAN";
    public static final ArrayList<Monster> allMonsters = AllMonsters.getAllMonsters();
    public static final ArrayList<Monster> monsterHashArray = AllMonsters.createMonsterHashArray(allMonsters);
    public static final double monsterRate = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(AppDatabase.getAppDatabase(this).userDao().getAll().size()==0){
            //Debug - add all monsters
//            for (int i = 0; i < allMonsters.size(); i++){
//                AppDatabase.getAppDatabase(this).monsterDao().insertAll(allMonsters.get(i));
//            }

            Intent intent = new Intent(this, TribeSelectionActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public void onBackPressed() {

    }

    public void scanMenuOption(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void monstersMenuOption(View view) {
        Intent intent = new Intent(this, MonstersActivity.class);
        startActivity(intent);
    }
}
