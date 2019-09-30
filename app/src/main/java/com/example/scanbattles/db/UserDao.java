package com.example.scanbattles.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.scanbattles.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT tribe from user")
    String getTribe();

    @Query("SELECT team1 from User union SELECT team2 from User union SELECT team3 from User")
    List<Integer> getTeamMembers();

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    void update(User... users);
}