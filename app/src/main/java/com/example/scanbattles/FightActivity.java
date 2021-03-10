package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
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
    private List<Monster> monsters;
    private Monster currentMonster;
    private TextView log;
    private String logString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        viewFlipper = findViewById(R.id.view_flipper);

        int monsterID = getIntent().getExtras().getInt("monsterID");
        try {
            enemyMonster = (Monster) AllMonsters.getMonsterById(monsterID, MainActivity.allMonsters).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        log = findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());
        log.setText("");
        logString = "";
    }

    private void setupEnemy() {
        //makes monster more difficult based on rarity and current team
        int monstersTotalHP = 0;
        for(int i = 0; i < monsters.size(); i++){
            monstersTotalHP += monsters.get(i).maxHP;
        }
        monstersTotalHP /= monsters.size();
        monstersTotalHP *= (5-enemyMonster.rarity);

//        if((enemyMonster.rarity == 3 && monstersTotalHP > 30) || (enemyMonster.rarity == 2 && monstersTotalHP < 30)){
//            monstersTotalHP = 30;
//        }
//        if((enemyMonster.rarity == 2 && monstersTotalHP > 70) || (enemyMonster.rarity == 1 && monstersTotalHP < 70)){
//            monstersTotalHP = 70;
//        }

        enemyMonster.maxHP = monstersTotalHP;
        enemyMonster.currentHP = enemyMonster.maxHP;
        enemyMonster.level = (4-enemyMonster.rarity);
    }

    private List<Monster> getMonsterTeam(int team) {
        User user = AppDatabase.getAppDatabase(this).userDao().getAll().get(0);
        switch (team){
            case 1:
                return AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{user.team1Monster1, user.team1Monster2, user.team1Monster3});
            case 2:
                return AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{user.team2Monster1, user.team2Monster2, user.team2Monster3});
            default:
                return AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{user.team3Monster1, user.team3Monster2, user.team3Monster3});
        }
    }

    public void teamSelected(View v){
        User user = AppDatabase.getAppDatabase(this).userDao().getAll().get(0);
        int selectedTeam = Integer.parseInt(v.getTag().toString());
        if(user.emptyTeam(selectedTeam)){
            Toast.makeText(getApplicationContext(), "Team " + v.getTag().toString() + " is empty!", Toast.LENGTH_SHORT).show();
        }
        else if(teamIsFainted(selectedTeam)){
            Toast.makeText(getApplicationContext(), "All monsters in Team " + v.getTag().toString() + " have fainted!", Toast.LENGTH_SHORT).show();
        }
        else {

            monsters = getMonsterTeam(selectedTeam);
            currentMonster = monsters.get(0);
            int i = 0;
            while(currentMonster.currentHP==0 && i < 3){
                currentMonster = monsters.get(++i);
            }
            setupEnemy();

            updateMonsterViews();
            viewFlipper.showNext();

            if(enemyMonster.getSpeed() > currentMonster.getSpeed()){
                enemyAttack();
            }
        }
    }

    public void updateMonsterViews(){
        String enemyMonsterHPText = enemyMonster.currentHP + "/" + enemyMonster.maxHP;
        TextView enemyMonsterHP = findViewById(R.id.enemyMonsterHP);
        enemyMonsterHP.setText(enemyMonsterHPText);

        ImageView enemyMonsterImage = findViewById(R.id.enemyMonsterImage);
        enemyMonsterImage.setImageResource(enemyMonster.image);

        ImageView currentMonsterImage = findViewById(R.id.currentMonsterImage);
        currentMonsterImage.setImageResource(currentMonster.image);

        TextView currentMonsterName = findViewById(R.id.currentMonsterName);
        currentMonsterName.setText(currentMonster.name);

        TextView currentMonsterHP = findViewById(R.id.currentMonsterHP);
        String currentMonsterHPText = currentMonster.currentHP + "/" + currentMonster.maxHP;
        currentMonsterHP.setText(currentMonsterHPText);

        ImageView monster1Image = findViewById(R.id.monster1Image);
        monster1Image.setImageResource(monsters.get(0).image);
        if(monsters.get(0).currentHP==0){
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            monster1Image.setColorFilter(new ColorMatrixColorFilter(matrix));
        }
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
            if(monsters.get(1).currentHP==0){
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                monster2Image.setColorFilter(new ColorMatrixColorFilter(matrix));
            }
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
            if(monsters.get(2).currentHP==0){
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                monster3Image.setColorFilter(new ColorMatrixColorFilter(matrix));
            }
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
        viewFlipper.showNext();
    }

    public void switchMonster(View v){
        int selectedMonster = Integer.parseInt(v.getTag().toString());
        if(monsters.get(selectedMonster).currentHP!=0) {
            currentMonster = monsters.get(selectedMonster);
            if(enemyMonster.getSpeed() > currentMonster.getSpeed()){
                enemyAttack();
            }
            updateMonsterViews();
            viewFlipper.showPrevious();
        }
        else{
            Toast.makeText(getApplicationContext(), "This monster has fainted!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelSwitch(View v){
        viewFlipper.showPrevious();
    }

    public void updateLog(String text){
        logString = "<font color=#ffffff>" + text + "</font><br>" + (!logString.contains("<br>") ? "": (logString.substring(0,logString.indexOf("<br>"))
                +"<font color=#bbbbbb>" + logString.substring(logString.indexOf("<br>")) + "</font>"));
        log.setText(Html.fromHtml(logString, Html.FROM_HTML_MODE_LEGACY));
    }

    public void userAttack(View v) {
        if(currentMonster.currentHP==0){
            Toast.makeText(getApplicationContext(), "Your monster has fainted and cannot attack!", Toast.LENGTH_SHORT).show();
        }
        else if(enemyMonster.currentHP==0){
            captureMonsterDialogue();
        }
        else {
            int attack = calculateAttack(currentMonster, enemyMonster);
            double dodgeChance = enemyMonster.getDefense() / 20.0;
            if (Math.random() > dodgeChance) {
                enemyMonster.currentHP = (attack >= enemyMonster.currentHP ? 0 : enemyMonster.currentHP - attack);
                updateLog(currentMonster.name + " deals " + attack + " damage to " + enemyMonster.name + "!");
            } else {
                updateLog(currentMonster.name + " attacked " + enemyMonster.name + " and missed!");
            }
            updateMonsterViews();
            if (enemyMonster.currentHP == 0) {
                captureMonsterDialogue();
            } else {
                enemyAttack();
            }
        }
    }

    private int calculateAttack(Monster attackingMonster, Monster defendingMonster) {
        int attack = attackingMonster.getAttack();
        String classCombo = attackingMonster.classification + defendingMonster.classification;
        if(classCombo.equals("PowerPower")||classCombo.equals("TechTech")||classCombo.equals("MagicMagic")){
            return attack;
        }
        else if(classCombo.equals("MagicPower")||classCombo.equals("PowerTech")||classCombo.equals("TechMagic")){
            return attack + 1;
        }
        else{
            return attack - 1;
        }
    }

    private void captureMonsterDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FightActivity.this);
        builder.setTitle("You have won the Battle!");
        boolean alreadyOwned = AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{enemyMonster.id}).size() != 0;
        if (!alreadyOwned) {
            builder.setMessage("Do you want to capture " + enemyMonster.name + "?");
            builder.setPositiveButton("Yeah!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    captureMonster();
                    victory();
                }
            });
            builder.setNegativeButton("Nah", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    victory();
                }
            });
        }
        else{
            builder.setPositiveButton("Nice!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    victory();
                }
            });
        }


        builder.show();
    }

    private void victory() {
        for(int i = 0; i < monsters.size(); i++){
            monsters.get(i).maxHP = monsters.get(i).maxHP + 2;
            if(monsters.get(i).maxHP > 99){
                monsters.get(i).maxHP = 99;
            }
            if(monsters.get(i).maxHP > 30) monsters.get(i).level = 2;
            if(monsters.get(i).maxHP > 70) monsters.get(i).level = 3;
            AppDatabase.getAppDatabase(this).monsterDao().update(monsters.get(i));
        }

        FightActivity.this.finish();
    }

    private void captureMonster() {
        Monster normalisedEnemyMonster = AllMonsters.getMonsterById(enemyMonster.id, MainActivity.allMonsters);
        AppDatabase.getAppDatabase(this).monsterDao().insertAll(normalisedEnemyMonster);
    }

    private void enemyAttack() {
        int attack = calculateAttack(enemyMonster, currentMonster);
        double dodgeChance = currentMonster.getDefense() / 20.0;
        if (Math.random() > dodgeChance){
            damageCurrentMonster(attack);
            updateLog(enemyMonster.name + " deals " + attack + " damage to " + currentMonster.name + "!");
        }
        else{
            updateLog(enemyMonster.name + " attacked " + currentMonster.name + " and missed!");
        }
        updateMonsterViews();
        if(currentMonster.currentHP==0){
            updateLog(currentMonster.name + " fainted!");
            if(!healthyMonstersRemaining()){ //show dialogue if no more monsters
                showFailureDialogue();
            }
        }
    }

    private boolean teamIsFainted(int team){
        List<Monster> monsterList = getMonsterTeam(team);
        for(int i = 0; i<monsterList.size(); i++){
            if(monsterList.get(i).currentHP!=0) return false;
        }
        return true;
    }

    private boolean healthyMonstersRemaining(){
        for(int i = 0; i< monsters.size(); i++){
            if(monsters.get(i).currentHP>0) return true;
        }
        return false;
    }

    private void showFailureDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FightActivity.this);
        builder.setTitle("All your monsters have fainted!");
        builder.setPositiveButton("Darn!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                FightActivity.this.finish();
            }
        });
        builder.show();
    }

    private void damageCurrentMonster(int attack){
        currentMonster.currentHP = (attack >= currentMonster.currentHP? 0 : currentMonster.currentHP - attack);
        for(int i = 0; i < monsters.size(); i++){
            if(monsters.get(i).id == currentMonster.id){
                monsters.get(i).currentHP = currentMonster.currentHP;
                break;
            }
        }
        AppDatabase.getAppDatabase(this).monsterDao().update(currentMonster);
    }

    public void endFight(View v){
        FightActivity.this.finish();
    }

}