package com.example.scanbattles.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Monster {
    @PrimaryKey
    public int id;
    public int image;
    public String name;
    public String tribe;
    public String classification;
    public int maxHP;
    public int currentHP;
    public int level;
    public int defense;
    public int speed;
    public int attack;

}