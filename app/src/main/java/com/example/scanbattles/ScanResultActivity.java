package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;

import java.util.ArrayList;

public class ScanResultActivity extends AppCompatActivity {
    boolean alreadyOwned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.SCAN_KEY);

        Scan scan = new Scan(message);
        Monster monster = scan.scan();

        String resultString;
        int imageResID;
        //monster returned by scan
        if(monster != null) {
            imageResID = monster.image;
            ArrayList<Monster> newMonster = (ArrayList<Monster>) AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{monster.id});
            alreadyOwned = newMonster.size() != 0;
            //check to see if owned

            //owned
            if (alreadyOwned) {
                //heal monster
                newMonster.get(0).currentHP = newMonster.get(0).maxHP;
                AppDatabase.getAppDatabase(this).monsterDao().update(newMonster.get(0));
                resultString = "Healed " + newMonster.get(0).name + "!";
                if (!AppDatabase.getAppDatabase(this).userDao().getTribe().equals(monster.tribe)) {
                    fightDialogue(monster);
                }
            }

            //Check if in User's tribe
            else if (!AppDatabase.getAppDatabase(this).userDao().getTribe().equals(monster.tribe)) {
                fightDialogue(monster);
                resultString = monster.tribe + " Monster found!";
            }

            //not owned
            else {
                //add monster to user monsters
                monster.currentHP = monster.maxHP;
                AppDatabase.getAppDatabase(this).monsterDao().insertAll(monster);
                resultString = "Added " + monster.name + " to your monsters!";
            }

        }

        else {
            imageResID = R.drawable.sad_face;
            resultString = "No monster found!";
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(resultString);
        ImageView imageView = findViewById(R.id.scanResultMonsterImage);
        imageView.setImageResource(imageResID);

    }

    private void fightDialogue(final Monster monster) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ScanResultActivity.this);
        if (alreadyOwned) {
            builder.setTitle("You already own " + monster.name + ". Fight again?");
        }
        else{
            builder.setTitle("Fight " + monster.tribe + " monster?");
        }

        builder.setPositiveButton("Let's go!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(AppDatabase.getAppDatabase(ScanResultActivity.this).userDao().getAll().get(0).emptyTeam(1)
                    && AppDatabase.getAppDatabase(ScanResultActivity.this).userDao().getAll().get(0).emptyTeam(2)
                    && AppDatabase.getAppDatabase(ScanResultActivity.this).userDao().getAll().get(0).emptyTeam(3)){
                    Toast.makeText(ScanResultActivity.this,"You must make a team of monsters first!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(ScanResultActivity.this, FightActivity.class);
                    intent.putExtra("monsterID", monster.id);
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void MenuOption(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ScanAgainOption(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

}
