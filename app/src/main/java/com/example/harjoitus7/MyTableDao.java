package com.example.harjoitus7;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyTableDao {
    @Query("SELECT * FROM myentity ORDER BY id desc")
    List<MyEntity> getAllInDescendingOrder();

    @Insert
    void Insert(MyEntity myEntity);

    @Delete
    void deleteTaulu(MyEntity myEntity);
}
