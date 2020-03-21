package com.example.bluejackkos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapterBooking extends RecyclerView.Adapter<ListAdapterBooking.ViewHolder> {
    private OnRecycleBookingListener mListener;

    public interface OnRecycleBookingListener{
        void onItemBookingClick(int position);
    }

    public void setItemBookingClickListener(OnRecycleBookingListener listener){
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBookingId, tvKostName, tvBookingDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookingId = itemView.findViewById(R.id.bookingId);
            tvKostName = itemView.findViewById(R.id.kostName);
            tvBookingDate = itemView.findViewById(R.id.bookingDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemBookingClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_booking, parent, false);
        return new ListAdapterBooking.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = FilteredDB.filtered.get(position);
        holder.tvBookingId.setText(booking.getBookingId());
        holder.tvBookingDate.setText(booking.getBookingDate());
        holder.tvKostName.setText(BoardingDB.boardings.get(Integer.parseInt(booking.getKosData())-1).getName());
    }

    @Override
    public int getItemCount() {
        return FilteredDB.filtered.size();
    }


}
