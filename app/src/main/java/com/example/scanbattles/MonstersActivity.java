package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonstersActivity extends AppCompatActivity implements MonsterAdapter.monsterClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Monster> myMonsters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monsters);

        recyclerView = findViewById(R.id.monsterRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        myMonsters = (ArrayList<Monster>) AppDatabase.getAppDatabase(this).monsterDao().getAll();

        mAdapter = new MonsterAdapter(myMonsters, this);
        recyclerView.setAdapter(mAdapter);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void setTeam(int team){

    }

    @Override
    public void onMonsterClick(int position) {
        Toast toast = Toast.makeText(getApplicationContext(), myMonsters.get(position).name, Toast.LENGTH_SHORT);
        toast.show();
    }
}

