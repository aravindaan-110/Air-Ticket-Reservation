package com.example.airplaneticketreservation;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.Query;


public class viewActivity extends AppCompatActivity {

    EditText name;
    Button viewButton;
    static String searchName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        name=findViewById(R.id.editText5);
        viewButton=findViewById(R.id.viewButton);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewActivity.this, "Button pressed", Toast.LENGTH_SHORT).show();
                searchName = name.getText().toString();

                DocumentReference ref = FirebaseFirestore.getInstance().collection("Ticket Details").document(searchName);
                ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc = task.getResult();
                            if(doc.exists()){

                                startActivity(new Intent(viewActivity.this,ticketActivity.class));}
                                else
                                Toast.makeText(viewActivity.this, "No Ticket Found!", Toast.LENGTH_SHORT).show();
                            }
                        }



                });



            }
        });


    }


}
