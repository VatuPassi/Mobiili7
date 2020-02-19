package com.example.harjoitus7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telecom.CallScreeningService;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Kuuntelija kuuntelija;
    Date currentTime = Calendar.getInstance().getTime();

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> lista;
    MyTableDao myTableDao;
    Database database;




    public class Kuuntelija extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            MyEntity myEntity = new MyEntity();
            Intent i = new Intent(MainActivity.this,IntentPalvelu.class);
            i.putExtra("teksti", myEntity);




            if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){

                myEntity.txt ="Kiinni ";
                myEntity.aika = currentTime.toString();

            }
            else if (intent.getAction().equals((Intent.ACTION_SCREEN_ON))){
                myEntity.txt = "Auki ";
                myEntity.aika = currentTime.toString();
            }
            context.startService(i);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.kuuntelija = new Kuuntelija();
        IntentFilter intentFilter = new IntentFilter();
        registerReceiver(kuuntelija, intentFilter);
        listView = findViewById(R.id.listView);
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);

        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(kuuntelija, intentFilter);

        // Ei toimi ilman. Tuleeko tieto ollenkaan singletonin kautta?
        database = Room.databaseBuilder(getApplicationContext(), Database.class,Database.kannanNimi).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        myTableDao = database.myTableDao();

    }

    @Override
    public void onResume(){
        super.onResume();

        List<MyEntity> lista = myTableDao.getAllInDescendingOrder();

        for (MyEntity t : lista) {
            String s = "";
            s = s + t.txt + t.aika;
            adapter.add(s);
        }


    }
}
