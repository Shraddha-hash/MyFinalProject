package com.example.myfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfinalproject.model.Products;
import com.example.myfinalproject.viewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class home extends AppCompatActivity {
private DatabaseReference productref;
private RecyclerView recyclerView;
private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        productref= FirebaseDatabase.getInstance().getReference().child("Products");

        recyclerView=findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    protected void OnStart()
    {

        super.onStart();
        FirebaseRecyclerOptions<Products>options=new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref,Products.class).build();
        FirebaseRecyclerAdapter<Products, ProductViewHolder>adapter=new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull Products products) {
                productViewHolder.txtProductName.setText(products.getPname());
                productViewHolder.txtProductDescription.setText(products.getDescription());
                productViewHolder.txtProductPrice.setText("Price= "+ products.getPrice()+"$");
                Picasso.get().load(products.getImage()).into(productViewHolder.imageView);

            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home,parent,false);
                ProductViewHolder holder=new ProductViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}