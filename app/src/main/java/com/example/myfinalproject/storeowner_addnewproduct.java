package com.example.myfinalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class storeowner_addnewproduct extends AppCompatActivity {
    private String categoryname,description,price,pname,savecurrentdate,savecurrenttime,productrandomkey,downloadimageurl;
    private Button Addnewproductbutton;
    private ImageView inputproductimage;
    private EditText inputproductname,inputproductdescription,inputproductprice;
    private static final int gallerypick=1;
    private Uri imageuri;

    private StorageReference Productimagesref;
    private DatabaseReference productref;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeowner_addnewproduct);

        categoryname=getIntent().getExtras().get("category").toString();
        Productimagesref= FirebaseStorage.getInstance().getReference().child("Product Images");
        productref=FirebaseDatabase.getInstance().getReference().child("Products");
        Addnewproductbutton=(Button)findViewById(R.id.add_new_product);
        inputproductimage=(ImageView)findViewById(R.id.select_product_image);
        inputproductname=(EditText)findViewById(R.id.product_name);
        inputproductdescription=(EditText)findViewById(R.id.product_description);
        inputproductprice=(EditText)findViewById(R.id.product_price);
        loadingbar=new ProgressDialog(this);

        inputproductimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

    Addnewproductbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ValidateProductData();
        }
    });

    }

    private void openGallery() {

        Intent galleryIntent=new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,gallerypick);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==gallerypick  &&  resultCode==RESULT_OK   && data!=null)
        {
            imageuri=data.getData();
            inputproductimage.setImageURI(imageuri);

        }
    }

    private void ValidateProductData()
    {

        description=inputproductdescription.getText().toString();
        price=inputproductprice.getText().toString();
        pname=inputproductname.getText().toString();

        if(imageuri==null)
        {

            Toast.makeText(this,"Product Image Required",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(description))
        {

            Toast.makeText(this,"Enter Product Description",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(price))
        {

            Toast.makeText(this,"Enter Product Price",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pname))
        {

            Toast.makeText(this,"Enter Product Name",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Storeproductinformation();

        }
    }

    private void Storeproductinformation() {

        loadingbar.setTitle("Adding new Product");
        loadingbar.setMessage("Please wait while we are adding a new product...");
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.show();


        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentdate=new SimpleDateFormat("MMM dd, yyyy");
        savecurrentdate=currentdate.format(calendar.getTime());
        SimpleDateFormat currenttime=new SimpleDateFormat("HH:mm:ss a");
        savecurrenttime=currenttime.format(calendar.getTime());

        productrandomkey=savecurrentdate + savecurrenttime;
        final StorageReference filepath=Productimagesref.child(imageuri.getLastPathSegment()+productrandomkey+ ".jpg");
        final UploadTask uploadTask=filepath.putFile(imageuri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message=e.toString();
                Toast.makeText(storeowner_addnewproduct.this,"Error:"+message,Toast.LENGTH_SHORT).show();
                   loadingbar.dismiss();
            }
        }) .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(storeowner_addnewproduct.this,"Product image uploaded successfully",Toast.LENGTH_SHORT).show();

                Task<Uri> urltask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();


                        }
                        downloadimageurl=filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if(task.isSuccessful()) {
                            downloadimageurl=task.getResult().toString();
                            Toast.makeText(storeowner_addnewproduct.this, " got Product image url successfully", Toast.LENGTH_SHORT).show();
                            saveproductinfotodatabase();
                        }
                    }
                });
            }
        });



    }

    private void saveproductinfotodatabase() {

        HashMap<String,Object> productMap=new HashMap<>();
        productMap.put("pid",productrandomkey);
        productMap.put("date",savecurrentdate);
        productMap.put("description",description);
        productMap.put("image",downloadimageurl);
        productMap.put("category",categoryname);
        productMap.put("price",price);
        productMap.put("pname",pname);

        productref.child(productrandomkey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {

                    Intent intent=new Intent(storeowner_addnewproduct.this,category.class);
                    loadingbar.dismiss();
                    Toast.makeText(storeowner_addnewproduct.this," Product is added successfully",Toast.LENGTH_SHORT).show();


                }
                else
                {    loadingbar.dismiss();
                    String message=task.getException().toString();
                    Toast.makeText(storeowner_addnewproduct.this," Error" +message,Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}