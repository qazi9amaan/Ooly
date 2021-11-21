package com.oolysolutions.oolys.Act.NewOrder;

import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.ParcelDetails.ParcelDetails;

public class Order {

    AddressDetails pickUpDetails;
    AddressDetails dropDetails;
    ParcelDetails parcelDetails;


    public Order() {
    }



    public AddressDetails getPickUpDetails() {
        return pickUpDetails;
    }

    public void setPickUpDetails(AddressDetails pickUpDetails) {
        this.pickUpDetails = pickUpDetails;
    }

    public AddressDetails getDropDetails() {
        return dropDetails;
    }

    public void setDropDetails(AddressDetails dropDetails) {
        this.dropDetails = dropDetails;
    }

    public ParcelDetails getParcelDetails() {
        return parcelDetails;
    }

    public void setParcelDetails(ParcelDetails parcelDetails) {
        this.parcelDetails = parcelDetails;
    }
}
