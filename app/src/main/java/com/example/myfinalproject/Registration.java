package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registration extends AppCompatActivity {

    TextView gotologin;
    Button register;
    EditText etname,etmno,etmail,etpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        gotologin=findViewById(R.id.textView4);
        register=findViewById(R.id.button2);
        etname=findViewById(R.id.editTextTextPersonName);
        etmno=findViewById(R.id.editTextPhone);
        etmail=findViewById(R.id.editTextTextEmailAddress2);
        etpass=findViewById(R.id.editTextTextPassword2);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this,MainActivity.class));
            }
        });
    }
}