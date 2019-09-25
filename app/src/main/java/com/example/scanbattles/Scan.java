package com.example.scanbattles;

import com.example.scanbattles.models.Monster;

public class Scan {

    private String scanString;
    private int hash;

    public Scan(String input){
        scanString = input;
        hash = hash();
    }

    private int hash(){
        return scanString.hashCode();
    }

    public Monster scan(){
        return new AllMonsters().getMonsterWithHash(hash);
    }
}
