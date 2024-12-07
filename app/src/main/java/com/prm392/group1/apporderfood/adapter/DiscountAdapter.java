package com.prm392.group1.apporderfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.model.Discount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {

    private List<Discount> discountCodes;

    private OnDiscountSelectedListener listener;

    public interface OnDiscountSelectedListener {
        void onDiscountSelected(Discount discountCode);
    }

    public DiscountAdapter(List<Discount> discountCodes, OnDiscountSelectedListener listener) {
        this.listener = listener;
        this.discountCodes = discountCodes;
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_item, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        Discount code = discountCodes.get(position);
        holder.nameTextView.setText(code.getName());
        holder.percentageTextView.setText(String.format("%.2f%%", code.getPercentage()));
        holder.expirationDateTextView.setText(formatDate(code.getExpirationDate()));

        holder.applyButton.setOnClickListener(v -> selectDiscountCode(code));
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    private void selectDiscountCode(Discount selectedCode) {
        if (listener != null) {
            listener.onDiscountSelected(selectedCode);
        }
    }

    @Override
    public int getItemCount() {
        return discountCodes.size();
    }

    public static class DiscountViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView percentageTextView;
        TextView expirationDateTextView;
        Button applyButton;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.discount_name);
            percentageTextView = itemView.findViewById(R.id.discount_percentage);
            expirationDateTextView = itemView.findViewById(R.id.expiration_date);
            applyButton = itemView.findViewById(R.id.apply_button);
        }
    }
}