package com.prm392.group1.apporderfood.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.helper.PopupHelper;

import java.util.Arrays;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView total, discountAddText;

    private PopupHelper mPopupHelper;

    Button addDiscountBtn, btnCheckout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        handleClickDiscountBtn();
    }

    private void initView() {
        recyclerView = findViewById(R.id.cartView);
        total = findViewById(R.id.totalOrder);
        discountAddText = findViewById(R.id.discountAddText);
        btnCheckout = findViewById(R.id.btnCheckout);
        addDiscountBtn = findViewById(R.id.btnDiscountPopup);
    }

    private void handleClickDiscountBtn() {
        mPopupHelper = new PopupHelper(this);
        mPopupHelper.setOnItemSelectedListener(this::updateUI);

        addDiscountBtn.setOnClickListener(v -> showDiscountCodes());
    }

    private void showDiscountCodes() {
        List<String> discountCodes = Arrays.asList("MDC10", "MDC15", "MDC20");
        mPopupHelper.showDiscountPopup(addDiscountBtn, R.layout.discount_popup, discountCodes);
    }

    private void updateUI(String selectedCode) {
        // Cập nhật UI với mã giảm giá đã chọn
        discountAddText.setText(selectedCode);
        // Thêm logic khác để xử lý khi chọn mã giảm giá
        // Ví dụ: cập nhật giá trị trong giỏ hàng, hiển thị thông tin chi tiết về mã giảm giá, v.v.
    }
}