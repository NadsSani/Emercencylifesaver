package com.example.bitsi34gb.emercencylifesaver;

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

public class LastDonated extends AppCompatActivity {
    SharedPreferences sharedPref;
    String userid ;
    private static final String TAG = "Nadeem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_donated);

        final EditText lastdate = (EditText) findViewById(R.id.lastdonatedchange);
        Button button = (Button)findViewById(R.id.submitfordonateddate);
        sharedPref = getApplicationContext().getSharedPreferences("MyNads", Context.MODE_PRIVATE);
        userid = sharedPref.getString("userid","0");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("donors/"+userid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastdate.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"date should not be empty",Toast.LENGTH_LONG).show();
                }
                else{


                  myRef.child("lastdonateddate").setValue(lastdate.getText().toString());
                    moveTaskToBack(true);
                    Intent intent = new Intent(LastDonated.this, ProfilePage.class);
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
    }
}
