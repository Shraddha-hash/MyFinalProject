package com.example.myfinalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.name.setText(model.getPname());
        holder.category.setText(model.getCategory());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView name,category,price,description;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image=(CircleImageView)itemView.findViewById(R.id.imageview1);
            name=(TextView)itemView.findViewById(R.id.textview);
            category=(TextView)itemView.findViewById(R.id.textview1);
            price=(TextView)itemView.findViewById(R.id.textview2);
            description=(TextView)itemView.findViewById(R.id.textview3);

        }
    }
}
