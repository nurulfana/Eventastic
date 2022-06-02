package com.example.loginregister.FVenue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Guest;
import com.example.loginregister.recyclerView.Venue;
import com.example.loginregister.recyclerView.adapter.GuestRecyclerView;
import com.example.loginregister.recyclerView.adapter.VenueRecyclerView;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class VenueActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    List<Venue> allVenueInfor;
    VenueRecyclerView venueRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factivity_venue);

        recyclerView = findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(VenueActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);



        List<Venue> allVenueInfor = getallVenueInfor();
        venueRecyclerView = new VenueRecyclerView(VenueActivity.this,allVenueInfor);
        recyclerView.setAdapter(venueRecyclerView);

    }

//    private List<Venue> getallVenueInfor(){
//
//        List<Venue> allVenue = new ArrayList<Venue>();
//        allVenue.add(new Venue("booked","Dewan Canselor Tun Abdul Razak",R.drawable.dectar,"5.0"));
//        allVenue.add(new Venue("unbooked","Dewan Gemilang",R.drawable.dewangemilang,"5.0"));
//        allVenue.add(new Venue("booked","Dewan Kolej Aminuddin Baki",R.drawable.dewankab,"3.0"));
//        allVenue.add(new Venue("unbooked","Dewan Kolej Burhanuddin Helmi",R.drawable.dewankbh,"3.0"));
//        allVenue.add(new Venue("booked","Dewan Kolej Dato' Onn",R.drawable.dewankdo,"2.0"));
//        allVenue.add(new Venue("unbooked","Dewan Kolej Ibrahim Yaakob",R.drawable.dewankiy,"4.5"));
//        allVenue.add(new Venue("unbooked","Dewan Kolej Ibu Zain",R.drawable.dewankiz,"5.0"));
//        allVenue.add(new Venue("booked","Dewan Kolej Rahim Kajai",R.drawable.dewankrk,"3.0"));
//        allVenue.add(new Venue("unbooked","Dewan Kolej Ungku Omar",R.drawable.dewankuo,"2.5"));
//        allVenue.add(new Venue("unbooked","Dewan Kolej Pendeta Zaaba",R.drawable.dewanzaba,"5.0"));
//
//        return allVenue;
//
//
//    }

    private List<Venue> getallVenueInfor(){

        List<Venue> allVenue = new ArrayList<Venue>();
//        allGuest.add(new Guest(obj.getString("name"), obj.getString("category"), obj.getString("payment_status")));
//        allGuest.add(new Guest("name","category","payment_status"));
        //letak putdata
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[1];
                field[0] = "place_area";

                //Creating array for data
                String[] data = new String[1];
                data[0] = "UKM";

                // todo host
                PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/Venue/VenueListView.php", "POST", field, data);
                //lepak server
//                PutData putData = new PutData("https://eventastic.lepak.xyz/Venue/VenueListView.php", "POST", field, data);

                if (putData.startPut()) {

                    if (putData.onComplete()) {
//                progressBar.setVisibility(View.GONE);
                        String result = putData.getResult();

                        if (!result.equals("500")) {

                            try {


//                                JSONObject json = new JSONObject(result);
//                                JSONArray array = json.getJSONArray("GetCitiesResult");

                                System.out.println(result);
                                JSONArray array = new JSONArray(result);

//polissssssss
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    allVenue.add(new Venue(obj.getString("venue_name"),obj.getString("venue_book_status"),obj.getString("venue_ratings"),obj.getString("venue_image"),obj.getString("venue_longitude"),obj.getString("venue_latitude"),obj.getString("venue_search")));
//                                    allGuest.add(new Guest("name","category","payment_status"));
//                                    Toast.makeText(getApplicationContext(),obj.getString("Guest_id"), Toast.LENGTH_SHORT).show();
                                }
                                  recyclerView.setAdapter(venueRecyclerView);
//                                recyclerView.notify();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
//                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
//        allBeverage.add(new Beverage("Event 1"/**, R.drawable.flat_white**/));
            }
        });

        return allVenue;

    }

}