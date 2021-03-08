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
import java.util.Locale;

public class FightActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private Monster enemyMonster;
    private TextView enemyMonsterHP;
    private TextView currentMonsterName;
    private TextView currentMonsterHP;
    private ImageView enemyMonsterImage;
    private ImageView currentMonsterImage;
    private List<Monster> monsters;
    private  Monster currentMonster;


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

            updateMonsterViews();
            viewFlipper.showNext();

            startFight();
        }
    }

    public void updateMonsterViews(){
        currentMonsterImage = findViewById(R.id.currentMonsterImage);
        currentMonsterImage.setImageResource(currentMonster.image);

        currentMonsterName = findViewById(R.id.currentMonsterName);
        currentMonsterName.setText(currentMonster.name);

        currentMonsterHP = findViewById(R.id.currentMonsterHP);
        String currentMonsterHPText = currentMonster.currentHP + "/" + currentMonster.maxHP;
        currentMonsterHP.setText(currentMonsterHPText);

        ImageView monster1Image = findViewById(R.id.monster1Image);
        monster1Image.setImageResource(monsters.get(0).image);
        TextView monster1Name = findViewById(R.id.monster1Name);
        monster1Name.setText(monsters.get(0).name);
        TextView monster1HP = findViewById(R.id.monster1HP);
        String monster1HPText = "HP: " + monsters.get(0).currentHP + "/" + monsters.get(0).maxHP;
        monster1HP.setText(monster1HPText);
        TextView monster1Attack = findViewById(R.id.monster1Attack);
        monster1Attack.setText(String.format(Locale.getDefault(),"Attack: %d", monsters.get(0).getAttack()));
        TextView monster1Defense = findViewById(R.id.monster1Defense);
        monster1Defense.setText(String.format(Locale.getDefault(), "Defense: %d", monsters.get(0).getDefense()));
        TextView monster1Speed = findViewById(R.id.monster1Speed);
        monster1Speed.setText(String.format(Locale.getDefault(), "Speed: %d", monsters.get(0).getSpeed()));

        if(monsters.size()>1){
            findViewById(R.id.monster2Card).setVisibility(View.VISIBLE);
            ImageView monster2Image = findViewById(R.id.monster2Image);
            monster2Image.setImageResource(monsters.get(1).image);
            TextView monster2Name = findViewById(R.id.monster2Name);
            monster2Name.setText(monsters.get(1).name);
            TextView monster2HP = findViewById(R.id.monster2HP);
            String monster2HPText = "HP: " + monsters.get(1).currentHP + "/" + monsters.get(1).maxHP;
            monster2HP.setText(monster2HPText);
            TextView monster2Attack = findViewById(R.id.monster2Attack);
            monster2Attack.setText(String.format(Locale.getDefault(), "Attack: %d", monsters.get(1).getAttack()));
            TextView monster2Defense = findViewById(R.id.monster2Defense);
            monster2Defense.setText(String.format(Locale.getDefault(), "Defense: %d", monsters.get(1).getDefense()));
            TextView monster2Speed = findViewById(R.id.monster2Speed);
            monster2Speed.setText(String.format(Locale.getDefault(), "Speed: %d", monsters.get(1).getSpeed()));
        }
        else{
            findViewById(R.id.monster2Card).setVisibility(View.INVISIBLE);
        }
        if(monsters.size()>2){
            findViewById(R.id.monster3Card).setVisibility(View.VISIBLE);
            ImageView monster3Image = findViewById(R.id.monster3Image);
            monster3Image.setImageResource(monsters.get(2).image);
            TextView monster3Name = findViewById(R.id.monster3Name);
            monster3Name.setText(monsters.get(2).name);
            TextView monster3HP = findViewById(R.id.monster3HP);
            String monster3HPText = "HP: " + monsters.get(2).currentHP + "/" + monsters.get(2).maxHP;
            monster3HP.setText(monster3HPText);
            TextView monster3Attack = findViewById(R.id.monster3Attack);
            monster3Attack.setText(String.format(Locale.getDefault(), "Attack: %d", monsters.get(2).getAttack()));
            TextView monster3Defense = findViewById(R.id.monster3Defense);
            monster3Defense.setText(String.format(Locale.getDefault(), "Defense: %d", monsters.get(2).getDefense()));
            TextView monster3Speed = findViewById(R.id.monster3Speed);
            monster3Speed.setText(String.format(Locale.getDefault(), "Speed: %d", monsters.get(2).getSpeed()));
        }
        else{
            findViewById(R.id.monster3Card).setVisibility(View.INVISIBLE);
        }
    }

    public void switchMonsterView(View v){
        //TODO check if there are any playable monsters remaining
        viewFlipper.showNext();
    }

    public void switchMonster(View v){
        int selectedMonster = Integer.parseInt(v.getTag().toString());
        Toast.makeText(this, selectedMonster + "", Toast.LENGTH_SHORT).show();
        currentMonster = monsters.get(selectedMonster);
        updateMonsterViews();
        viewFlipper.showPrevious();
    }

    public void cancelSwitch(View v){
        viewFlipper.showPrevious();
    }

    private void startFight() {

    }

    public void endFight(View v){
        FightActivity.this.finish();
    }

}