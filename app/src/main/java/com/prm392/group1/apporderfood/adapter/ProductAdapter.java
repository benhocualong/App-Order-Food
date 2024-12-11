package com.prm392.group1.apporderfood.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.activity.ListProductsActivity;
import com.prm392.group1.apporderfood.activity.ProductDetails;
import com.prm392.group1.apporderfood.helper.DatabaseHelper;
import com.prm392.group1.apporderfood.model.Product;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> productList = new ArrayList<>();
    Context context;

    public ProductAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.productName.setText(productList.get(position).getName());
        holder.productPrice.setText(productList.get(position).getPrice());
        holder.productDescription.setText(productList.get(position).getDescription());
        holder.productCategory.setText(productList.get(position).getCategory());
        holder.productImage.setImageResource(productList.get(position).getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra("product_id", productList.get(position).getId());
            intent.putExtra("product_name", productList.get(position).getName());
            intent.putExtra("product_description", productList.get(position).getDescription());
            intent.putExtra("product_price", productList.get(position).getPrice());
            intent.putExtra("product_image", productList.get(position).getImage());
            intent.putExtra("product_category", productList.get(position).getCategory());
            context.startActivity(intent);
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog);

                EditText productName = dialog.findViewById(R.id.edtName);
                EditText productPrice = dialog.findViewById(R.id.edtPrice);
                EditText productDescription = dialog.findViewById(R.id.edtDescription);
                EditText productImageID = dialog.findViewById(R.id.edtImage);
                EditText productCategory = dialog.findViewById(R.id.edtCategory);
                Button btnInsertProduct = dialog.findViewById(R.id.btnUpdateProduct);

                productName.setText(productList.get(position).getName());
                productPrice.setText(String.valueOf(productList.get(position).getPrice()));
                productDescription.setText(productList.get(position).getDescription());
                productImageID.setText(String.valueOf(productList.get(position).getImage()));
                productCategory.setText(productList.get(position).getCategory());

                String id = String.valueOf(productList.get(position).getId());
                btnInsertProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.updateProduct(productName.getText().toString(),
                                productDescription.getText().toString(),
                                productPrice.getText().toString(),
                                Integer.parseInt(productImageID.getText().toString()),
                                productCategory.getText().toString(), id);
                        Toast.makeText(context,"The Product is successfully Updated !",Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, ListProductsActivity.class));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(context);
                Cursor cursor = db.getAllProducts();
                String id = String.valueOf(productList.get(position).getId());
                new AlertDialog.Builder(context)
                        .setTitle("Delete product")
                        .setMessage("Are you sure to delete this product?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                db.deleteProduct(id);
                                Toast.makeText(context,"The Product is successfully Deleted !",Toast.LENGTH_SHORT).show();
                                context.startActivity(new Intent(context, ListProductsActivity.class));
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, productDescription, productCategory;
        Button btnUpdate, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imgvProduct);
            productName = itemView.findViewById(R.id.tvName);
            productPrice = itemView.findViewById(R.id.tvPrice);
            productDescription = itemView.findViewById(R.id.tvDescription);
            productCategory = itemView.findViewById(R.id.tvCategory);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
