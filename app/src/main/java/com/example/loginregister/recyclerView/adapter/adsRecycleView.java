package com.example.loginregister.recyclerView.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.DBooking.AddBookingDetail;
import com.example.loginregister.GAds.AddAdsDetail;
import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Ads;
import com.example.loginregister.recyclerView.Booking;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;

public class adsRecycleView extends RecyclerView.Adapter<adsRecycleView.AdsViewHolder> {
    public List<Ads> adsList;
    private Context context;
    String username;
    int id;

    public adsRecycleView(Context context, List<Ads> adsList,String username,int id) {
        this.context=context;
        this.adsList= adsList;
        this.username= username;
        this.id= id;
    }

    @NonNull
    @Override
    public AdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ads_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rads_row,null);

        AdsViewHolder adsVH = new AdsViewHolder(ads_row);
        return adsVH;
    }

    @Override
    public void onBindViewHolder(@NonNull AdsViewHolder holder, int position) {

        holder.tv_adsName.setText(adsList.get(position).getAname());
        holder.tv_adsCategory.setText(adsList.get(position).getAcategory());
        holder.tv_adsStatus.setText(adsList.get(position).getAstatus());

        holder.button_delete_booking.setOnClickListener(view -> {


            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            // Set a title for alert dialog
            builder.setTitle("Are you sure yo delete this ?");

            // Ask the final question
            builder.setMessage("Deleted data cannot be undone.");

            // Set the alert dialog yes button click listener
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override

                public void onClick(DialogInterface dialog, int which) {

                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "process";
                            field[1] = "ads_id";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = "delete";
                            data[1] = String.valueOf(adsList.get(position).getAds_id());
                            // todo host
                            PutData putData = new PutData("http://"+context.getString(R.string.localhost)+"/API-Eventastic/Ads/AdsListView.php", "POST", field, data);
//                            PutData putData = new PutData("https://eventastic.lepak.xyz/Ads/AdsListView.php", "POST", field, data);

                            if (putData.startPut()) {

                                if (putData.onComplete()) {
//                progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                }
                            }
//        allBeverage.add(new Beverage("Event 1"/**, R.drawable.flat_white**/));
                        }
                    });

                }
            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();

        });
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }

    public class AdsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_adsName,tv_adsCategory,tv_adsStatus;
        public Button button_delete_booking;


        public AdsViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_adsName = itemView.findViewById(R.id. tv_adsName);
            tv_adsCategory = itemView.findViewById(R.id. tv_adsCategory);
            tv_adsStatus = itemView.findViewById(R.id.tv_adsStatus);
            button_delete_booking = itemView.findViewById(R.id.button_delete_booking);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Ads currentAds = adsList.get(getAdapterPosition());
            Intent intent = new Intent(view.getContext(), AddAdsDetail.class);
            intent.putExtra("id", adsList.get(getAdapterPosition()).getEventid());
            intent.putExtra("username", username);
            intent.putExtra("edit", currentAds);
//            intent.putExtra("bookingName", bookingList.get(getAdapterPosition()).getName());

            view.getContext().startActivity(intent);
        }
    }
}

