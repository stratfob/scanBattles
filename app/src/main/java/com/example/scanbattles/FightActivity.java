package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        int monsterID = getIntent().getExtras().getInt("monsterID");
        TextView tv = findViewById(R.id.MonsterID);
        tv.setText(AllMonsters.getMonsterById(monsterID, AllMonsters.getAllMonsters()).name);
    }
}