package com.prm392.group1.apporderfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.prm392.group1.apporderfood.R;

public class ProductDetails extends AppCompatActivity {
    private TextView tvProductName, tvDetailPrice, tvDetailDescription, tvDetailsInfomation;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvProductName), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvProductName = findViewById(R.id.tvProductName);
        imageView = findViewById(R.id.imageView);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailsInfomation = findViewById(R.id.tvDetailsInfomation);

        Intent intent = getIntent();
        if (intent != null) {
            String productName = intent.getStringExtra("product_name");
            String productDescription = intent.getStringExtra("product_description");
            String productPrice = intent.getStringExtra("product_price");
            int productImage = intent.getIntExtra("product_image", R.drawable.ic_food);
            String productCategory = intent.getStringExtra("product_category");

            tvProductName.setText(productName);
            tvDetailPrice.setText(productPrice);
            tvDetailDescription.setText(productDescription);
            tvDetailsInfomation.setText("Category: " + productCategory); // Hiển thị thêm thông tin
            imageView.setImageResource(productImage); // Hiển thị ảnh từ resource
        }
    }
}