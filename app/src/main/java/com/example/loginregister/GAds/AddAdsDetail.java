package com.example.loginregister.GAds;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Ads;
import com.example.loginregister.recyclerView.Booking;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class AddAdsDetail extends AppCompatActivity {

    EditText et_adsName, et_adsNotes;
    RadioGroup rg_adsCategory, rg_Ads_status;
    RadioButton radioButtonAdsCategory,radioButtonAdsStatus;
    Button btn_saveChangesAds;
    int id;
    String process="insert";
    String username;
    Intent extra;
    Ads currentAds;
    int adsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gactivity_add_ads_detail);

        et_adsName = findViewById(R.id.et_adsName);
        et_adsNotes = findViewById(R.id.et_adsNotes);

        btn_saveChangesAds = findViewById(R.id.btn_saveChangesAds);
        rg_adsCategory = findViewById(R.id.rg_adsCategory);
        rg_Ads_status = findViewById(R.id.rg_Ads_status);

        //        if(savedInstanceState==null) {

        extra = getIntent();
        id = extra.getIntExtra("id",0);




//            Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();


        username = extra.getStringExtra("username");

        if(extra.hasExtra("edit")){

            process = "update";
            currentAds = (Ads) getIntent().getSerializableExtra("edit");
            adsid = currentAds.getAds_id();

            et_adsName.setText(currentAds.getAname());
            et_adsNotes.setText(currentAds.getNotes());

            switch(currentAds.getAcategory()){
                case "Poster": rg_adsCategory.check(rg_adsCategory.getChildAt(0).getId()); break;
                case "Foods Catering": rg_adsCategory.check(rg_adsCategory.getChildAt(1).getId()); break;
                case "Banner": rg_adsCategory.check(rg_adsCategory.getChildAt(2).getId()); break;
                case "Invitation Card": rg_adsCategory.check(rg_adsCategory.getChildAt(3).getId()); break;
                case "Others": rg_adsCategory.check(rg_adsCategory.getChildAt(4).getId()); break;
            }
            switch(currentAds.getAstatus()){
                case "Finished": rg_Ads_status.check(rg_Ads_status.getChildAt(0).getId()); break;
                case "Not Finished": rg_Ads_status.check(rg_Ads_status.getChildAt(1).getId()); break;

            }

        }else if(currentAds==null){

            adsid = 0;

        }


        btn_saveChangesAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //for radio group

                String name, category, notes, adsStatus ;

                int radioIdAdsCategory = rg_adsCategory.getCheckedRadioButtonId();
                radioButtonAdsCategory = findViewById(radioIdAdsCategory);

                int radioIdAdsStatus = rg_Ads_status.getCheckedRadioButtonId();
                radioButtonAdsStatus = findViewById(radioIdAdsStatus);


//                username = String.valueOf(textInputEditTextUsername.getText());
                name = String.valueOf(et_adsName.getText());
                notes = String.valueOf(et_adsNotes.getText());
                category = String.valueOf(radioButtonAdsCategory.getText());
                adsStatus = String.valueOf(radioButtonAdsStatus.getText());



                if(!name.equals("") && !category.equals("") && !notes.equals("") && !adsStatus.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
//                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[7];
                            field[0] = "name";
                            field[1] = "category";
                            field[2] = "notes";
                            field[3] = "status";
                            field[4] = "process";
                            field[5] = "username";
                            field[6] = "event_id";

                            //Creating array for data
                            String[] data = new String[7];
                            data[0] = name;
                            data[1] = category;
                            data[2] = notes;
                            data[3] = adsStatus;
                            data[4] = process;
                            data[5] = username;
                            data[6] = String.valueOf(id);
                            // todo host
                            PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/Ads/AdsListView.php", "POST", field, data);
                            //lepak server
//                            PutData putData = new PutData("https://eventastic.lepak.xyz/Ads/AdsListView.php", "POST", field, data);
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
            }
        });
    }

//
}