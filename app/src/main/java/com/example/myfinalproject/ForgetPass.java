package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPass extends AppCompatActivity {

    Button reset,back;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        reset=findViewById(R.id.button3);
        back=findViewById(R.id.button4);
        email =findViewById(R.id.editTextTextEmailAddress3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPass.this,MainActivity.class));
            }
        });
    }
}