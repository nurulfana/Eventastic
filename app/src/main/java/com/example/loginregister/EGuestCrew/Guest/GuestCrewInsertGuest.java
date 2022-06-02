package com.example.loginregister.EGuestCrew.Guest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Guest;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

//Insert Guest
public class GuestCrewInsertGuest extends AppCompatActivity {

    EditText et_nameGuest, et_quantityGuest,et_phoneNoGuestCrew,et_emailGuestCrew,et_notesGuestCrew;
    RadioGroup rg_genderGuest, rg_categoryGuest,rg_progressGuest;
    RadioButton radioButtonGender,radioButtonCategory,radioButtonProgress;
    Button btn_saveChangesGuest;
    int id;
    String username;
    String guestid;

    String process="insert";

    Guest currentGuest;

    Intent extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eactivity_guest_crew_insert_guest);

        et_nameGuest = findViewById(R.id.et_nameGuest);
        et_quantityGuest = findViewById(R.id.et_quantityGuest);
        et_phoneNoGuestCrew = findViewById(R.id.et_phoneNoGuestCrew);
        et_emailGuestCrew = findViewById(R.id.et_emailGuestCrew);
        et_notesGuestCrew = findViewById(R.id.et_notesGuestCrew);
        rg_genderGuest = findViewById(R.id.rg_genderGuest);
        rg_categoryGuest = findViewById(R.id.rg_categoryGuest);
        rg_progressGuest = findViewById(R.id.rg_progressGuest);
        btn_saveChangesGuest = findViewById(R.id.btn_saveChangesGuest);

        extra = getIntent();
        id = extra.getIntExtra("id",0);
        username = extra.getStringExtra("username");
        if(extra.hasExtra("edit")){

            process = "update";
            currentGuest = (Guest) getIntent().getSerializableExtra("edit");
            guestid = String.valueOf(currentGuest.getGuestid());

            et_nameGuest.setText(currentGuest.getName());
            et_quantityGuest.setText(currentGuest.getQuantity());
            et_phoneNoGuestCrew.setText(currentGuest.getPhone());
            et_emailGuestCrew.setText(currentGuest.getEmail());
            et_notesGuestCrew.setText(currentGuest.getNotes());

            switch(currentGuest.getGender()){
                case "Male": rg_genderGuest.check(rg_genderGuest.getChildAt(0).getId()); break;
                case "Female": rg_genderGuest.check(rg_genderGuest.getChildAt(1).getId()); break;
                }
            switch(currentGuest.getCategory()){
                case "Adult": rg_categoryGuest.check(rg_categoryGuest.getChildAt(0).getId()); break;
                case "Teen": rg_categoryGuest.check(rg_categoryGuest.getChildAt(1).getId()); break;
                case "Children": rg_categoryGuest.check(rg_categoryGuest.getChildAt(2).getId()); break;

            }
            switch(currentGuest.getProgress()){
                case "Yes": rg_progressGuest.check(rg_progressGuest.getChildAt(0).getId()); break;
                case "No": rg_progressGuest.check(rg_progressGuest.getChildAt(1).getId()); break;

            }

        }else if(currentGuest==null){

            guestid = "0";

        }

        btn_saveChangesGuest.setOnClickListener(view -> {

            //for radio group

            String name, gender, category, quantity, progress, phone, email, notes ;

            int radioIdGender = rg_genderGuest.getCheckedRadioButtonId();
            radioButtonGender = findViewById(radioIdGender);

            int radioIdCategory = rg_categoryGuest.getCheckedRadioButtonId();
            radioButtonCategory = findViewById(radioIdCategory);

            int radioIdProgress = rg_progressGuest.getCheckedRadioButtonId();
            radioButtonProgress = findViewById(radioIdProgress);


//                username = String.valueOf(textInputEditTextUsername.getText());
            name = String.valueOf(et_nameGuest.getText());
            gender = String.valueOf(radioButtonGender.getText());
            category = String.valueOf(radioButtonCategory.getText());
            quantity = String.valueOf(et_quantityGuest.getText());
            progress = String.valueOf(radioButtonProgress.getText());
            phone = String.valueOf(et_phoneNoGuestCrew.getText());
            email = String.valueOf(et_emailGuestCrew.getText());
            notes = String.valueOf(et_notesGuestCrew.getText());



            if(!name.equals("") && !gender.equals("") && !category.equals("") && !quantity.equals("") && !progress.equals("") ) {

                //Start ProgressBar first (Set visibility VISIBLE)
//                    progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[12];
                        field[0] = "name";
                        field[1] = "gender";
                        field[2] = "category";
                        field[3] = "quantity";
                        field[4] = "progress";
                        field[5] = "phone";
                        field[6] = "email";
                        field[7] = "notes";
                        field[8] = "process";
                        field[9] = "username";
                        field[10] = "event_id";
                        field[11] = "guest_id";

                        //Creating array for data
                        String[] data = new String[12];
                        data[0] = name;
                        data[1] = gender;
                        data[2] = category;
                        data[3] = quantity;
                        data[4] = progress;
                        data[5] = phone;
                        data[6] = email;
                        data[7] = notes;
                        data[8] = process;
                        data[9] = username;
                        data[10] = String.valueOf(id);
                        data[11] = guestid;
                        // todo host
                        PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/GuestCrew/guestListView.php", "POST", field, data);
                        //lepak server
//                        PutData putData = new PutData("https://eventastic.lepak.xyz/GuestCrew/guestListView.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
//                                    progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if(result.contains("200")){

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
