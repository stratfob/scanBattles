package com.example.scanbattles.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Monster {
    @PrimaryKey
    public int id;
    public String name;
    public String tribe;
    public String classification;
    public int startingHP;
    public int levelOneDefense;
    public int levelTwoDefense;
    public int levelThreeDefense;
    public int levelOneSpeed;
    public int levelTwoSpeed;
    public int levelThreeSpeed;
    public int levelOneDamage;
    public int levelTwoDamage;
    public int levelThreeDamage;

    //TODO - Image

}