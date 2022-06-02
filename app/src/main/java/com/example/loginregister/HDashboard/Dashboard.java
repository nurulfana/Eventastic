package com.example.loginregister.HDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginregister.CMainMenu.BeverageDetailActivity;
import com.example.loginregister.DBooking.BookingActivity;
import com.example.loginregister.EGuestCrew.GuestCrew;
import com.example.loginregister.GAds.AdsActivity;
import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Dashboardd;
import com.example.loginregister.recyclerView.Venue;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    public TextView tv_booking_not_paid,tv_booking_paid,tv_crew_confirmed,tv_guest_sent,tv_crew_pending,tv_crew_confirm,tv_ads_confirmed,tv_ads_unconfirmed,view_budget_spent,view_budget_balanced;
    public TextView view_booking_notpaid,view_booking_paid,view_guest_pending,view_guest_sent,view_crew_pending,view_crew_sent,view_ads_pending,view_ads_confirmed;
    int id;
    String username;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hactivity_dashboard);

        if(savedInstanceState==null) {
            Bundle extra = getIntent().getExtras();
            id = extra.getInt("id");
            username = extra.getString("username");
        }else{
            id=(int)savedInstanceState.getSerializable("id");
            username=(String)savedInstanceState.getSerializable("username");
        }

        tv_booking_not_paid = findViewById(R.id.tv_booking_not_paid);
        tv_booking_paid = findViewById(R.id.tv_booking_paid);
        tv_crew_confirmed =findViewById(R.id.tv_crew_confirmed);
        tv_guest_sent = findViewById(R.id.tv_guest_sent);
        tv_crew_pending = findViewById(R.id.tv_crew_pending);
        tv_crew_confirm = findViewById(R.id.tv_crew_confirm);
        tv_ads_confirmed = findViewById(R.id.tv_ads_confirmed);
        tv_ads_unconfirmed = findViewById(R.id.tv_ads_unconfirmed);

        view_budget_spent= findViewById(R.id.view_budget_spent);
        view_budget_balanced= findViewById(R.id.view_budget_balanced);

        getallDashboardInfor();

        view_booking_notpaid = findViewById(R.id.view_booking_notpaid);
        view_booking_paid = findViewById(R.id.view_booking_paid);
        view_guest_pending =findViewById(R.id.view_guest_pending);
        view_guest_sent = findViewById(R.id.view_guest_sent);
        view_crew_pending = findViewById(R.id.view_crew_pending);
        view_crew_sent = findViewById(R.id.view_crew_sent);
        view_ads_pending = findViewById(R.id.view_ads_pending);
        view_ads_confirmed = findViewById(R.id.view_ads_confirmed);




        view_booking_notpaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(Dashboard.this, BookingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("username", username);
                intent.putExtra("type", "Not Paid Yet");
                startActivity(intent);

            }
        });
        view_booking_paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Dashboard.this, BookingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("username", username);
                intent.putExtra("type", "Paid");
                startActivity(intent);
            }
        });
        view_guest_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Dashboard.this, GuestDashboard.class);
                intent.putExtra("id", id);
                intent.putExtra("username", username);
                intent.putExtra("type", "Yes");
                startActivity(intent);
            }
        });
        view_guest_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Dashboard.this, GuestDashboard.class);
                intent.putExtra("id", id);
                intent.putExtra("username", username);
                intent.putExtra("type", "No");
                startActivity(intent);
            }
        });
        view_crew_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Dashboard.this, CrewDashboard.class);
                intent.putExtra("id", id);
                intent.putExtra("username", username);
                intent.putExtra("type", "No");
                startActivity(intent);
            }
        });
        view_crew_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Dashboard.this, CrewDashboard.class);
                intent.putExtra("id", id);
                intent.putExtra("username", username);
                intent.putExtra("type", "Yes");
                startActivity(intent);
            }
        });
        view_ads_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Dashboard.this, AdsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("username", username);
                intent.putExtra("type", "Not Finished");
                startActivity(intent);
            }
        });
        view_ads_confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Dashboard.this, AdsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("username", username);
                intent.putExtra("type", "Finished");
                startActivity(intent);
            }
        });

    }

    private List<Dashboardd> getallDashboardInfor()  {


        List<Dashboardd> allDashboard = new ArrayList<Dashboardd>();
//        allDashboard.add(new Dashboard(obj.getString("name"), obj.getString("category"), obj.getString("payment_status")));
//        allDashboard.add(new Dashboard("name","category","payment_status"));
        //letak putdata
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[2];
                field[0] = "username";
                field[1] = "event_id";

                //Creating array for data
                String[] data = new String[2];
                data[0] = username;
                data[1] = String.valueOf(id);
                // todo host
                PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/Dashboard/dashboard.php", "POST", field, data);
                //lepak server
//                PutData putData = new PutData("https://eventastic.lepak.xyz/Dashboard/dashboard.php", "POST", field, data);

                if (putData.startPut()) {

                    if (putData.onComplete()) {
//                progressBar.setVisibility(View.GONE);
                        String result = putData.getResult();
//                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                        if (!result.equals("500")) {




//                                JSONObject json = new JSONObject(result);
//                                JSONArray array = json.getJSONArray("GetCitiesResult");
//                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//                                System.out.println(result);
//
                            String[] tokens = result.split(",");
//
                                tv_booking_paid.setText(String.valueOf(tokens[0]));
                                tv_booking_not_paid.setText(String.valueOf(tokens[1]));
                                tv_guest_sent.setText(String.valueOf(tokens[2]));
                                tv_crew_confirmed.setText(String.valueOf(tokens[3]));
                                tv_crew_confirm.setText(String.valueOf(tokens[4]));
                                tv_crew_pending.setText(String.valueOf(tokens[5]));
                                tv_ads_confirmed.setText(String.valueOf(tokens[6]));
                                tv_ads_unconfirmed.setText(String.valueOf(tokens[7]));
                                view_budget_spent.setText(String.valueOf(tokens[8]));
                                view_budget_balanced.setText(String.valueOf(tokens[9]));


                        } else {
//                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
//        allBeverage.add(new Beverage("Event 1"/**, R.drawable.flat_white**/));
            }
        });

        return allDashboard;

    }
}