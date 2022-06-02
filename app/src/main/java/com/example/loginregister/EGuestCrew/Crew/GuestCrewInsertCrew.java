package com.example.loginregister.EGuestCrew.Crew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Crew;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class GuestCrewInsertCrew extends AppCompatActivity {

    EditText et_nameCrew, et_quantityCrew,et_phoneNoGuestCrew,et_emailGuestCrew,et_notesGuestCrew;
    RadioGroup rg_categoryCrew, rg_progressCrew;
    RadioButton radioButtonCategory,radioButtonProgress;
    Button btn_saveChangesCrew;
    int id;
    String username;
    String crewid;

    String process="insert";
    Crew currentCrew;
    Intent extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eactivity_guest_crew_insert_crew);
        et_nameCrew = findViewById(R.id.et_nameCrew);
        et_quantityCrew = findViewById(R.id.et_quantityCrew);
        et_phoneNoGuestCrew = findViewById(R.id.et_phoneNoGuestCrew);
        et_emailGuestCrew = findViewById(R.id.et_emailGuestCrew);
        et_notesGuestCrew = findViewById(R.id.et_notesGuestCrew);
        rg_categoryCrew = findViewById(R.id.rg_categoryCrew);
        rg_progressCrew = findViewById(R.id.rg_progressCrew);
        btn_saveChangesCrew = findViewById(R.id.btn_saveChangesCrew);

        extra = getIntent();
        id = extra.getIntExtra("id",0);
        username = extra.getStringExtra("username");
        if(extra.hasExtra("edit")){

            process = "update";
            currentCrew = (Crew) getIntent().getSerializableExtra("edit");
            crewid = String.valueOf(currentCrew.getCrew_id());

            et_nameCrew.setText(currentCrew.getName());
            et_quantityCrew.setText(currentCrew.getQuantity());
            et_phoneNoGuestCrew.setText(currentCrew.getPhone());
            et_emailGuestCrew.setText(currentCrew.getEmail());
            et_notesGuestCrew.setText(currentCrew.getNotes());

            switch(currentCrew.getCategory()){
                case "Protocol": rg_categoryCrew.check(rg_categoryCrew.getChildAt(0).getId()); break;
                case "Media & Publicity": rg_categoryCrew.check(rg_categoryCrew.getChildAt(1).getId()); break;
                case "Entertainment": rg_categoryCrew.check(rg_categoryCrew.getChildAt(2).getId()); break;
                case "Security": rg_categoryCrew.check(rg_categoryCrew.getChildAt(3).getId()); break;
                case "Technical": rg_categoryCrew.check(rg_categoryCrew.getChildAt(4).getId()); break;
                case "Others": rg_categoryCrew.check(rg_categoryCrew.getChildAt(5).getId()); break;

            }
            switch(currentCrew.getProgress()){
                case "Yes": rg_progressCrew.check(rg_progressCrew.getChildAt(0).getId()); break;
                case "No": rg_progressCrew.check(rg_progressCrew.getChildAt(1).getId()); break;

            }

        }else if(currentCrew==null){

            crewid = "0";

        }

        btn_saveChangesCrew.setOnClickListener(view -> {

            //for radio group

            String name,  category, quantity, progress, phone, email, notes ;

            int radioIdCategory = rg_categoryCrew.getCheckedRadioButtonId();
            radioButtonCategory = findViewById(radioIdCategory);

            int radioIdProgress = rg_progressCrew.getCheckedRadioButtonId();
            radioButtonProgress = findViewById(radioIdProgress);


//                username = String.valueOf(textInputEditTextUsername.getText());
            name = String.valueOf(et_nameCrew.getText());
            category = String.valueOf(radioButtonCategory.getText());
            quantity = String.valueOf(et_quantityCrew.getText());
            progress = String.valueOf(radioButtonProgress.getText());
            phone = String.valueOf(et_phoneNoGuestCrew.getText());
            email = String.valueOf(et_emailGuestCrew.getText());
            notes = String.valueOf(et_notesGuestCrew.getText());



            if(!name.equals("") && !category.equals("") && !quantity.equals("") && !progress.equals("") ) {

                //Start ProgressBar first (Set visibility VISIBLE)
//                    progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[11];
                        field[0] = "name";
                        field[1] = "category";
                        field[2] = "quantity";
                        field[3] = "progress";
                        field[4] = "phone";
                        field[5] = "email";
                        field[6] = "notes";
                        field[7] = "process";
                        field[8] = "username";
                        field[9] = "event_id";
                        field[10] = "crew_id";

                        //Creating array for data
                        String[] data = new String[11];
                        data[0] = name;
                        data[1] = category;
                        data[2] = quantity;
                        data[3] = progress;
                        data[4] = phone;
                        data[5] = email;
                        data[6] = notes;
                        data[7] = process;
                        data[8] = username;
                        data[9] = String.valueOf(id);
                        data[10] = crewid;
                        // todo host
                        PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/GuestCrew/crewListView.php", "POST", field, data);
                        //lepak server
//                        PutData putData = new PutData("https://eventastic.lepak.xyz/GuestCrew/crewListView.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
//                                    progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if(result.equals("200")){

//                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    finish();

                                }else{
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        //End Write and Read data with URL
                    }
                });
            }
            else{
                Toast.makeText(getApplicationContext(), "All fields required",Toast.LENGTH_SHORT).show();
            }

        });
    }

//
}
