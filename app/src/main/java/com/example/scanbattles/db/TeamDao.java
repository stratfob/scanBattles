package com.example.scanbattles.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.scanbattles.models.Team;

import java.util.List;


@Dao
public interface TeamDao {


    @Query("SELECT monster1 from Team where id = :team " +
            "union SELECT monster2 from Team where id = :team " +
            "union SELECT monster3 from Team where id = :team")
    List<Integer> getTeamMembers(int team);

    @Query("SELECT * FROM Team")
    List<Team> getAll();

    @Query("SELECT * FROM Team WHERE id IN (:teamIds)")
    List<Team> loadAllByIds(int[] teamIds);

    @Insert
    void insertAll(Team... teams);

    @Delete
    void delete(Team team);

    @Update
    void update(Team... teams);
}