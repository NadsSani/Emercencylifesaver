package com.example.bitsi34gb.emercencylifesaver;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class ProfilePage extends Activity {
    static EditText date, time;
    EditText pname,area;
    EditText pnumber;
    Spinner bloodGroup;

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
