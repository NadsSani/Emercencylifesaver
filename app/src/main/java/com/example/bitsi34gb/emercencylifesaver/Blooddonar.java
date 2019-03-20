package com.example.bitsi34gb.emercencylifesaver;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Blooddonar extends AppCompatActivity {
Button login,signup;
EditText userid , password;
String user , pass ;

    SharedPreferences sharedPref;
    ProgressDialog progressDialog;
 private static final String TAG = "Nadeemse";
    SharedPreferences.Editor editor;
    static HashMap<String,String> hashMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blooddonar);



        sharedPref = getApplicationContext().getSharedPreferences("MyNads", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Log.e(TAG , sharedPref.getString("userid","0"));
        userid = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(Blooddonar.this);
                progressDialog.setMessage("please Wait while loading");
                progressDialog.show();
                user = userid.getText().toString();
                pass = password.getText().toString();
                if(!user.isEmpty()&& !pass.isEmpty()){
                signin(user,pass);
                }else{

                    Toast.makeText(getApplicationContext(),"Userid and Password cannot be empty",Toast.LENGTH_LONG).show();
                }


            }
        });
        signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Blooddonar.this,UsersActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        sharedPref = getApplicationContext().getSharedPreferences("MyNads",Context.MODE_PRIVATE);
        String email1 =  sharedPref.getString("userid","d");
        String password = sharedPref.getString("password","d");
        if(email1.equals("d")|| password.equals("d")){
            return;

        }
        else{
            progressDialog = new ProgressDialog(Blooddonar.this);
            progressDialog.setMessage("please Wait while loading");
            progressDialog.show();
            signin(email1,password);

        }
    }

    public void saveinsharedpref(){
       editor.putString("dob",hashMap.get("dateofbirth"));
        editor.putString("bloodgroup",hashMap.get("bloodgroup"));
        editor.putString("lastdonateddate",hashMap.get("lastdonateddate"));
        editor.putString("location",hashMap.get("location"));
        editor.putString("gender",hashMap.get("gender"));
        editor.putString("userid",hashMap.get("userid"));
        editor.putString("medicalreport",hashMap.get("medicalreport"));
        editor.putString("name",hashMap.get("name"));
        editor.putString("password",hashMap.get("password"));
        editor.putString("phone",hashMap.get("phone"));
        editor.apply();

    }
public void signin(final String usern, final String password){

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("donors");
    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.hasChild(usern)){
                hashMap.put("userid",usern);

                for(DataSnapshot dataSnapshot1:dataSnapshot.child(usern).getChildren()){

                    hashMap.put(dataSnapshot1.getKey(),dataSnapshot1.getValue().toString());
                }
                if (hashMap.get("password").equals(password)){
                    saveinsharedpref();
                    progressDialog.cancel();
                    Intent intent = new Intent(Blooddonar.this,ProfilePage.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Cant login without correct password ",Toast.LENGTH_LONG).show();
                }

            }
            else {
                Toast.makeText(getApplicationContext(),"Cant login without correct userid and password ",Toast.LENGTH_LONG).show();

            }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

}


}
