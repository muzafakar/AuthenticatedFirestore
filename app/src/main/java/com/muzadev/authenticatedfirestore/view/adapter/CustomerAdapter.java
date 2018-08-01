package com.muzadev.authenticatedfirestore.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muzadev.authenticatedfirestore.R;
import com.muzadev.authenticatedfirestore.model.Customer;

import java.util.List;

/**
 * Created by zulfakar on 01/08/18.
 * For educational purposes
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private Context context;
    private List<Customer> customerList;

    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.tvName.setText(customer.getName());
        holder.tvAddress.setText(customer.getAddress());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
