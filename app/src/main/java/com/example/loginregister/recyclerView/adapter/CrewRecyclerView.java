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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.EGuestCrew.Crew.GuestCrewInsertCrew;
import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Crew;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;

public class CrewRecyclerView extends RecyclerView.Adapter<CrewRecyclerView.CrewViewHolder> {

    String username;
    public List<Crew> CrewList;
    private Context context;

    public CrewRecyclerView(Context context, List<Crew> CrewList,String username) {
        this.context=context;
        this.CrewList= CrewList;
        this.username = username;
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Crew_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcrew_row,null);

        CrewViewHolder CrewVH = new CrewViewHolder(Crew_row);
        return CrewVH;
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {

        holder.tv_nameCrew.setText(CrewList.get(position).getName());
        holder.tv_crewProgress.setText(CrewList.get(position).getProgress());
        holder.tv_crewQuantity.setText(CrewList.get(position).getQuantity());

        holder.button_delete_booking.setOnClickListener(view -> {


            Toast.makeText(view.getContext(),String.valueOf(CrewList.get(position).getCrew_id()),Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            // Set a title for alert dialog
            builder.setTitle("Are you sure to delete this ?");

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
                            field[1] = "crew_id";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = "delete";
                            data[1] = String.valueOf(CrewList.get(position).getCrew_id());
                            // todo host
                            PutData putData = new PutData("http://"+context.getString(R.string.localhost)+"/API-Eventastic/GuestCrew/crewListView.php", "POST", field, data);
//                            PutData putData = new PutData("https://eventastic.lepak.xyz/GuestCrew/crewListView.php", "POST", field, data);

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
        return CrewList.size();
    }

    public class CrewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_nameCrew,tv_crewProgress,tv_crewQuantity;
        public Button button_delete_booking;


        public CrewViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_nameCrew = itemView.findViewById(R.id.tv_nameCrew);
            tv_crewProgress = itemView.findViewById(R.id.tv_crewProgress);
            tv_crewQuantity = itemView.findViewById(R.id.tv_crewQuantity);
            button_delete_booking = itemView.findViewById(R.id.button_delete_booking);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(),"Beverage Name: " + beverageList.get(getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
            Crew currentCrew = CrewList.get(getAdapterPosition());
            Intent intent = new Intent(view.getContext(), GuestCrewInsertCrew.class);
            intent.putExtra("id", CrewList.get(getAdapterPosition()).getEvent_id());
            intent.putExtra("username", username);
            intent.putExtra("edit", currentCrew);

            view.getContext().startActivity(intent);
        }
    }
}
