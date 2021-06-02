package com.example.airplaneticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passText;
    private Button register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailText = findViewById(R.id.emailText);
        passText = findViewById(R.id.passwordText);
        register = findViewById(R.id.registerButton);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtemail = emailText.getText().toString();
                String txtpass = passText.getText().toString();

                if (txtemail.equals("") || txtpass.equals("")) {
                    Toast.makeText(registerActivity.this, "Empty Credentials !", Toast.LENGTH_SHORT).show();
                } else if (txtpass.length() < 6) {
                    Toast.makeText(registerActivity.this, "Password too short !", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtemail, txtpass);
                }
            }
        });
    }

    private void registerUser(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(registerActivity.this, "Registration successful !", Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(registerActivity.this,MainActivity.class));
                    finish();
                } else{
                    try {
                        Toast.makeText(registerActivity.this, "Registration failed or User already exists", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    }
            }
        });

    }
}
