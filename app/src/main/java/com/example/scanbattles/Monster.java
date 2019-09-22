package com.example.scanbattles;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Monster {
    @PrimaryKey
    public int monsterID;

    public String monsterName;
    public String monsterTribe;
}