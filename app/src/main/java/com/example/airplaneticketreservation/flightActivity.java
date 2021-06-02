package com.example.airplaneticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class flightActivity extends AppCompatActivity {
    String option;
    CheckBox checkBox2,checkBox3,checkBox4;

    public void checkBoxClicked(View view){
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkBox2:
                if (checked){
                    option="3";
                    checkBox3.setEnabled(false);
                    checkBox4.setEnabled(false);
                    Toast.makeText(this, "Qatar Airways Selected", Toast.LENGTH_SHORT).show();}

                break;
            case R.id.checkBox3:
                if(checked){
                    option="1";
                    checkBox2.setEnabled(false);
                    checkBox4.setEnabled(false);
                    Toast.makeText(this, "Air Indigo Selected", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.checkBox4:
                if(checked){
                    option="2";
                    checkBox2.setEnabled(false);
                    checkBox3.setEnabled(false);
                    Toast.makeText(this, "Air Asia Selected", Toast.LENGTH_SHORT).show();}
                break;

        }

    }

    EditText editText;
    Button button,button2;
    static int cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);


        button=findViewById(R.id.button4);
        button2=findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String flight;
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                if(option.equals("1")){
                    cost=5500;
                    flight="Air Indigo";
                    MainActivity.details.put("Flight",flight);
                    MainActivity.details.put("Cost",cost);
                    FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("cost").setValue(cost);
                    FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("Flight").child("FlightName").child("name").setValue(flight);
                    button.setEnabled(false);

                    Toast.makeText(flightActivity.this, "Air Indigo selected !", Toast.LENGTH_SHORT).show();}
                else if(option.equals("2")){
                    flight="Air Asia";
                    cost=5000;
                    MainActivity.details.put("Flight",flight);
                    MainActivity.details.put("Cost",cost);
                    FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("cost").setValue(cost);
                    FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("Flight").child("FlightName").child("name").setValue(flight);
                    button.setEnabled(false);
                    Toast.makeText(flightActivity.this, "Air Asia selected !", Toast.LENGTH_SHORT).show();}
                else if(option.equals("3")){
                    flight="Qatar Airlines";
                    cost=7000;
                    MainActivity.details.put("Flight",flight);
                    MainActivity.details.put("Cost",cost);
                    FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("cost").setValue(cost);
                    FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("Flight").child("FlightName").child("name").setValue(flight);
                    button.setEnabled(false);
                    Toast.makeText(flightActivity.this, "Qatar Airlines selected !", Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(flightActivity.this, "No flight found !", Toast.LENGTH_SHORT).show();}


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(flightActivity.this,billingActivity.class));
            }
        });

    }
}
