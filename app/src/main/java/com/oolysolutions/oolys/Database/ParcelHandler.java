package com.oolysolutions.oolys.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.ParcelDetails.ParcelDetails;

@Dao
public interface ParcelHandler {

    @Insert
    public void insertParcelDetails(ParcelDetails... parcelDetails);

    @Query("Select * from parcels where :mid = id")
    ParcelDetails getDetails(String mid);

    @Update
    void updatePArcel(ParcelDetails mparcelDetails);

    @Delete
    void removeParcel(ParcelDetails mparcelDetails);
}
