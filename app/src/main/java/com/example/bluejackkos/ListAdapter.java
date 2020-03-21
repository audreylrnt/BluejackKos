package com.example.bluejackkos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private OnRecycleClickListener mListener;

    public interface OnRecycleClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(OnRecycleClickListener listener){
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView kostName, kostPrice, kostFacility;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kostName = itemView.findViewById(R.id.kostName);
            kostPrice = itemView.findViewById(R.id.kostPrice);
            kostFacility = itemView.findViewById(R.id.kostFacility);
            img = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Boarding boarding = BoardingDB.boardings.get(position);
        holder.kostName.setText(boarding.getName());
        String harga = String.valueOf(boarding.getPrice());
        holder.kostPrice.setText(harga);
        holder.kostFacility.setText(boarding.getFacility());
        holder.img.setImageResource(boarding.getImgId());
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
