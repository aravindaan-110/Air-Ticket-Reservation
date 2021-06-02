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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passText;
    private Button loginButton;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText=findViewById(R.id.emailText);
        passText=findViewById(R.id.passwordText);
        loginButton=findViewById(R.id.loginButton);
        auth=FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtemail = emailText.getText().toString();
                String txtpass = passText.getText().toString();
                loginUser(txtemail,txtpass);
            }
        });


    }

    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(loginActivity.this, "Login Successful !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(loginActivity.this, MainActivity.class));
                    FirebaseUser user = auth.getCurrentUser();

                }
                else
                    Toast.makeText(loginActivity.this, "User not found !", Toast.LENGTH_SHORT).show();
                }

        });
    }
}
