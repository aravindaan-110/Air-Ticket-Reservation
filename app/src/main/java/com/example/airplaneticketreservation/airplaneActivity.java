package com.example.airplaneticketreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class airplaneActivity extends AppCompatActivity {
    String SourceDeparture;
    String ArrivalDestination;

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked){
                    SourceDeparture="Bangalore";
                    }
                break;
            case R.id.radioButton2:
                if (checked){
                    SourceDeparture="Chennai";
                    }
                break;
            case R.id.radioButton3:
                if (checked){
                    SourceDeparture="Trichy";
                    }
                break;
            case R.id.radioButton4:
                if (checked){
                    ArrivalDestination="Dubai";
                    }
                break;
            case R.id.radioButton5:
                if (checked){
                    ArrivalDestination="Goa";
                    }
                break;
            case R.id.radioButton6:
                if (checked){
                    ArrivalDestination="Singapore";
                    }
                break;
        }
    }


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane);


    button=findViewById(R.id.button3);



    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(SourceDeparture.equals("Chennai") || SourceDeparture.equals("Bangalore") || SourceDeparture.equals("Trichy") || SourceDeparture.equals("") && ArrivalDestination.equals("Dubai") ||ArrivalDestination.equals("Goa") || ArrivalDestination.equals("Singapore") || ArrivalDestination.equals("")){

            FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("Source").child("name").setValue(SourceDeparture);
            MainActivity.details.put("Source",SourceDeparture);
            MainActivity.details.put("Destination",ArrivalDestination);
            FirebaseDatabase.getInstance().getReference().child("Ticket Details").child(MainActivity.passName).child("Destination").child("name").setValue(ArrivalDestination);
            startActivity(new Intent(airplaneActivity.this,flightActivity.class));
            }
            else
                Toast.makeText(airplaneActivity.this, "No flights for requested source/destination !", Toast.LENGTH_SHORT).show();
        }
    });
    }
}
