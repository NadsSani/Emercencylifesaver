package com.example.bitsi34gb.emercencylifesaver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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


public class UsersActivity extends Activity {
    Spinner bloodGroup,gender;
    Button btn_submit;
    String[] SbloodGroup = {"Blood Group", "A+", "A-", "B+", "B-", "O+","O-", "AB+", "AB-"};
    String[] Gender = {"Male","Female","Others"};

    EditText name,phone,location,lastdonated,password,userid,dateofbirth,eligible;
    String sname,slocation,slastdonated, spassword,suserid,sdateofbirth,sgender,sbloodgroup,sphone,seligible;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("donors");
        btn_submit = (Button)findViewById(R.id.submit);
        bloodGroup = (Spinner) findViewById(R.id.bloodgroup);
        gender = (Spinner) findViewById(R.id.gender);
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        userid = (EditText)findViewById(R.id.userid);
        location = (EditText)findViewById(R.id.location);
        phone = (EditText)findViewById(R.id.phoneno);
        lastdonated = (EditText)findViewById(R.id.lastdonated);
        dateofbirth = (EditText)findViewById(R.id.dob);
        eligible = (EditText)findViewById(R.id.eligible);
        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this, R.layout.textview, SbloodGroup);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(adapter_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.textview, Gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(UsersActivity.this,R.id.name,".{3,}",R.string.name);
        awesomeValidation.addValidation(UsersActivity.this,R.id.userid,".{3,}",R.string.username);
        awesomeValidation.addValidation(UsersActivity.this,R.id.password,".{3,}",R.string.password);
        awesomeValidation.addValidation(UsersActivity.this,R.id.location,".{3,}",R.string.location);
        awesomeValidation.addValidation(UsersActivity.this,R.id.phoneno,".{3,}",R.string.phone);
        awesomeValidation.addValidation(UsersActivity.this,R.id.dob,".{3,}",R.string.dateofbirth);
        awesomeValidation.addValidation(UsersActivity.this,R.id.eligible,".{3,}",R.string.eligible);
        awesomeValidation.addValidation(UsersActivity.this,R.id.lastdonated,".{3,}",R.string.lastdonated);
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
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(suserid)){
                                Toast.makeText(UsersActivity.this,"The Userid Already Exists",Toast.LENGTH_LONG).show();
                                return;
                            }
                            else {
                                CreateUser createUser = new CreateUser(sname,slocation,slastdonated,spassword,sdateofbirth,sgender,sphone,sbloodgroup,seligible);
                                myRef.child(suserid).setValue(createUser);
                                Toast.makeText(UsersActivity.this,"The User Created",Toast.LENGTH_LONG).show();
                                moveTaskToBack(true);

                                Intent intent = new Intent(UsersActivity.this, Blooddonar.class);
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
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
