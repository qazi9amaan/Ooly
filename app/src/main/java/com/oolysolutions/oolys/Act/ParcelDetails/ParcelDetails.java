package com.oolysolutions.oolys.Act.ParcelDetails;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "parcels")
public class ParcelDetails {
    @PrimaryKey(autoGenerate = true)
    long id;

    String quantity;
    String codAmount ;
    String payAmount;
    String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ParcelDetails() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(String codAmount) {
        this.codAmount = codAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
}
