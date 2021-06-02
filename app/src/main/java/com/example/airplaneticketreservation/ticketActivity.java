package com.example.airplaneticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ticketActivity extends AppCompatActivity {

     TextView nameText,gateText,flightText,dateText2,fromText,toText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        nameText=findViewById(R.id.nameText);

        gateText=findViewById(R.id.gateText);

        flightText=findViewById(R.id.flightText);

        dateText2=findViewById(R.id.dateText2);
        fromText=findViewById(R.id.fromtext);
        toText=findViewById(R.id.toText);

        nameText.setText(viewActivity.searchName);


        gateText.setText("C4");

        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(viewActivity.searchName).child("Destination");
        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    toText.setText(snapshot.getValue().toString().toUpperCase());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(viewActivity.searchName).child("Source");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    fromText.setText(snapshot.getValue().toString().toUpperCase());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(viewActivity.searchName).child("Dep Date");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    dateText2.setText(snapshot.getValue().toString().toUpperCase());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(viewActivity.searchName).child("Flight").child("FlightName");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    flightText.setText(snapshot.getValue().toString().toUpperCase());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
