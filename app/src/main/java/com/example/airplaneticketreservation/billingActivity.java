package com.example.airplaneticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;


public class billingActivity extends AppCompatActivity {
    ProgressBar progressBar;
    EditText cvvtext,dateText,cardText;
    Button payButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        cvvtext=findViewById(R.id.cvvtext);
        dateText=findViewById(R.id.dateText);
        cardText=findViewById(R.id.cardtext);
        payButton=findViewById(R.id.payButton);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payButton.setVisibility(View.INVISIBLE);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Ticket Details").document(MainActivity.passName).set(MainActivity.details);
                Toast.makeText(billingActivity.this, "Processing payment...", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
                if(cardText.length()==16 && cvvtext.length()==3){
                    delay();

                    }
                else{
                    payButton.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(billingActivity.this, "Invalid card !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void delay(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(billingActivity.this, "Payment Success !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(billingActivity.this,MainActivity.class));

            }
        },4000);
    }
}
