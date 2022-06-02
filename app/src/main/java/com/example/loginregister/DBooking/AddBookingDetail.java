package com.example.loginregister.DBooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.loginregister.R;
import com.example.loginregister.recyclerView.Booking;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class AddBookingDetail extends AppCompatActivity {

    EditText et_bookingName, it_notes, it_payment, it_bookingContactPhoneNumber,it_bookingContactEmail;
    RadioGroup rg_bookingCategory, rg_Payment_status;
    RadioButton radioButtonCategory,radioButtonPaymentStatus;
    Button btn_saveChanges;
    int id;
    String username;
    int bookingid;

    String process="insert";

    Booking currentBooking;

    Intent extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dactivity_add_booking_detail);

        et_bookingName = findViewById(R.id.et_bookingName);
        it_notes = findViewById(R.id.it_notes);
        it_payment = findViewById(R.id.it_payment);
        it_bookingContactPhoneNumber = findViewById(R.id.it_bookingContactPhoneNumber);
        it_bookingContactEmail = findViewById(R.id.it_bookingContactEmail);
        btn_saveChanges = findViewById(R.id.btn_saveChangesGuest);
        rg_bookingCategory = findViewById(R.id.rg_bookingCategory);
        rg_Payment_status = findViewById(R.id.rg_Payment_status);

//        if(savedInstanceState==null) {

            extra = getIntent();
            id = extra.getIntExtra("id",0);




//            Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();


            username = extra.getStringExtra("username");


            if(extra.hasExtra("edit")){

                process = "update";
                currentBooking = (Booking) getIntent().getSerializableExtra("edit");
                bookingid = currentBooking.getBookingid();

                et_bookingName.setText(currentBooking.getName());
                it_notes.setText(currentBooking.getNotes());
                it_payment.setText(currentBooking.getPayment());
                it_bookingContactPhoneNumber.setText(currentBooking.getPhone());
                it_bookingContactEmail.setText(currentBooking.getEmail());

                switch(currentBooking.getCategory()){
                    case "Venue": rg_bookingCategory.check(rg_bookingCategory.getChildAt(0).getId()); break;
                    case "Foods Catering": rg_bookingCategory.check(rg_bookingCategory.getChildAt(1).getId()); break;
                    case "Entertainments/Performances": rg_bookingCategory.check(rg_bookingCategory.getChildAt(2).getId()); break;
                    case "Crew Teams": rg_bookingCategory.check(rg_bookingCategory.getChildAt(3).getId()); break;
                    case "Transportation": rg_bookingCategory.check(rg_bookingCategory.getChildAt(4).getId()); break;
                }
                switch(currentBooking.getPayment_status()){
                    case "Paid": rg_Payment_status.check(rg_Payment_status.getChildAt(0).getId()); break;
                    case "Not Paid Yet": rg_Payment_status.check(rg_Payment_status.getChildAt(1).getId()); break;

                }

            }else if(currentBooking==null){

                bookingid = 0;

            }

//        }else{

//            id=(int)savedInstanceState.getSerializable("id");
//            username=(String)savedInstanceState.getSerializable("username");

//        }


        btn_saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //for radio group

                String name, category, notes, payment, paymentStatus, phone, email ;

                int radioIdCategory = rg_bookingCategory.getCheckedRadioButtonId();
                radioButtonCategory = findViewById(radioIdCategory);

                int radioIdPaymentStatus = rg_Payment_status.getCheckedRadioButtonId();
                radioButtonPaymentStatus = findViewById(radioIdPaymentStatus);


//                username = String.valueOf(textInputEditTextUsername.getText());
                name = String.valueOf(et_bookingName.getText());
                notes = String.valueOf(it_notes.getText());
                phone = String.valueOf(it_bookingContactPhoneNumber.getText());
                email = String.valueOf(it_bookingContactEmail.getText());
                payment = String.valueOf(it_payment.getText());
                category = String.valueOf(radioButtonCategory.getText());
                paymentStatus = String.valueOf(radioButtonPaymentStatus.getText());



                if(!name.equals("") && !category.equals("") && !notes.equals("") && !payment.equals("") && !paymentStatus.equals("") ) {

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
                            field[2] = "notes";
                            field[3] = "payment";
                            field[4] = "payment_status";
                            field[5] = "phone";
                            field[6] = "email";
                            field[7] = "process";
                            field[8] = "username";
                            field[9] = "event_id";
                            field[10] = "booking_id";

                            //Creating array for data
                            String[] data = new String[11];
                            data[0] = name;
                            data[1] = category;
                            data[2] = notes;
                            data[3] = payment;
                            data[4] = paymentStatus;
                            data[5] = phone;
                            data[6] = email;
                            data[7] = process;
                            data[8] = username;
                            data[9] = String.valueOf(id);
                            data[10] = String.valueOf(bookingid);
                            // todo host
                            PutData putData = new PutData("http://"+getString(R.string.localhost)+"/API-Eventastic/Booking/BookingListView.php", "POST", field, data);
                            //lepak server
//                            PutData putData = new PutData("https://eventastic.lepak.xyz/Booking/BookingListView.php", "POST", field, data);
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