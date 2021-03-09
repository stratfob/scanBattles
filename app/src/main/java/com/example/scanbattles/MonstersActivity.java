package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;
import com.example.scanbattles.models.User;

import java.util.ArrayList;

public class MonstersActivity extends AppCompatActivity implements MonsterAdapter.monsterClickListener{
    private RecyclerView.Adapter<MonsterAdapter.MyViewHolder> mAdapter;
    ArrayList<Monster> myMonsters;
    int selectedTeam = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monsters);

        RecyclerView recyclerView = findViewById(R.id.monsterRecycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
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
        int monsterId = monster.id;
        User user = AppDatabase.getAppDatabase(this).userDao().getAll().get(0);
        if (user.addMonster(monsterId, team)){
            user.removeMonster(monsterId);  // to remove from other team
            user.addMonster(monsterId, team); // to add back into new team
            AppDatabase.getAppDatabase(this).userDao().update(user);
            monster.team = team;
            AppDatabase.getAppDatabase(this).monsterDao().update(monster);

        }
        else{
            Toast.makeText(getApplicationContext(),"Team " + team + " is already full!", Toast.LENGTH_SHORT).show();
        }
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

