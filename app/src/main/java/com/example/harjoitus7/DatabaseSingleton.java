package com.example.harjoitus7;

import android.content.Context;

import androidx.room.Room;

public class  DatabaseSingleton {

    private static Database INSTANCE;

    public static synchronized Database getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, Database.class, "DATABASE").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return INSTANCE;
    }

}