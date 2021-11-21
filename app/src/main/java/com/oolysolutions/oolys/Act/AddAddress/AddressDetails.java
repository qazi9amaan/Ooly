package com.oolysolutions.oolys.Act.AddAddress;

import android.content.Intent;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

@Entity(tableName = "addresses")
public class AddressDetails {

    @PrimaryKey(autoGenerate = true)
    long id;

    String name;
    String phone;
    String address;
    String area;
    String pincode;
    String state;
    String district;

    Double lat;
    Double lon;

    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AddressDetails(String name, String phone, String address, String area, String pincode, String state, String district) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.area = area;
        this.pincode = pincode;
        this.state = state;
        this.district = district;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }


    public String getPhone() {
        return phone;
    }


    public String getAddress() {
        return address;
    }


    public String getArea() {
        return area;
    }


    public String getPincode() {
        return pincode;
    }


    public String getState() {
        return state;
    }


    public String getDistrict() {
        return district;
    }


}
