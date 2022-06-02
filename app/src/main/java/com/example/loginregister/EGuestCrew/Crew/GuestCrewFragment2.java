package com.example.loginregister.EGuestCrew.Crew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Crew;
import com.example.loginregister.recyclerView.adapter.CrewRecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GuestCrewFragment2 extends Fragment {

    int id;
    String username;
    String type;

    FloatingActionButton fbtn_createCrew;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    List<Crew> allCrewInfor;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eguestcrew_fragment2_crew_layout,container,false);

        if(savedInstanceState==null) {
            Bundle extra = getActivity().getIntent().getExtras();
            id = extra.getInt("id");
            username = extra.getString("username");
            type = extra.getString("type");
        }else{
            id=(int)savedInstanceState.getSerializable("id");
            username=(String)savedInstanceState.getSerializable("username");
            type=(String)savedInstanceState.getSerializable("type");
        }

        recyclerView = view.findViewById(R.id. recycler_view);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        allCrewInfor = getallCrewInfor();

        fbtn_createCrew = view.findViewById(R.id.fbtn_createCrew);

        fbtn_createCrew.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity().getApplication(), GuestCrewInsertCrew.class);

            intent.putExtra("id", id);
            intent.putExtra("username", username);

            startActivity(intent);
        });

        return view;
    }

    private List<Crew> getallCrewInfor()  {


        List<Crew> allCrew = new ArrayList<Crew>();
//        allCrew.add(new Crew(obj.getString("name"), obj.getString("category"), obj.getString("payment_status")));
//        allCrew.add(new Crew("name","category","payment_status"));
        //letak putdata
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[4];
                field[0] = "username";
                field[1] = "process";
                field[2] = "event_id";
                field[3] = "type";

                //Creating array for data
                String[] data = new String[4];
                data[0] = username;
                data[1] = "list";
                data[2] = String.valueOf(id);
                data[3] = type;
                // todo host
                PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/GuestCrew/crewListView.php", "POST", field, data);
                //lepak server
//                PutData putData = new PutData("https://eventastic.lepak.xyz/GuestCrew/crewListView.php", "POST", field, data);

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


                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    allCrew.add(new Crew(obj.getInt("crew_id"),obj.getString("event_id"),obj.getString("name"), obj.getString("category"),obj.getString("quantity"),obj.getString("progress"), obj.getString("phone"), obj.getString("email"), obj.getString("notes")));
//                                    allCrew.add(new Crew("name","category","payment_status"));
//                                    Toast.makeText(getApplicationContext(),obj.getString("Crew_id"), Toast.LENGTH_SHORT).show();
                                }
                                CrewRecyclerView CrewRecyclerView = new CrewRecyclerView(getContext(),allCrewInfor,username);
                                recyclerView.setAdapter(CrewRecyclerView);
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

        return allCrew;

    }
}
