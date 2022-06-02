package com.example.loginregister.BCreateEvent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.loginregister.CMainMenu.BeverageDetailActivity;
import com.example.loginregister.R;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;
//create event
public class InsertEventDetails extends AppCompatActivity {

    TextInputEditText eventName,eventDate,eventTime,eventBudget;
    Button buttonSaveChanges;

    int id;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bactivity_insert_event_details);


        eventName =findViewById(R.id.eventName);
        eventDate =findViewById(R.id.eventDate);
        eventTime =findViewById(R.id.eventTime);
        eventBudget =findViewById(R.id.eventBudget);
        buttonSaveChanges =findViewById(R.id.buttonSaveChanges);

        if(savedInstanceState==null) {
            Bundle extra = getIntent().getExtras();

            id = extra.getInt("event_id");
            username = extra.getString("username");
        }else{
            id=(int)savedInstanceState.getSerializable("event_id");
            username=(String)savedInstanceState.getSerializable("username");
        }
//        Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_LONG).show();
        fetchInfo();



        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BeverageDetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("event_name", eventName.getText().toString());
                intent.putExtra("event_date", eventDate.getText().toString());
                intent.putExtra("event_time", eventTime.getText().toString());
                intent.putExtra("event_budget", eventBudget.getText().toString());
                intent.putExtra("username", username);
                startActivity(intent);
                updateEventDetails();
            }
        });
    }

    private void updateEventDetails() {

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[7];
                field[0] = "username";
                field[1] = "process";
                field[2] = "event_id";
                field[3] = "event_name";
                field[4] = "event_date";
                field[5] = "event_time";
                field[6] = "event_budget";

                //Creating array for data
                String[] data = new String[7];
                data[0] = username;
                data[1] = "insert";
                data[2] = String.valueOf(id);
                data[3] = eventName.getText().toString();
                data[4] = eventDate.getText().toString();
                data[5] = eventTime.getText().toString();
                data[6] = eventBudget.getText().toString();
                // todo host
                PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/Event/updateEvent.php", "POST", field, data);
                //lepak server
//                PutData putData = new PutData("https://eventastic.lepak.xyz/Event/updateEvent.php", "POST", field, data);

                if (putData.startPut()) {

                    if (putData.onComplete()) {
//                progressBar.setVisibility(View.GONE);
                        String result = putData.getResult();

                        if (!result.equals("500")) {


                        } else {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
//        allBeverage.add(new Beverage("Event 1"/**, R.drawable.flat_white**/));
            }
        });


    }


    protected void fetchInfo(){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[3];
                field[0] = "username";
                field[1] = "process";
                field[2] = "event_id";

                //Creating array for data
                String[] data = new String[3];
                //TODO TUKA FANAE
                data[0] = username;
                data[1] = "list";
                data[2] = String.valueOf(id);
                // todo host
                PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/Event/updateEvent.php", "POST", field, data);
                //lepak server
//                PutData putData = new PutData("https://eventastic.lepak.xyz/Event/updateEvent.php", "POST", field, data);

                if (putData.startPut()) {

                    if (putData.onComplete()) {
//                progressBar.setVisibility(View.GONE);
                        String result = putData.getResult();

                        if (!result.equals("500")) {

                            try {


                                JSONObject json = new JSONObject(result);
                                eventName.setText(json.getString("event_name"));
                                eventDate.setText(json.getString("event_date"));
                                eventTime.setText(json.getString("event_time"));
                                eventBudget.setText(json.getString("event_budget"));

                                } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
//        allBeverage.add(new Beverage("Event 1"/**, R.drawable.flat_white**/));
            }
        });


    }




    }
