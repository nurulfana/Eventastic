package com.example.loginregister.recyclerView.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.recyclerView.Beverage;
import com.example.loginregister.BCreateEvent.InsertEventDetails;
import com.example.loginregister.R;

import java.util.List;
//Sini tempat add event bok
public class BeverageRecyclerViewAdapter extends RecyclerView.Adapter<BeverageRecyclerViewAdapter.BeverageViewHolder> {

    public List<Beverage> beverageList;
    private Context context;
    public String username;

    public BeverageRecyclerViewAdapter(Context context, List<Beverage> beverageList, String username) {
        this.context=context;
        this.beverageList= beverageList;
        this.username = username;
    }

    @NonNull
    @Override
    public BeverageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View beverage_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rbeverage_row,null);

        BeverageViewHolder beverageVH = new BeverageViewHolder(beverage_row);
        return beverageVH;
    }

    @Override
    public void onBindViewHolder(@NonNull BeverageViewHolder holder, int position) {

        holder.tvBeverageName.setText(beverageList.get(position).getName());
//        holder.imgViewBeverageImage.setImageResource(beverageList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return beverageList.size();
    }

    public class BeverageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvBeverageName;
//        public ImageView imgViewBeverageImage;


        public BeverageViewHolder(@NonNull View itemView) {

            super(itemView);
            tvBeverageName = itemView.findViewById(R.id.tv_nameCrew);
//            imgViewBeverageImage = itemView.findViewById(R.id. img_beverage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(),"Beverage Name: " + beverageList.get(getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(view.getContext(), InsertEventDetails.class);
            intent.putExtra("event_id", beverageList.get(getAdapterPosition()).getId());
            intent.putExtra("username",username);

            view.getContext().startActivity(intent);
        }
    }
}
