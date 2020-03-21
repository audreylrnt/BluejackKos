package com.example.bluejackkos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class KostList extends AppCompatActivity {
    private RecyclerView rvKostList;
    private ListAdapter listAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.bookingTrx){
            Bundle bundled = getIntent().getExtras();
            final String userId = bundled.getString("userData");
            Intent toBookingTrx = new Intent(KostList.this, BookingTransaction.class);
            toBookingTrx.putExtra("userIdValidation", userId);
            startActivity(toBookingTrx);
        }
        if(item.getItemId() == R.id.logout){
            Intent logout = new Intent(KostList.this, MainActivity.class);
            FilteredDB.filtered.clear();
            startActivity(logout);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kost_list);
        rvKostList = findViewById(R.id.rvKostList);
        Bundle bundled = getIntent().getExtras();
        final String userId = bundled.getString("userData");
        showListData();

        listAdapter.setItemClickListener(new ListAdapter.OnRecycleClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent toKosDetail = new Intent(KostList.this, KosDetail.class);
                toKosDetail.putExtra("BoardingDatas", BoardingDB.boardings.get(position));
                toKosDetail.putExtra("userDataFromKostList", userId);
                startActivity(toKosDetail);
            }
        });
    }

    private void showListData() {
        listAdapter = new ListAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvKostList.setLayoutManager(layoutManager);
        rvKostList.setAdapter(listAdapter);
    }
}