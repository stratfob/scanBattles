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
    public int defenseLvl1;
    public int speedLvl1;
    public int attackLvl1;
    public int defenseLvl2;
    public int speedLvl2;
    public int attackLvl2;
    public int defenseLvl3;
    public int speedLvl3;
    public int attackLvl3;
    public int rarity;

}