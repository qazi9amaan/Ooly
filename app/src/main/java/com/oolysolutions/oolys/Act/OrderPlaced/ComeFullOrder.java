package com.oolysolutions.oolys.Act.OrderPlaced;

import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.ParcelDetails.ParcelDetails;
import com.oolysolutions.oolys.Act.PaymentDetails.Payment;

public class ComeFullOrder {

    String currentUserId;
    String currentUserPhoneNumber;
    AddressDetails pickUpDetails;
    AddressDetails dropDetails;
    ParcelDetails parcelDetails;
    Payment paymentDetails;

    public ComeFullOrder() {
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getCurrentUserPhoneNumber() {
        return currentUserPhoneNumber;
    }

    public void setCurrentUserPhoneNumber(String currentUserPhoneNumber) {
        this.currentUserPhoneNumber = currentUserPhoneNumber;
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

    public Payment getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Payment paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
