package com.example.loginregister.BCreateEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Beverage;
import com.example.loginregister.recyclerView.adapter.BeverageRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
//create event
public class MainActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
//    public FloatingActionButton buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bactivity_main);

        RecyclerView recyclerView = findViewById(R.id. recycler_view);

//        buttonAdd =findViewById(R.id.btnAddEvent);
//        kirim = (Button) rootView.findViewById(R.id.kirim);

//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            String name;
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this,InsertEventDetails.class);
//
//
//                startActivity(intent);
//
//
//
//            }
//        });

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);


        List<Beverage> allBeverageInfor = getallBeverageInfor();

        Intent extra = getIntent();


        BeverageRecyclerViewAdapter beverageRecyclerViewAdapter = new BeverageRecyclerViewAdapter(MainActivity.this,allBeverageInfor, extra.getStringExtra("username"));
        recyclerView.setAdapter(beverageRecyclerViewAdapter);

    }

    private List<Beverage> getallBeverageInfor(){

        List<Beverage> allBeverage = new ArrayList<Beverage>();
        allBeverage.add(new Beverage("Event 1",1));
        allBeverage.add(new Beverage("Event 2",2));
        allBeverage.add(new Beverage("Event 3",3));
//        allBeverage.add(new Beverage("Latte"/**, R.drawable.latte_2**/));
//        allBeverage.add(new Beverage("Long Black"/**, R.drawable.long_black_2**/));
//
//        allBeverage.add(new Beverage("Macchiato"/**, R.drawable.macchiato**/));
//        allBeverage.add(new Beverage("Mochaccino"/**, R.drawable.mochaccino**/));
//        allBeverage.add(new Beverage("Espresso"/**, R.drawable.espresso_1**/));
//        allBeverage.add(new Beverage("Expresso"/**, R.drawable.espresso_2**/));
//        allBeverage.add(new Beverage("Earl Grey"/**, R.drawable.earl_grey**/));
//
//        allBeverage.add(new Beverage("Grean Tea"/**, R.drawable.green_tea**/));
//        allBeverage.add(new Beverage("Capuccino"/**, R.drawable.cappuccino_1**/));
//        allBeverage.add(new Beverage("Capuccino"/**, R.drawable.cappuccino_2**/));

        return allBeverage;

    }

}