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

import com.example.loginregister.DBooking.AddBookingDetail;
import com.example.loginregister.DBooking.BookingActivity;
import com.example.loginregister.recyclerView.Booking;
import com.example.loginregister.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class bookingRecycleView extends RecyclerView.Adapter<bookingRecycleView.BookingViewHolder> {

    public List<Booking> bookingList;
    private Context context;
    String eventid;
    String username;
    int id;

    public bookingRecycleView(Context context, List<Booking> bookingList,String username,int id) {
        this.context=context;
        this.bookingList= bookingList;
        this.username = username;
        this.id = id;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View booking_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rbooking_row,null);

        BookingViewHolder bookingVH = new BookingViewHolder(booking_row);
        return bookingVH;

    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {

        holder.tv_name.setText(bookingList.get(position).getName());
        holder.tv_bookingBategory.setText(bookingList.get(position).getCategory());
        holder.tv_paymentStatus.setText(bookingList.get(position).getPayment_status());

        holder.button_delete_booking.setOnClickListener(view -> {

            Toast.makeText(view.getContext(),String.valueOf(bookingList.get(position).getBookingid()),Toast.LENGTH_SHORT).show();
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
                            field[1] = "booking_id";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = "delete";
                            data[1] = String.valueOf(bookingList.get(position).getBookingid());
                            // todo host
                            PutData putData = new PutData("http://"+context.getString(R.string.localhost)+"/API-Eventastic/Booking/BookingListView.php", "POST", field, data);
//                            PutData putData = new PutData("https://eventastic.lepak.xyz/Booking/BookingListView.php", "POST", field, data);

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
        return bookingList.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_name,tv_bookingBategory,tv_paymentStatus;
        public Button button_delete_booking;


        public BookingViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_nameCrew);
            tv_bookingBategory = itemView.findViewById(R.id.tv_crewProgress);
            tv_paymentStatus = itemView.findViewById(R.id.tv_crewQuantity);
            button_delete_booking = itemView.findViewById(R.id.button_delete_booking);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(),"Beverage Name: " + beverageList.get(getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
//
            Booking currentBooking = bookingList.get(getAdapterPosition());
            Intent intent = new Intent(view.getContext(), AddBookingDetail.class);
            intent.putExtra("id", bookingList.get(getAdapterPosition()).getEventid());
            intent.putExtra("username", username);
            intent.putExtra("edit", currentBooking);
//            intent.putExtra("bookingName", bookingList.get(getAdapterPosition()).getName());

            view.getContext().startActivity(intent);
        }
    }
}
