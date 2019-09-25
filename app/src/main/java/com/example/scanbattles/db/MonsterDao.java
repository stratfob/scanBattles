package com.example.scanbattles.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.scanbattles.models.Monster;

import java.util.List;

@Dao
public interface MonsterDao {
    @Query("SELECT * FROM monster")
    List<Monster> getAll();

    @Query("SELECT * FROM monster WHERE id IN (:monsterIds)")
    List<Monster> loadAllByIds(int[] monsterIds);

    @Insert
    void insertAll(Monster... monsters);

    @Delete
    void delete(Monster monster);

    @Update
    void update(Monster... monsters);
}