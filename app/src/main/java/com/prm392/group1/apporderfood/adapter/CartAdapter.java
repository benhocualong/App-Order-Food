package com.prm392.group1.apporderfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.database.CartItemDatabase;
import com.prm392.group1.apporderfood.helper.Constant;
import com.prm392.group1.apporderfood.model.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> data;

    private CartItemDatabase db;

    public CartAdapter(CartItemDatabase db) {
        this.db = db;
        this.data = db.getAllCartItems();
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
        int quantity = cartItem.getQuantity();
        holder.cartQuantity.setText(String.valueOf(quantity));
        holder.btnPlus.setOnClickListener(v -> {
            int newQuantity = quantity + 1;
            changeQuantity(position, cartItem, newQuantity);
        });

        holder.btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                int newQuantity = quantity - 1;
                changeQuantity(position, cartItem, newQuantity);
            }
        });
    }

    private void changeQuantity(int position, CartItem cartItem, int newQuantity) {
        int rowsAffected = db.updateCartItem(cartItem.getId(), newQuantity);
        if (rowsAffected > 0) {
            cartItem.setQuantity(newQuantity);
            data.set(position, cartItem);
            notifyItemChanged(position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        private TextView cartProductName;
        private TextView productPrice;
        private TextView productOrderPrice;
        private TextView cartQuantity;
        private ImageView imageView;
        private ImageView btnPlus, btnMinus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
        }

        private void bindingView() {
            cartProductName = itemView.findViewById(R.id.cartProductName);
            productPrice = itemView.findViewById(R.id.productPrice);
            imageView = itemView.findViewById(R.id.imageView);
            productOrderPrice = itemView.findViewById(R.id.productOrderPrice);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            cartQuantity = itemView.findViewById(R.id.cartQuantity);
        }

        public void setCart(CartItem cartItem) {
            cartProductName.setText(cartItem.getProductName());
            productPrice.setText(cartItem.getProductPrice() + " " + Constant.VND);
            productOrderPrice.setText(cartItem.getProductOrderPrice() + " " + Constant.VND);
            imageView.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}
