package com.example.bluejackkos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingTransaction extends AppCompatActivity {
    RecyclerView rvBookingTrx;
    TextView tvMessage;
    ListAdapterBooking listAdapterBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_transaction);
        tvMessage = (TextView)findViewById(R.id.message);
        rvBookingTrx = (RecyclerView) findViewById(R.id.rvBookingTrx);

        Bundle bundled = getIntent().getExtras();
        final String validateUserId = bundled.getString("userIdValidation");
        for(int i = 0; i < BookingDB.bookings.size(); i++){
            if(validateUserId.equals(BookingDB.bookings.get(i).getUserId())){
                FilteredDB.filtered.add(new Booking(BookingDB.bookings.get(i).getBookingId(), BookingDB.bookings.get(i).getBookingDate(), BookingDB.bookings.get(i).getKosData(), BookingDB.bookings.get(i).getUserId()));
            }
        }
        
        if(FilteredDB.filtered.size() == 0){
            tvMessage.setText("No booking transactions");
        }
        else {
            tvMessage.setText("");
            showListData();
            listAdapterBooking.setItemBookingClickListener(new ListAdapterBooking.OnRecycleBookingListener() {
                @Override
                public void onItemBookingClick(final int position) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(BookingTransaction.this);
                    alert.setMessage("Do you want to cancel your booking?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BookingDB.bookings.remove(position);
                            FilteredDB.filtered.remove(position);
                            listAdapterBooking.notifyDataSetChanged();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.setTitle("Cancel Booking");
                    alertDialog.show();
                }
            });
        }

    }

    private void showListData() {
        listAdapterBooking = new ListAdapterBooking();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvBookingTrx.setLayoutManager(layoutManager);
        rvBookingTrx.setAdapter(listAdapterBooking);

    }

    @Override
    protected void onPause() {
        super.onPause();
        FilteredDB.filtered.clear();
    }
}
