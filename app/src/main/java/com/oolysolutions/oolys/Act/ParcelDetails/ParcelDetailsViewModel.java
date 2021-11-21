package com.oolysolutions.oolys.Act.ParcelDetails;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Database.Database;

import java.util.List;

public class ParcelDetailsViewModel extends AndroidViewModel {

    MutableLiveData<ParcelDetails> parcelDetails;
    ParcelDetails addressDetails;
    Application application;

    public ParcelDetailsViewModel(@NonNull Application application) {
        super(application);
        parcelDetails = new MutableLiveData<>();
        this.application = application;
        getParcelDetails();
        addressDetails = new ParcelDetails();
    }



    public MutableLiveData<ParcelDetails> getListObserver(){
        return parcelDetails;
    }

    public void getParcelDetails(){
        if(addressDetails != null  ){
            parcelDetails.postValue(addressDetails);
        }else{
            parcelDetails.postValue(null);
        }
    }

    public ParcelDetails setDetails(){
        getParcelDetails();
        return addressDetails;

    }

    public void insetParcel(ParcelDetails parcelDetails){
        Database database = Database.getDatabase(application.getApplicationContext());
        database.getParcelHandler().insertParcelDetails(parcelDetails);

    }

    public ParcelDetails getItemDetails(String toString) {
        Database database = Database.getDatabase(application.getApplicationContext());
        return database.getParcelHandler().getDetails(toString);
    }

    public void setCurrentParcel(ParcelDetails pp) {
        addressDetails  = new ParcelDetails();
        addressDetails = pp;
        getParcelDetails();

    }

    public void updateParcel(ParcelDetails mparcelDetails) {
        Database database = Database.getDatabase(application.getApplicationContext());
        database.getParcelHandler().updatePArcel(mparcelDetails);
    }

    public void removeParcel(ParcelDetails mparcelDetails) {
        Database database = Database.getDatabase(application.getApplicationContext());
        database.getParcelHandler().removeParcel(mparcelDetails);
    }
}
