package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.Monster;

import java.util.List;

public class MonstersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monsters);

        List<Monster> test = AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{1});
        TextView textView = findViewById(R.id.textView3);
        if(test.isEmpty()){
            textView.setText("Empty");
        }
        else{
            textView.setText(test.get(0).name);
        }

    }

    public void addMonster(View view) {

        Monster monster = new Monster();
        monster.id = 1;
        monster.name = "Hello";
        AppDatabase.getAppDatabase(this).monsterDao().insertAll(monster);
        TextView textView = findViewById(R.id.textView3);
        textView.setText(AppDatabase.getAppDatabase(this).monsterDao().loadAllByIds(new int[]{1}).get(0).name);


    }
}
