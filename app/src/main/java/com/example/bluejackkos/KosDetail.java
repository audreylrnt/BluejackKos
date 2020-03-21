package com.example.bluejackkos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KosDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kos_detail);
        TextView tvKostName = findViewById(R.id.tvKostName);
        ImageView imgKost = findViewById(R.id.imgKost);
        TextView tvKostPrice = findViewById(R.id.tvKostPrice);
        TextView tvKostFacility = findViewById(R.id.tvKostFacility);
        TextView tvKostDesc = findViewById(R.id.tvKostDesc);
        TextView tvKostLatitude = findViewById(R.id.tvKostLatitude);
        TextView tvKostLongitude = findViewById(R.id.tvKostLongitude);
        Button btnBooking = findViewById(R.id.btnBooking);
        final TextView tvBookingDate = findViewById(R.id.tvBookingDate);

        Intent fromKostList = getIntent();
        Boarding boarding = fromKostList.getParcelableExtra("BoardingDatas");
        final String userId = fromKostList.getStringExtra("userDataFromKostList");
        final String kostId = boarding.getId();
        int imgId = boarding.getImgId();
        String kostName = boarding.getName();
        String kostPrice = String.valueOf(boarding.getPrice());
        String kostDesc = boarding.getDesc();
        String kostFacility = boarding.getFacility();
        String kostLatitude = String.valueOf(boarding.getLatitude());
        String kostLongitude = String.valueOf(boarding.getLongitude());

        tvKostName.setText(kostName);
        tvKostPrice.setText(kostPrice);
        tvKostDesc.setText(kostDesc);
        tvKostFacility.setText(kostFacility);
        imgKost.setImageResource(imgId);
        tvKostLatitude.setText(kostLatitude);
        tvKostLongitude.setText(kostLongitude);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(KosDetail.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date selected = sdf.parse(date);
                            Calendar today = Calendar.getInstance();
                            today.set(Calendar.HOUR_OF_DAY, 0);
                            today.set(Calendar.MINUTE, 0);
                            today.set(Calendar.SECOND, 0);
                            today.set(Calendar.MILLISECOND, 0);
                            today.set(Calendar.YEAR, today.get(Calendar.YEAR));
                            today.set(Calendar.MONTH, today.get(Calendar.MONTH));
                            today.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));

                            Date dtToday = today.getTime();
                            if(dtToday.compareTo(selected) > 0){
                                Toast.makeText(KosDetail.this, "Selected date must be at least today", Toast.LENGTH_LONG).show();
                            }
                            else{
                                String bookingId = validateOther();
                                if(BookingDB.bookings.size() == 0){
                                    String selDate = selected.toString();
                                    Booking booking = new Booking(bookingId, selDate, kostId, userId);
                                    BookingDB.bookings.add(booking);
                                    Toast.makeText(KosDetail.this, "Boarding house successfully booked", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    for(int i = 0; i < BookingDB.bookings.size(); i++){
                                        if(BookingDB.bookings.get(i).getUserId().equals(userId) && BookingDB.bookings.get(i).getKosData().equals(kostId)){
                                            Toast.makeText(KosDetail.this, "You can't book the same boarding house", Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                    }
                                    String selDate = selected.toString();
                                    Booking booking = new Booking(bookingId, selDate, kostId, userId);
                                    BookingDB.bookings.add(booking);
                                    Toast.makeText(KosDetail.this, "Boarding house successfully booked", Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
    }

    private String validateOther() {
        String bookingId = "";
        bookingId = String.format("BK%03d", BookingDB.bookings.size()+1);
        return bookingId;
    }

}
