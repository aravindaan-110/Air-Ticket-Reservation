package com.example.airplaneticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class cancelActivity extends AppCompatActivity {

    EditText deleteName;
    Button cancelButton;
    static String deleteName2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        deleteName=findViewById(R.id.deleteName);
        cancelButton=findViewById(R.id.cancelButton);
        progressBar=findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(cancelActivity.this, "Processing...", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
                delay();
            }
        });
    }

    public void delay(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                deleteName2=deleteName.getText().toString();
                DocumentReference ref = FirebaseFirestore.getInstance().collection("Ticket Details").document(deleteName2);
                ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc = task.getResult();
                            if(doc.exists()){
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("Ticket Details").document(deleteName2).delete();

                                startActivity(new Intent(cancelActivity.this,MainActivity.class));
                            }
                            else{
                                startActivity(new Intent(cancelActivity.this,MainActivity.class));
                                finish();
                            }
                        }
                    }



                });
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(cancelActivity.this, "Ticket cancelled ! Amount will be refunded Shortly...", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(deleteName2).removeValue();



            }
        },4000);
    }
}
