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

    public void removeMonster(int id){
        if (team1Monster1 == id) team1Monster1 = 0;
        if (team1Monster2 == id) team1Monster2 = 0;
        if (team1Monster3 == id) team1Monster3 = 0;
        if (team2Monster1 == id) team2Monster1 = 0;
        if (team2Monster2 == id) team2Monster2 = 0;
        if (team2Monster3 == id) team2Monster3 = 0;
        if (team3Monster1 == id) team3Monster1 = 0;
        if (team3Monster2 == id) team3Monster2 = 0;
        if (team3Monster3 == id) team3Monster3 = 0;
    }

    public boolean addMonster(int id, int team){
        switch (team){
            case 1:
                if (team1Monster1 != 0 && team1Monster2 != 0 && team1Monster3 != 0){
                    return false;
                }
                else {
                    if (team1Monster1 == 0) team1Monster1 = id;
                    else if (team1Monster2 == 0) team1Monster2 = id;
                    else team1Monster3 = id;
                }
                break;
            case 2:
                if (team2Monster1 != 0 && team2Monster2 != 0 && team2Monster3 != 0){
                    return false;
                }
                else {
                    if (team2Monster1 == 0) team2Monster1 = id;
                    else if (team2Monster2 == 0) team2Monster2 = id;
                    else team2Monster3 = id;
                }
                break;
            case 3:
                if (team3Monster1 != 0 && team3Monster2 != 0 && team3Monster3 != 0){
                    return false;
                }
                else {
                    if (team3Monster1 == 0) team3Monster1 = id;
                    else if (team3Monster2 == 0) team3Monster2 = id;
                    else team3Monster3 = id;
                }
                break;
            default:
        }

        return true;
    }

    public boolean emptyTeam(int team){
        switch(team){
            case 1:
                return (team1Monster1 == 0 && team1Monster2 == 0 && team1Monster3 == 0);
            case 2:
                return (team2Monster1 == 0 && team2Monster2 == 0 && team2Monster3 == 0);
            case 3:
                return (team3Monster1 == 0 && team3Monster2 == 0 && team3Monster3 == 0);
            default:
                return true;
        }
    }

}