package com.measures.corona_hack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.ViewHolder> {

    private Context context;
    private List<Store_Owners> owners;

    public OwnerAdapter(Context context, List<Store_Owners> owners){
        this.context=context;
        this.owners=owners;
    }

    @NonNull
    @Override
    public OwnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.store, parent, false);
        return new OwnerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerAdapter.ViewHolder holder, int position) {

        final Store_Owners owner=owners.get(position);
        holder.Locality.setText(owner.getLocality());
        holder.storeName.setText(owner.getStore_name());
        holder.Timings.setText(owner.getTimings());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("Store name", owner.getStore_name());
                intent.putExtra("Address", owner.getAddress());
                intent.putExtra("Peak time", owner.getPeak_time());
                intent.putExtra("Items", owner.getItems());
                intent.putExtra("Special items", owner.getSpecial_items());
                intent.putExtra("Off day", owner.getOff_day());
                intent.putExtra("Owner name", owner.getUsername());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return owners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView storeName, Locality, Timings;
        ImageView store_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storeName=itemView.findViewById(R.id.storeName);
            Locality=itemView.findViewById(R.id.Locality);
            Timings=itemView.findViewById(R.id.Timings);
            store_icon=itemView.findViewById(R.id.store_icon);
        }
    }
}