package com.prm392.group1.apporderfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.activity.ProductDetails;
import com.prm392.group1.apporderfood.activity.UserProductDetailsActivity;
import com.prm392.group1.apporderfood.model.Product;

import java.util.ArrayList;

public class UserProductAdapter extends RecyclerView.Adapter<UserProductAdapter.ViewHolder> {
    ArrayList<Product> productList = new ArrayList<>();
    Context context;

    public UserProductAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(productList.get(position).getName());
        holder.productPrice.setText(productList.get(position).getPrice());
        holder.productDescription.setText(productList.get(position).getDescription());
        holder.productCategory.setText(productList.get(position).getCategory());
        holder.productImage.setImageResource(productList.get(position).getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserProductDetailsActivity.class);
            intent.putExtra("product_id", productList.get(position).getId());
            intent.putExtra("product_name", productList.get(position).getName());
            intent.putExtra("product_description", productList.get(position).getDescription());
            intent.putExtra("product_price", productList.get(position).getPrice());
            intent.putExtra("product_image", productList.get(position).getImage());
            intent.putExtra("product_category", productList.get(position).getCategory());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, productDescription, productCategory;
        Button btnBuyProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imgvProduct);
            productName = itemView.findViewById(R.id.tvName);
            productPrice = itemView.findViewById(R.id.tvPrice);
            productDescription = itemView.findViewById(R.id.tvDescription);
            productCategory = itemView.findViewById(R.id.tvCategory);
            btnBuyProduct = itemView.findViewById(R.id.btnBuyProduct);
        }
    }
}
