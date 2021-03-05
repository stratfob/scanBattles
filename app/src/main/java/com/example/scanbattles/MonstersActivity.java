package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    int selectedTeam = 0;

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

    public void setTeam(int monsterPosition, int team){
        Monster monster = myMonsters.get(monsterPosition);
        monster.team = team;
        AppDatabase.getAppDatabase(this).monsterDao().update(monster);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMonsterClick(int position) {
//        Toast toast = Toast.makeText(getApplicationContext(), myMonsters.get(position).name, Toast.LENGTH_SHORT);
//        toast.show();
        String monsterName = myMonsters.get(position).name;
        showOptionsDialogue(position, monsterName);
    }

    private void showOptionsDialogue(final int monsterPosition, String monsterName) {
        selectedTeam = 0;
        final String[] teamChoices = new String[] {"None", "Team 1", "Team 2", "Team 3"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MonstersActivity.this);
        builder.setTitle("Choose a team for " + monsterName);
        builder.setSingleChoiceItems(teamChoices, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedTeam = which;
            }
        });
        builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTeam(monsterPosition, selectedTeam);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}

