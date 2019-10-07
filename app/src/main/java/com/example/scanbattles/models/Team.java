package com.example.scanbattles.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Team {
    @PrimaryKey
    public int id;
    public int monster1;
    public int monster2;
    public int monster3;

}