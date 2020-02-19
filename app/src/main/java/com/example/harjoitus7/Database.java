package com.example.harjoitus7;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {MyEntity.class}, version = 1)

public abstract class Database extends RoomDatabase {
    public static final String kannanNimi = "DATABASE";
    public abstract MyTableDao myTableDao();
}

