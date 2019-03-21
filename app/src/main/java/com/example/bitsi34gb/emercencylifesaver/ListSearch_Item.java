package com.example.bitsi34gb.emercencylifesaver;

public class ListSearch_Item {

    private String Name;
    private String PhoneNo;
    private String Place;
     private String medical;

    public ListSearch_Item(String Name, String PhoneNo, String Place,String medical) {
        this.Name = Name;
        this.PhoneNo = PhoneNo;
        this.Place = Place;
        this.medical = medical;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public String getPlace() {
        return Place;
    }

    public String getMedical() { return medical; }
}