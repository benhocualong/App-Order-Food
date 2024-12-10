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
import com.prm392.group1.apporderfood.model.Discount;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> data;

    CartItemDatabase db;

    private OnChangeQuantityListener listener;

    public interface OnChangeQuantityListener {
        void onChangeQuantity(CartItem cartItemList);
    }

    public CartAdapter(List<CartItem> cartList, OnChangeQuantityListener listener) {
        this.data = cartList;
        this.listener = listener;
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
        holder.cartQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.productOrderPrice.setText(String.valueOf(cartItem.getProductOrderPrice()));
        holder.btnPlus.setOnClickListener(v -> {
            int newQuantity = cartItem.getQuantity() + 1;
            cartItem.setQuantity(newQuantity);
            cartItem.setProductOrderPrice(cartItem.getProductPrice() * newQuantity);
            updateCartItem(position, cartItem);
        });

        holder.btnMinus.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                int newQuantity = cartItem.getQuantity() - 1;
                cartItem.setQuantity(newQuantity);
                cartItem.setProductOrderPrice(cartItem.getProductPrice() * newQuantity);
                updateCartItem(position, cartItem);

            }
        });
    }

    private void updateCartItem(int position, CartItem cartItem) {
        data.set(position, cartItem);
        notifyItemChanged(position);
        if (listener != null) {
            listener.onChangeQuantity(cartItem);
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
