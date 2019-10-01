package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scanbattles.db.AppDatabase;
import com.example.scanbattles.models.User;

public class TribeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tribe_selection);
    }

    public void PataakOption(View view) {
        User user = new User();
        user.id = 1;
        user.tribe = "Pataak";
        AppDatabase.getAppDatabase(this).userDao().insertAll(user);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void UjaluOption(View view) {
        User user = new User();
        user.id = 1;
        user.tribe = "Ujalu";
        AppDatabase.getAppDatabase(this).userDao().insertAll(user);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void ZendraOption(View view) {
        User user = new User();
        user.id = 1;
        user.tribe = "Zendra";
        AppDatabase.getAppDatabase(this).userDao().insertAll(user);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
