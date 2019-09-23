package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonstersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monsters);

        recyclerView = findViewById(R.id.monsterRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //ArrayList<Monster> myMonsters = (ArrayList<Monster>) AppDatabase.getAppDatabase(this).monsterDao().getAll();
        Monster monster = new Monster();
        monster.name = "I'm a monster";
        monster.image = R.drawable.ic_android_black_24dp;
        monster.attack = 12;
        monster.currentHP = 100;
        monster.defense = 33;
        monster.speed = 4;

        Monster monster2 = new Monster();
        monster2.name = "I'm a monster";
        monster2.image = R.drawable.ic_android_black_24dp;
        monster2.attack = 123;
        monster2.currentHP = 1020;
        monster2.defense = 333;
        monster2.speed = 44;

        ArrayList<Monster> myMonsters = new ArrayList<>();
        myMonsters.add(monster);
        myMonsters.add(monster2);
        mAdapter = new MonsterAdapter(myMonsters);
        recyclerView.setAdapter(mAdapter);

    }

}




/*

 List<Monster> test = AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{1});
        TextView textView = findViewById(R.id.textView3);
        if(test.isEmpty()){
            textView.setText("Empty");
        }
        else{
            textView.setText(test.get(0).name);
        }

Monster monster1 = new Monster();
        monster1.id = 2;
        monster1.name = "two";
        Monster monster2 = new Monster();
        monster2.id = 3;
        monster2.name = "three";
        AppDatabase.getAppDatabase(this).monsterDao().insertAll(monster1,monster2);
        TextView textView = findViewById(R.id.textView3);
        textView.setText(AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{1}).get(0).name);

 */
