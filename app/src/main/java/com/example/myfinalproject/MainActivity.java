package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView gotoreg,gotofpass;
    Button btnsignin;
    EditText etmail,etpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoreg=findViewById(R.id.textView2);
        gotofpass=findViewById(R.id.textView);
        btnsignin=findViewById(R.id.button);
        etmail=findViewById(R.id.editTextTextEmailAddress);
        etpass=findViewById(R.id.editTextTextPassword);

        gotoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });

        gotofpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ForgetPass.class));
            }
        });
    }

}