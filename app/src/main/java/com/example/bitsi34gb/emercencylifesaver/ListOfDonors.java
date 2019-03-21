package com.example.bitsi34gb.emercencylifesaver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListOfDonors extends AppCompatActivity {
String bloodgroup,place;
    RecyclerView recyclerView;
    private static final String Tah = "NASE";
    ArrayList<String> arrayList = new ArrayList<>();
    List<ListSearch_Item> donorList = new ArrayList<>();
    String lastDonated;
    ListSearch_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_donors);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("donors");
        Intent intent = getIntent();
        recyclerView = (RecyclerView) findViewById(R.id.rv_list_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        place = intent.getStringExtra("area").toString();
        bloodgroup = intent.getStringExtra("bloodgroup").toString();
        Log.e(Tah,place + bloodgroup);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                donorList.clear();
                int numOfDays;
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                           if(dataSnapshot1.child("bloodgroup").getValue().toString().equals(bloodgroup) && dataSnapshot1.child("location").getValue().toString().equals(place)){


                                lastDonated = dataSnapshot1.child("lastdonateddate").getValue().toString();

                               if(!lastDonated.equals("")) {
                                   Date ld = null;
                                   long diff = 0;
                                   try {
                                       ld = new SimpleDateFormat("dd/MM/yyyy").parse(lastDonated);
                                       Date today = new Date();
                                       diff = today.getTime() - ld.getTime();
                                   } catch (ParseException e) {
                                       e.printStackTrace();
                                   }

                                   numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
                               }else{numOfDays=91;}


                               if (numOfDays>90){donorList.add(new ListSearch_Item(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("phone").getValue().toString(),dataSnapshot1.child("location").getValue().toString(),dataSnapshot1.child("medicalreport").getValue().toString()));}
                               //donorList.add(new ListSearch_Item(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("phone").getValue().toString(),dataSnapshot1.child("location").getValue().toString()));
                        }
                    adapter = new ListSearch_Adapter(ListOfDonors.this, donorList);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
