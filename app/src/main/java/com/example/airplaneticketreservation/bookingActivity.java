package com.example.airplaneticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;


public class bookingActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Button button,button2;
    private TextView dateView;
    private CalendarView calendarView2;
    EditText editText;
    static String passengerName;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        calendarView=findViewById(R.id.calendarView);
        button=findViewById(R.id.registerButton);
        dateView=findViewById(R.id.dateView);
        calendarView2=findViewById(R.id.calendarView2);
        calendarView2.setVisibility(View.INVISIBLE);
        dateView.setText("Choose Departure Date");
        button2=findViewById(R.id.loginButton);
        button2.setEnabled(false);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year1, int month1, int dayOfMonth1) {
                if (year1>2023)
                    Toast.makeText(bookingActivity.this, "Advance booking not allowed !", Toast.LENGTH_SHORT).show();
                else{
                    day=dayOfMonth1;
                    month=month1;
                    year=year1;
                    month1++;
                    FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("Dep Date").child("date").setValue(dayOfMonth1+"/"+month1+"/"+year1);
                    MainActivity.details.put("Dep Date",dayOfMonth1+"/"+month1+"/"+year1);
                    dateView.setText(dayOfMonth1+"/"+month1+"/"+year1);}
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    calendarView.setVisibility(View.INVISIBLE);
                    calendarView2.setVisibility(View.VISIBLE);
                    dateView.setText("Choose Arrival date");
                    button.setEnabled(false);
                    button2.setEnabled(true);

                    calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView view, int year2, int month2, int dayOfMonth2) {
                            if (year2<year || month2<month || dayOfMonth2<day){
                                Toast.makeText(bookingActivity.this, "Check date properly !", Toast.LENGTH_SHORT).show();
                            button2.setEnabled(false);}
                            else{
                                month2++;
                                FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child(MainActivity.passName).child("Arr Date").setValue(dayOfMonth2+"/"+month2+"/"+year2);
                                MainActivity.details.put("Arrival Date",dayOfMonth2+"/"+month2+"/"+year2);
                                String temp = Integer.toString(dayOfMonth2);
                                if(temp.equals("")){
                                    Toast.makeText(bookingActivity.this, "Enter Arrival Date", Toast.LENGTH_SHORT).show();
                                    button2.setEnabled(false);
                                }
                                else{
                                dateView.setText(dayOfMonth2+"/"+month2+"/"+year2);
                                button2.setEnabled(true);}
                            }
                        }
                    });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(bookingActivity.this,airplaneActivity.class));
            }
        });
    }
}
