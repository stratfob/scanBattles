package com.example.scanbattles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SCAN_KEY = "com.example.scanbattles.SCAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void scanMenuOption(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void monstersMenuOption(View view) {
        Intent intent = new Intent(this, MonstersActivity.class);
        startActivity(intent);
    }

    public void itemsMenuOption(View view) {
        //TODO
    }

    public void teamMenuOption(View view) {
        //TODO
    }
}
