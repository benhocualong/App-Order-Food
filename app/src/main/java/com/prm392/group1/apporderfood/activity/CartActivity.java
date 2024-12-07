package com.prm392.group1.apporderfood.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.adapter.CartAdapter;
import com.prm392.group1.apporderfood.adapter.DiscountAdapter;
import com.prm392.group1.apporderfood.model.CartItem;
import com.prm392.group1.apporderfood.model.Discount;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerDiscountView;
    private RecyclerView recyclerCartView;
    private TextView total, discountAddText;

    List<CartItem> cartItems;

    private PopupWindow popupWindow;
    private List<Discount> discountCodes = Arrays.asList(
            new Discount(1, "Mã Giảm Giá 10%", 10.0, new Date()),
            new Discount(2, "Mã Giảm Giá 20%", 20.0, new Date(System.currentTimeMillis() + 86400000)), // 1 ngày sau
            new Discount(3, "Mã Giảm Giá 30%", 30.0, new Date(System.currentTimeMillis() + 172800000)) // 2 ngày sau
    );

    private void fakeData() {
        cartItems = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            cartItems.add(new CartItem("Hang 1", "100000 VNĐ"));
        }
    }

    Button addDiscountBtn, btnCheckout;

    DiscountAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cart), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        fakeData();
        handleAddDiscountBtn();
        initRecyclerCarView();
        initRecyclerDiscountView();

    }

    private void initRecyclerDiscountView() {
        View popupView = LayoutInflater.from(this).inflate(R.layout.discout_list, null);
        recyclerDiscountView = popupView.findViewById(R.id.recyclerView);
        recyclerDiscountView.setAdapter(adapter);
        recyclerDiscountView.setLayoutManager(new LinearLayoutManager(this));
        popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
    }

    private void initRecyclerCarView() {
        CartAdapter adapter = new CartAdapter(cartItems);
        recyclerCartView.setAdapter(adapter);
        recyclerCartView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void handleAddDiscountBtn() {
        addDiscountBtn.setOnClickListener(v -> popupWindow.showAsDropDown(addDiscountBtn));
    }

    public void onDiscountSelected(Discount selectedCode) {
        discountAddText.setText(String.format("%s (%.2f%% - Hết hạn %s)",
                selectedCode.getName(),
                selectedCode.getPercentage(),
                formatDate(selectedCode.getExpirationDate())
        ));
        popupWindow.dismiss();
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    private void initView() {
        total = findViewById(R.id.totalOrder);
        discountAddText = findViewById(R.id.discountAddText);
        btnCheckout = findViewById(R.id.btnCheckout);
        addDiscountBtn = findViewById(R.id.btnDiscountPopup);
        adapter = new DiscountAdapter(discountCodes, this::onDiscountSelected);
        recyclerCartView = findViewById(R.id.cartView);
    }


}