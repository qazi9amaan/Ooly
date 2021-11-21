package com.oolysolutions.oolys.Act.Drop;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Database.Database;

import java.util.List;

public class DropViewModel extends AndroidViewModel {

    MutableLiveData<List<AddressDetails>> listMutableLiveData;
    private Database database;

    public DropViewModel(@NonNull Application application) {
        super(application);
        listMutableLiveData = new MutableLiveData<>();
        database = Database.getDatabase(application.getApplicationContext());

        getAllAddresses();
    }

    public MutableLiveData<List<AddressDetails>> getListObserver(){
        return listMutableLiveData;
    }

    public void getAllAddresses(){
        List<AddressDetails> addressDetails = database.getHandler().getDropAddresses();
        if(addressDetails.size() > 0 ){
            listMutableLiveData.postValue(addressDetails);
        }else{
            listMutableLiveData.postValue(null);
        }
    }

    public void addAddress(AddressDetails addressDetails){
        database.getHandler().insertAddress(addressDetails);
    }

    public AddressDetails getItemDetails(String id) {
        return database.getHandler().getAddress(id);
    }
}
