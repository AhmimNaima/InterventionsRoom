package com.example.interventionroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InterventionDao {

    @Query("SELECT * FROM Intervention")
    List<Intervention> getAll();
    @Query("SELECT * FROM Intervention WHERE date LIKE :date")
    List<Intervention> getIntervention(String date);

    @Insert
    void insert(Intervention i);

    @Delete
    void delete(Intervention i);

    @Update
    void update(Intervention i);

}
