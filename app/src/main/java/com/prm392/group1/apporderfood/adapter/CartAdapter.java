package com.prm392.group1.apporderfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.model.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private List<CartItem> data;

    public CartAdapter(List<CartItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = data.get(position);
        holder.setCart(cartItem);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        private TextView cartProductName;
        private TextView cartTotalPrice;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
        }

        private void bindingView() {
            cartProductName = itemView.findViewById(R.id.cartProductName);
            cartTotalPrice = itemView.findViewById(R.id.cartTotalPrice);
        }

        public void setCart(CartItem cartItem) {
            cartProductName.setText(cartItem.getProductName());
            cartTotalPrice.setText(cartItem.getTotalPrice());
        }
    }
}
