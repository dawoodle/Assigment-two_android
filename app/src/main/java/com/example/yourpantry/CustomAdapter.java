package com.example.yourpantry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHodler> {

    private final Context context;
    Activity activity;
    private final ArrayList<String> food_title, food_type, food_date;

    CustomAdapter(Activity activity,Context context, ArrayList food_id, ArrayList food_title, ArrayList food_type,
                  ArrayList food_date){
        this.activity = activity;
        this.context = context;
        this.food_title = food_title;
        this.food_type = food_type;
        this.food_date = food_date;

    }
    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.food_title_txt.setText(String.valueOf(food_title.get(position)));
        holder.food_type_txt.setText(String.valueOf(food_type.get(position)));
        holder.food_date_txt.setText(String.valueOf(food_date.get(position)));
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateDeleteActivity.class);
            intent.putExtra("title", String.valueOf(food_title.get(position)));
            intent.putExtra("type", String.valueOf(food_type.get(position)));
            intent.putExtra("date", String.valueOf(food_date.get(position)));
            activity.startActivityForResult(intent,1);

        });


    }

    @Override
    public int getItemCount() {
        return food_title.size();
    }

   public static class MyViewHodler extends RecyclerView.ViewHolder {

        TextView food_title_txt, food_type_txt, food_date_txt;
        LinearLayout mainLayout;


        MyViewHodler(@NonNull View itemView) {
            super(itemView);
            food_title_txt = itemView.findViewById(R.id.food_title_txt);
            food_type_txt = itemView.findViewById(R.id.food_type_txt);
            food_date_txt = itemView.findViewById(R.id.food_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
