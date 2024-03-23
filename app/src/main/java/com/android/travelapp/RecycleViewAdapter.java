package com.android.travelapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private ArrayList<String> al_img_tour = new ArrayList<>();
    private ArrayList<String> al_name_tour = new ArrayList<>();
    private ArrayList<String> al_desc_tour = new ArrayList<>();
    private ArrayList<Integer> al_price_tour = new ArrayList<>();
    private ArrayList<String> al_location = new ArrayList<>();
    private Context context;

    public RecycleViewAdapter(ArrayList<String>al_img_tour, ArrayList<String>al_name_tour, ArrayList<String>al_desc_tour, ArrayList<Integer> al_price_tour, ArrayList<String> al_location, Context context){
        this.al_img_tour = al_img_tour;
        this.al_name_tour = al_name_tour;
        this.al_desc_tour = al_desc_tour;
        this.al_price_tour = al_price_tour;
        this.al_location = al_location;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desain_dashboard_adapter, parent, false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).asBitmap().load(al_img_tour.get(position)).into(holder.imgTour);
        holder.nameTour.setText(al_name_tour.get(position));
//        holder.descTour.setText(al_desc_tour.get(position));
        holder.priceTour.setText(Integer.toString(al_price_tour.get(position)));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TourDetail.class);
                intent.putExtra("imgTour", al_img_tour.get(position));
                intent.putExtra("nameTour", al_name_tour.get(position));
                intent.putExtra("descTour", al_desc_tour.get(position));
                intent.putExtra("locTour", al_location.get(position));
                intent.putExtra("priceTour", al_price_tour.get(position));

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return al_name_tour.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTour;
        TextView nameTour, priceTour, locTour;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTour = itemView.findViewById(R.id.img_tour);
            nameTour = itemView.findViewById(R.id.name_tour);
            locTour = itemView.findViewById(R.id.btn_img_loc);
            priceTour = itemView.findViewById(R.id.price_tour);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }
}
