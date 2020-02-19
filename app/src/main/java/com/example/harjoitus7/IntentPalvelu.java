package com.example.harjoitus7;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;


import androidx.annotation.Nullable;
import androidx.room.Room;


public class IntentPalvelu extends IntentService {

    MyTableDao myTableDao;
    Database database;
    public IntentPalvelu() {
        super("palvelunNimi");
    }
    public IntentPalvelu(String name) {
        super(name);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        database = Room.databaseBuilder(getApplicationContext(), Database.class, Database.kannanNimi).allowMainThreadQueries().build();
        this.myTableDao = DatabaseSingleton.getInstance(getApplicationContext()).myTableDao();

        MyEntity entity = (MyEntity) intent.getSerializableExtra("teksti");
        myTableDao.Insert(entity);
    }
}