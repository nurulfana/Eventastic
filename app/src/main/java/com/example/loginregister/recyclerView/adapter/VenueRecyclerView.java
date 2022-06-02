package com.example.loginregister.recyclerView.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Venue;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;
import java.util.Locale;

public class VenueRecyclerView extends RecyclerView.Adapter<VenueRecyclerView.VenueViewHolder> {

    public List<Venue> VenueList;
    private Context context;
    String url = "https://api.whatsapp.com/send?phone=601156354196";
    Intent intent = new Intent(Intent.ACTION_VIEW);


    public VenueRecyclerView(Context context, List<Venue> VenueList) {
        this.context=context;
        this.VenueList= VenueList;
    }

    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Venue_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_row,null);

        VenueViewHolder VenueVH = new VenueViewHolder(Venue_row);
        return VenueVH;
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {

        Venue venueList = VenueList.get(position);

        holder.Dewan.setText(venueList.getDewan());
        holder.book_status.setText(venueList.getBookstatus());
        holder.ratings.setText(venueList.getRatings());
//        holder.venue_image.setImageResource(VenueList.get(position).getImage());
//                // todo host
        Picasso.get().load("http://"+context.getString(R.string.localhost)+"/API-Eventastic/dewan/"+VenueList.get(position).getImage()).into(holder.venue_image);
//        Picasso.get().load("https://eventastic.lepak.xyz//dewan/"+VenueList.get(position).getImage()).into(holder.venue_image);


        if (venueList.getBookstatus().equals("Unbooked")) {

            holder.BookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    intent.setData(Uri.parse(url));
                    view.getContext().startActivity(intent);

                }
            });
        }else if(venueList.getBookstatus().equals("Booked")){
            holder.BookButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return VenueList.size();
    }

    public class VenueViewHolder extends RecyclerView.ViewHolder{

        public TextView Dewan,book_status,ratings;
        public ImageView venue_image;
        public Button BookButton;
        public CardView hotelCard;


        public VenueViewHolder(@NonNull View itemView) {

            super(itemView);

            Dewan = itemView.findViewById(R.id.Dewan);
            book_status = itemView.findViewById(R.id.book_status);
            ratings = itemView.findViewById(R.id.ratings);
            venue_image = itemView.findViewById(R.id.venue_image);
            BookButton = itemView.findViewById(R.id.BookButton);
            hotelCard = itemView.findViewById(R.id.hotelCard);

            hotelCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String location = VenueList.get(getAdapterPosition()).getLatitude()+","+VenueList.get(getAdapterPosition()).getLongitude();
                    Uri gmmIntentUri = Uri.parse("geo:"+location+"?q="+VenueList.get(getAdapterPosition()).getSearch());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    view.getContext().startActivity(mapIntent);
                }
            });

        }

//        @Override
//        public void onClick(View view) {
//
//        }
    }
}
