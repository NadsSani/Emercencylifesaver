package com.example.bitsi34gb.emercencylifesaver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.bitsi34gb.emercencylifesaver.buissinessentities.CreateUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditDetails extends AppCompatActivity {
    SharedPreferences sharedPref;
    Spinner bloodGroup,gender;
    Button btn_submit;
    private AwesomeValidation awesomeValidation;
    String[] SbloodGroup = {"Blood Group", "A+", "A-", "B+", "B-", "O+","O-", "AB+", "AB-"};
    String[] Gender = {"Male","Female","Others"};
    EditText name,phone,location,lastdonated,password,userid,dateofbirth,eligible;
    String sname,slocation,slastdonated, spassword,suserid,sdateofbirth,sgender,sbloodgroup,sphone,seligible;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("donors");


        sharedPref = getApplicationContext().getSharedPreferences("MyNads", Context.MODE_PRIVATE);

        sname = sharedPref.getString("name","d");
        slocation =sharedPref.getString("location","d");
        slastdonated =sharedPref.getString("lastdonateddate","d");
        spassword =sharedPref.getString("password","d");
        suserid =sharedPref.getString("userid","d");
        sdateofbirth =sharedPref.getString("dob","d");
        sgender =sharedPref.getString("gender","d");
        sbloodgroup =sharedPref.getString("bloodgroup","d");
        sphone =sharedPref.getString("phone","d");
        seligible =sharedPref.getString("medicalreport","d");
       /* btn_submit = (Button)findViewById(R.id.submit1);
        bloodGroup = (Spinner) findViewById(R.id.bloodgroup1);
        gender = (Spinner) findViewById(R.id.gender1);
        name = (EditText)findViewById(R.id.name1);
        password = (EditText)findViewById(R.id.password1);
        location = (EditText)findViewById(R.id.location1);
        phone = (EditText)findViewById(R.id.phoneno1);
        lastdonated = (EditText)findViewById(R.id.lastdonated1);
        dateofbirth = (EditText)findViewById(R.id.dob1);
        eligible = (EditText)findViewById(R.id.eligible1);
        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this, R.layout.textview, SbloodGroup);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(adapter_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.textview, Gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        name.setText(sname);
        phone.setText(sphone);
        location.setText(slocation);
        lastdonated.setText(slastdonated);
        password.setText(spassword);
        userid.setText(suserid);
        dateofbirth.setText(sdateofbirth);
        eligible.setText(seligible);
        gender.setSelection(getIndex(gender,sgender.toString()));
        bloodGroup.setSelection(getIndex(bloodGroup,sbloodgroup.toString()));
        userid.setVisibility(View.INVISIBLE);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(EditDetails.this,R.id.name,".{3,}",R.string.name);
        awesomeValidation.addValidation(EditDetails.this,R.id.userid,".{3,}",R.string.username);
        awesomeValidation.addValidation(EditDetails.this,R.id.password,".{3,}",R.string.password);
        awesomeValidation.addValidation(EditDetails.this,R.id.location,".{3,}",R.string.location);
        awesomeValidation.addValidation(EditDetails.this,R.id.phoneno,".{3,}",R.string.phone);
        awesomeValidation.addValidation(EditDetails.this,R.id.dob,".{3,}",R.string.dateofbirth);
        awesomeValidation.addValidation(EditDetails.this,R.id.eligible,".{3,}",R.string.eligible);
        awesomeValidation.addValidation(EditDetails.this,R.id.lastdonated,".{3,}",R.string.lastdonated);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname = name.getText().toString();
                suserid = userid.getText().toString();
                spassword = password.getText().toString();
                slocation = location.getText().toString();
                sphone = phone.getText().toString();
                sdateofbirth = dateofbirth.getText().toString();
                seligible = eligible.getText().toString();
                slastdonated = lastdonated.getText().toString();
                sgender = gender.getSelectedItem().toString();
                sbloodgroup = bloodGroup.getSelectedItem().toString();
                if(awesomeValidation.validate()){


                    myRef.child(suserid).child("name").setValue(sname);
                    myRef.child(suserid).child("location").setValue(slocation);
                    myRef.child(suserid).child("dateofbirth").setValue(sdateofbirth);
                    myRef.child(suserid).child("bloodgroup").setValue(sbloodgroup);
                    myRef.child(suserid).child("medicalreport").setValue(seligible);
                    myRef.child(suserid).child("gender").setValue(sgender);
                    myRef.child(suserid).child("lastdonateddate").setValue(slastdonated);
                    myRef.child(suserid).child("phone").setValue(sphone);
                    myRef.child(suserid).child("password").setValue(spassword);
                    moveTaskToBack(true);

                    Intent intent = new Intent(EditDetails.this, ProfilePage.class);
                    intent.putExtra("finish", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            }
        });


*/
    }
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
