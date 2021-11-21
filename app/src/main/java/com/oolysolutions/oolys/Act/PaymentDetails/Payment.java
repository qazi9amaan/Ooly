package com.oolysolutions.oolys.Act.PaymentDetails;

public class Payment {
     public static double BASE_AMOUNT = 30;
    // charges per km
    public static double CPK = 3.75;
    public static double TAX = 19.45;
    public static double DISCOUNT = -20.2;
     double CHARGES ,COST = 0;
     double KM,PAYATPICKUP =0;


    public Payment() {
    }


    public double getCPK() {
        return CPK;
    }



    public double getTAX() {
        return TAX;
    }


    public double getDISCOUNT() {
        return DISCOUNT;
    }


    public double getCHARGES() {
        return CHARGES;
    }

    public void setCHARGES(double CHARGES) {
        this.CHARGES = CHARGES;
    }

    public double getCOST() {
        return COST;
    }

    public void setCOST(double COST) {
        this.COST = COST;
    }

    public double getKM() {
        return KM;
    }

    public void setKM(double KM) {
        this.KM = KM;
    }

    public double getPAYATPICKUP() {
        return PAYATPICKUP;
    }

    public void setPAYATPICKUP(double PAYATPICKUP) {
        this.PAYATPICKUP = PAYATPICKUP;
    }
}
