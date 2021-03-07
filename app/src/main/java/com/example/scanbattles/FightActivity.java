package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;
import com.example.scanbattles.models.User;

import java.util.ArrayList;
import java.util.List;

public class FightActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private Monster enemyMonster;
    TextView enemyMonsterHP;
    TextView currentMonsterName;
    TextView currentMonsterHP;
    ImageView enemyMonsterImage;
    ImageView currentMonsterImage;
    List<Monster> monsters;
    Monster currentMonster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        viewFlipper = findViewById(R.id.view_flipper);

        int monsterID = getIntent().getExtras().getInt("monsterID");
        enemyMonster = AllMonsters.getMonsterById(monsterID, MainActivity.allMonsters);
        setupEnemy();

        String enemyMonsterHPText = enemyMonster.currentHP + "/" + enemyMonster.maxHP;
        enemyMonsterHP = findViewById(R.id.enemyMonsterHP);
        enemyMonsterHP.setText(enemyMonsterHPText);

        enemyMonsterImage = findViewById(R.id.enemyMonsterImage);
        enemyMonsterImage.setImageResource(enemyMonster.image);

    }

    private void setupEnemy() {
        //TODO make monster more difficult based on rarity
        enemyMonster.currentHP = enemyMonster.maxHP;
    }

    private void getMonsterTeam(int team) {
        User user = AppDatabase.getAppDatabase(this).userDao().getAll().get(0);
        switch (team){
            case 1:
                monsters = AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{user.team1Monster1, user.team1Monster2, user.team1Monster3});
                break;
            case 2:
                monsters = AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{user.team2Monster1, user.team2Monster2, user.team2Monster3});
                break;
            case 3:
                monsters = AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{user.team3Monster1, user.team3Monster2, user.team3Monster3});
                break;
        }

        currentMonster = monsters.get(0);
    }

    public void teamSelected(View v){
        if(AppDatabase.getAppDatabase(this).userDao().getAll().get(0).emptyTeam(Integer.parseInt(v.getTag().toString()))){
            Toast.makeText(getApplicationContext(), "Team " + v.getTag().toString() + " is empty!", Toast.LENGTH_SHORT).show();
        }
        else {
            int selectedTeam = Integer.parseInt(v.getTag().toString());
            getMonsterTeam(selectedTeam);

            currentMonsterImage = findViewById(R.id.currentMonsterImage);
            currentMonsterImage.setImageResource(currentMonster.image);

            currentMonsterName = findViewById(R.id.currentMonsterName);
            currentMonsterName.setText(currentMonster.name);

            currentMonsterHP = findViewById(R.id.currentMonsterHP);
            String currentMonsterHPText = currentMonster.currentHP + "/" + currentMonster.maxHP;
            currentMonsterHP.setText(currentMonsterHPText);
            viewFlipper.showNext();
        }
    }

    public void endFight(View v){
        FightActivity.this.finish();
    }

}