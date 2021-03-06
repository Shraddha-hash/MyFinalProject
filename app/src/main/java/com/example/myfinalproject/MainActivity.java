package com.example.myfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView gotoreg,gotofpass;
    Button btnsignin;
    EditText etmail,etpass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoreg=findViewById(R.id.textView2);
        gotofpass=findViewById(R.id.textView);
        btnsignin=findViewById(R.id.button);
        etmail=findViewById(R.id.editTextTextEmailAddress);
        etpass=findViewById(R.id.editTextTextPassword);
        mAuth=FirebaseAuth.getInstance();
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


        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
            }
        });
    }

    private void userlogin() {
        final String email=etmail.getText().toString().trim();
        String pass=etpass.getText().toString().trim();
        if(email.isEmpty())
        {
            etmail.setError("Please enter email");
            etmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etmail.setError("Please enter valid email");
            etmail.requestFocus();
            return;
        }
        if(pass.isEmpty()) {
            etpass.setError("Please enter password");
            etpass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    if(email.equals("ourp272207@gmail.com"))
                        startActivity(new Intent(MainActivity.this,owner.class));
                    else
                        startActivity(new Intent(MainActivity.this,customer.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Failed to login! Please, check your login credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}