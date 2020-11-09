package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class storeowner_addnewproduct extends AppCompatActivity {
    private String categoryname;
    private Button Addnewproductbutton;
    private ImageView inputproductimage;
    private EditText inputproductname,inputproductdescription,inputproductprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeowner_addnewproduct);

        categoryname=getIntent().getExtras().get("category").toString();
        Addnewproductbutton=(Button)findViewById(R.id.add_new_product);
        inputproductimage=(ImageView)findViewById(R.id.select_product_image);
        inputproductname=(EditText)findViewById(R.id.product_name);
        inputproductdescription=(EditText)findViewById(R.id.product_description);
        inputproductprice=(EditText)findViewById(R.id.product_price);



    }
}