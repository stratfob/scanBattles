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


        ArrayList<Monster> myMonsters = (ArrayList<Monster>) AppDatabase.getAppDatabase(this).monsterDao().getAll();

        mAdapter = new MonsterAdapter(myMonsters);
        recyclerView.setAdapter(mAdapter);

    }

}

