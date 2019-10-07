package com.example.scanbattles.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int id;
    public String tribe;
    public int team1Monster1;
    public int team1Monster2;
    public int team1Monster3;
    public int team2Monster1;
    public int team2Monster2;
    public int team2Monster3;
    public int team3Monster1;
    public int team3Monster2;
    public int team3Monster3;

}