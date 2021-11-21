package com.oolysolutions.oolys.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;

import java.util.List;

@Dao
public interface AddressHandler {

    @Insert
    public void insertAddress(AddressDetails ... addressDetails);

    @Update
    public void updateAddress(AddressDetails addressDetails);

    @Delete
    public void deleteAddress(AddressDetails addressDetails);

    @Query("Select * from addresses where type = 'pickup'")
    public List<AddressDetails> getPickupAddresses();

    @Query("Select * from addresses where type = 'drop'")
    public List<AddressDetails> getDropAddresses();

    @Query("Select * from addresses where :mid = id")
    AddressDetails getAddress(String mid);
}
