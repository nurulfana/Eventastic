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

import com.example.loginregister.EGuestCrew.Guest.GuestCrewInsertGuest;
import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Guest;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;

public class GuestRecyclerView extends RecyclerView.Adapter<GuestRecyclerView.GuestViewHolder> {

    public List<Guest> GuestList;
    private Context context;
    String username;

    public GuestRecyclerView(Context context, List<Guest> GuestList,String username) {
        this.context=context;
        this.GuestList= GuestList;
        this.username= username;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Guest_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rguest_row,null);

        GuestViewHolder GuestVH = new GuestViewHolder(Guest_row);
        return GuestVH;
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {

        holder.tv_nameGuest.setText(GuestList.get(position).getName());
        holder.tv_guestInvitation.setText(GuestList.get(position).getProgress());
        holder.tv_guestQuantity.setText(GuestList.get(position).getQuantity());

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
                            field[1] = "guest_id";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = "delete";
                            data[1] = String.valueOf(GuestList.get(position).getGuestid());
                            // todo host
                            PutData putData = new PutData("http://"+context.getString(R.string.localhost)+"/API-Eventastic/GuestCrew/guestListView.php", "POST", field, data);
//                            PutData putData = new PutData("https://eventastic.lepak.xyz/GuestCrew/guestListView.php", "POST", field, data);

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
        return GuestList.size();
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_nameGuest,tv_guestInvitation,tv_guestQuantity;
        public Button button_delete_booking;


        public GuestViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_nameGuest = itemView.findViewById(R.id.tv_nameCrew);
            tv_guestInvitation = itemView.findViewById(R.id.tv_crewProgress);
            tv_guestQuantity = itemView.findViewById(R.id.tv_crewQuantity);
            button_delete_booking = itemView.findViewById(R.id.button_delete_booking);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(),"Beverage Name: " + beverageList.get(getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
//
            Guest currentGuest = GuestList.get(getAdapterPosition());
            Intent intent = new Intent(view.getContext(), GuestCrewInsertGuest.class);
            intent.putExtra("id", GuestList.get(getAdapterPosition()).getEventid());
            intent.putExtra("username", username);
            intent.putExtra("edit", currentGuest);

            view.getContext().startActivity(intent);
        }
    }
}
