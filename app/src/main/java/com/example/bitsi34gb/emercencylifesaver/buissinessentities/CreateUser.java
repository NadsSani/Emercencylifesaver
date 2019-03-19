package com.example.bitsi34gb.emercencylifesaver.buissinessentities;

public class CreateUser {

    private String name;
    private String location;
    private String lastdonateddate;
    private String password;
    private String dateofbirth;
    private String gender;
    private String phone;
    private String bloodgroup;
    private String medicalreport;

    public CreateUser(String name, String location, String lastdonateddate, String password, String dateofbirth, String gender, String phone, String bloodgroup, String medicalreport) {
        this.name = name;
        this.location = location;
        this.lastdonateddate = lastdonateddate;
        this.password = password;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.phone = phone;
        this.bloodgroup = bloodgroup;
        this.medicalreport = medicalreport;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLastdonateddate() {
        return lastdonateddate;
    }

    public String getPassword() {
        return password;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public String getMedicalreport() {
        return medicalreport;
    }
}
