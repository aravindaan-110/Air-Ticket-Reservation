package com.example.airplaneticketreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    static Button bookButton;
    static Button cancelButton;
    static Button viewButton;
    Button button;
    EditText editText;
    static String passName;
    static Map<String,Object> details = new HashMap<>();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Logout Successful !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,startActivity.class));
                finish();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookButton=findViewById(R.id.bookButton);
        cancelButton=findViewById(R.id.cancelButton);
        viewButton=findViewById(R.id.viewButton);
        button=findViewById(R.id.button6);
        editText=findViewById(R.id.editText4);

        bookButton.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
        viewButton.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passName=editText.getText().toString();
                details.put("Name",passName);
                Toast.makeText(MainActivity.this, "Name set !", Toast.LENGTH_SHORT).show();
                button.setVisibility(View.INVISIBLE);
                bookButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);
                viewButton.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,bookingActivity.class));
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,cancelActivity.class));
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,viewActivity.class));
            }
        });



    }
}
