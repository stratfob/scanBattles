package com.example.scanbattles.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int id;
    public String tribe;
    public int team1;
    public int team2;
    public int team3;

}