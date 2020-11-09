package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
//hii
public class category extends AppCompatActivity {
    private ImageView guitar,drum;
    private ImageView piano,harmonium;
    private ImageView veena,drum1;
    private ImageView flute,bugle;
    private ImageView cymbal,tabla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        guitar=(ImageView)findViewById(R.id.guitar) ;

        drum=(ImageView)findViewById(R.id.guitar) ;
        piano=(ImageView)findViewById(R.id.guitar) ;
        harmonium=(ImageView)findViewById(R.id.guitar) ;
        veena=(ImageView)findViewById(R.id.guitar) ;
        drum1=(ImageView)findViewById(R.id.guitar) ;
        flute=(ImageView)findViewById(R.id.guitar) ;
        bugle=(ImageView)findViewById(R.id.guitar) ;
        cymbal=(ImageView)findViewById(R.id.guitar) ;
        tabla=(ImageView)findViewById(R.id.guitar) ;
        guitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                        intent.putExtra("category","guitar");
                startActivity(intent);
            }

        });
        drum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","drum");
                startActivity(intent);
            }
        });
        piano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","piano");
                startActivity(intent);
            }
        });
        harmonium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","harmonium");
                startActivity(intent);
            }
        });
        veena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","veena");
                startActivity(intent);
            }
        });

        drum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","drum1");
                startActivity(intent);
            }
        });

        flute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","flute");
                startActivity(intent);
            }
        });

        bugle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","bugle");
                startActivity(intent);
            }
        });

        cymbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","cymbal");
                startActivity(intent);
            }
        });

        tabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(category.this,storeowner_addnewproduct.class);
                intent.putExtra("category","tabla");
                startActivity(intent);
            }
        });


    }
}