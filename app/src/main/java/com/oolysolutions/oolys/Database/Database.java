package com.oolysolutions.oolys.Database;
import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.ParcelDetails.ParcelDetails;

@androidx.room.Database( entities = {AddressDetails.class,ParcelDetails.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract AddressHandler getHandler();
    public abstract ParcelHandler getParcelHandler();

    private static  Database INSTANCE;

    public static  Database getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class,"OolyLocalDb")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE ;
    }

}
