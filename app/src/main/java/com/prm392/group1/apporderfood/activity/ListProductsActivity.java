package com.prm392.group1.apporderfood.activity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.adapter.ProductAdapter;
import com.prm392.group1.apporderfood.helper.DatabaseHelper;
import com.prm392.group1.apporderfood.model.Product;
import java.util.ArrayList;

public class ListProductsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product> productList = new ArrayList<>();
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_products);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerViewProducts), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = new DatabaseHelper(this);
        // Check db empty thì add data mẫu
        if (db.getAllProducts().getCount() == 0) {
            db.insertProduct(new Product(1, "Pizza Margherita", "Delicious cheese and tomato pizza", "8.99 USD", R.drawable.ic_food, "Italian"));
            db.insertProduct(new Product(2, "Burger Classic", "Juicy beef burger with fresh veggies", "6.49 USD", R.drawable.ic_food, "American"));
            db.insertProduct(new Product(3, "Sushi Platter", "Assorted sushi with soy sauce and wasabi", "12.99 USD", R.drawable.ic_food, "Japanese"));
            db.insertProduct(new Product(4, "Spaghetti Carbon", "Creamy pasta with bacon and cheese", "10.49 USD", R.drawable.ic_food, "Italian"));
            db.insertProduct(new Product(5, "Pad Thai", "Stir-fried rice noodles with shrimp", "7.99 USD", R.drawable.ic_food, "Thai"));
        }

        // Lấy data từ SQLite
        Cursor cursor = db.getAllProducts();
        while (cursor.moveToNext()) {
            productList.add(new Product(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5)
            ));
        }
        ProductAdapter adapter = new ProductAdapter(productList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_add_product) {
            showAddProductDialog();
            return true;
        } else if (id == R.id.menu_search_product) {
            showSearchProductDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showAddProductDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_product);

        EditText edtName = dialog.findViewById(R.id.edtProductName);
        EditText edtPrice = dialog.findViewById(R.id.edtProductPrice);
        EditText edtDescription = dialog.findViewById(R.id.edtProductDescription);
        EditText edtCategory = dialog.findViewById(R.id.edtProductCategory);
        Button btnAdd = dialog.findViewById(R.id.btnAddProduct);

        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String price = edtPrice.getText().toString();
            String description = edtDescription.getText().toString();
            String category = edtCategory.getText().toString();

            if (name.isEmpty() || price.isEmpty() || description.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseHelper db = new DatabaseHelper(this);
                db.insertProduct(new Product(0, name, description, price, R.drawable.ic_food, category));
                productList.add(new Product(0, name, description, price, R.drawable.ic_food, category));
                recyclerView.getAdapter().notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showSearchProductDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_search_product);

        EditText edtSearch = dialog.findViewById(R.id.edtSearch);
        Button btnSearch = dialog.findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(v -> {
            String query = edtSearch.getText().toString().toLowerCase();
            if (query.isEmpty()) {
                Toast.makeText(this, "Please enter a search query!", Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<Product> filteredList = new ArrayList<>();
                for (Product product : productList) {
                    if (product.getName().toLowerCase().contains(query) ||
                            product.getCategory().toLowerCase().contains(query)) {
                        filteredList.add(product);
                    }
                }
                ProductAdapter newAdapter = new ProductAdapter(filteredList, this);
                recyclerView.setAdapter(newAdapter);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}