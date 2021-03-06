package com.example.scanbattles;

import com.example.scanbattles.models.Monster;

public class Scan {

    private final String scanString;
    private final int hash;

    public Scan(String input){
        scanString = input;
        hash = hash();
    }

    private int hash(){
        return scanString.hashCode();
    }

    public Monster scan(){
        return AllMonsters.getMonsterWithHash(hash, MainActivity.monsterHashArray, MainActivity.monsterRate);
    }
}
