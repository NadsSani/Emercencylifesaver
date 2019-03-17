package com.example.bitsi34gb.emercencylifesaver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UsersActivity extends Activity {
    Spinner bloodGroup,gender;
    String[] SbloodGroup = {"Blood Group", "A+", "A-", "B+", "B-", "O+","O-", "AB+", "AB-"};
    String[] Gender = {"Male","Female","Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        bloodGroup = (Spinner) findViewById(R.id.bloodgroup);
        gender = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this, R.layout.textview, SbloodGroup);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(adapter_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.textview, Gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);




    }
}
