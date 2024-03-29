package com.example.bitsi34gb.emercencylifesaver;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ProfilePage extends Activity implements PopupMenu.OnMenuItemClickListener{
    static EditText date, time;
    EditText pname,area;
    EditText pnumber;
    Spinner bloodGroup;
    ImageButton menubutton;
    Button register;
    private int _day;
    private int _month;
    private int _birthYear;
    private Context _context;
    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";


    String[] SbloodGroup = {"Blood Group", "A+", "A-", "B+", "B-", "O+","O-", "AB+", "AB-"};
    // String[] Sarea = {"Select area", "Palayam", "pattom"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        bloodGroup = (Spinner) findViewById(R.id.spin_donor_blooggrp);
        area = (EditText) findViewById(R.id.Spin_donorPage_Area);
        date = (EditText) findViewById(R.id.eT_donorPage_date);
        time = (EditText) findViewById(R.id.eT_donorPage_Time);
       menubutton = (ImageButton)findViewById(R.id.menubutton);
        pname = (EditText) findViewById(R.id.eT_donorPage_patientName);
        pnumber = (EditText) findViewById(R.id.eT_donorPage_patientPhone);
        register = (Button) findViewById(R.id.bTn_donorPage_search);

        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this, R.layout.textview, SbloodGroup);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(adapter_category);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonDatePickerDialog(v);


            }


            private void showTruitonDatePickerDialog(View v) {


                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");

            }


        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  showTruitonTimePickerDialog(v);

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(ProfilePage.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }


           /* private void showTruitonTimePickerDialog(View v) {

                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }*/

        });
menubutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        PopupMenu popup = new PopupMenu(ProfilePage.this, v);

        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(ProfilePage.this);
        inflater.inflate(R.menu.menuf, popup.getMenu());
        popup.show();



    }
});



     register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (!date.getText().toString().equals("")) {
            if (!time.getText().toString().equals("")) {
                if (!pname.getText().toString().equals("")) {
                    // Toast.makeText(getActivity(),"Not Empty",Toast.LENGTH_SHORT).show();
                    if (!pnumber.getText().toString().equals("")) {

                        //send SMS
                        String msg = "BLOOD BANK"
                                +"\n"+"Patient name: "+pname.getText().toString()
                                +"\n"+"Blood group: "+bloodGroup.getSelectedItem().toString()
                                +"\n"+"Required date: "+date.getText().toString()
                                +"\n"+"Required time: "+time.getText().toString()
                                +"\n"+"Area: "+area.getText().toString()
                                +"\n"+"Phone No: "+pnumber.getText().toString();
                        String phNo="+918870892269";
                        sendSMS(phNo, msg);


                    Toast.makeText(ProfilePage.this, "Search", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfilePage.this,ListOfDonors.class);
                    intent.putExtra("bloodgroup",bloodGroup.getSelectedItem().toString());
                    intent.putExtra("area",area.getText().toString());
                    startActivity(intent);



                    } else {

                        pnumber.setError("Enter Patient Number");
                    }

                } else {
                    pname.setError("Enter Patient Name");
                    //   Toast.makeText(getActivity(),"Empty",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                time.setError("Enter Required Time");
            }
        }

        else {
            date.setError("Enter Required Date");
        }



     }
    });



    }




    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> messageParts = smsManager.divideMessage(msg);
            smsManager.sendMultipartTextMessage(phoneNo, null, messageParts, null, null);
            Toast.makeText(ProfilePage.this, "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(ProfilePage.this,ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);

                              /*  Intent intent = new Intent(Users.this, MainActivity.class);
                                intent.putExtra("finish", true);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);*/
                                finish();
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyNads", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(ProfilePage.this, Blooddonar.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.lastdonateddate:
                 Intent intent1 = new Intent(ProfilePage.this,LastDonated.class);
                 startActivity(intent1);
                 return true;
            case R.id.helpline:
                Intent intent12 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+918870892269", null));
                startActivity(intent12);
                 return  true;

            default:
                return false;
        }
    }




    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            date.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            // time.setText(time.getText() + " -" + hourOfDay + ":" + minute);
        }
    }






}
