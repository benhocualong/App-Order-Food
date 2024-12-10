package com.prm392.group1.apporderfood.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
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
import com.prm392.group1.apporderfood.adapter.CartAdapter;
import com.prm392.group1.apporderfood.adapter.DiscountAdapter;
import com.prm392.group1.apporderfood.database.CartDatabase;
import com.prm392.group1.apporderfood.database.CartItemDatabase;
import com.prm392.group1.apporderfood.helper.Constant;
import com.prm392.group1.apporderfood.model.Cart;
import com.prm392.group1.apporderfood.model.CartItem;
import com.prm392.group1.apporderfood.model.Discount;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerDiscountView;
    private RecyclerView recyclerCartView;
    private TextView totalOrder, discountAddText, totalShip, totalDiscount, totalAmount;
    private CartItemDatabase cartItemDatabase;
    private CartDatabase database;

    private PopupWindow popupWindow;

    private List<CartItem> cartItemList;

    private Cart cart;
    private List<Discount> discountCodes = Arrays.asList(
            new Discount(1, "Mã Giảm Giá 10%", 10.0, new Date()),
            new Discount(2, "Mã Giảm Giá 20%", 20.0, new Date(System.currentTimeMillis() + 86400000)), // 1 ngày sau
            new Discount(3, "Mã Giảm Giá 30%", 30.0, new Date(System.currentTimeMillis() + 172800000)) // 2 ngày sau
    );

    Button addDiscountBtn, btnCheckout;

    DiscountAdapter discountAdapter;

    CartAdapter cartAdapter;


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
        loadData();
        bindingData();
        initRecyclerCarView();
        initRecyclerDiscountView();
        handleAddDiscountBtn();
    }

    private void bindingData() {
        totalOrder.setText(cart.getTotalPrice() + " " + Constant.VND);
        totalShip.setText(cart.getTotalShip() + " " + Constant.VND);
        totalAmount.setText(cart.getTotalPrice() + " " + Constant.VND);
    }

    private void loadData() {
        cartItemDatabase = new CartItemDatabase(this);
        database = new CartDatabase(this);
        cart = database.getCart();
        if(cart != null){
            cartItemList = cartItemDatabase.getAllCartItemsByCartId(cart.getId());
            cart.setCartItemList(cartItemList);
        }
    }

    private void initRecyclerDiscountView() {
        View popupView = LayoutInflater.from(this).inflate(R.layout.discout_list, null);
        recyclerDiscountView = popupView.findViewById(R.id.recyclerView);
        recyclerDiscountView.setAdapter(discountAdapter);
        recyclerDiscountView.setLayoutManager(new LinearLayoutManager(this));
        popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
    }

    private void initRecyclerCarView() {
        cartAdapter = new CartAdapter(cartItemList, this::onChangeQuantity);
        recyclerCartView.setAdapter(cartAdapter);
        recyclerCartView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, 0, 0);
            }
        });
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

    public void onChangeQuantity(CartItem cartItem) {
        cartItemDatabase.updateCartItem(cartItem);
        cart.setTotalPrice(cartItemList.stream().mapToDouble(CartItem::getProductOrderPrice).sum());
        cart.setTotalAmount(cart.getTotalPrice()+cart.getTotalShip() - cart.getTotalDiscount());
        totalOrder.setText(cart.getTotalPrice() + " " + Constant.VND);
        totalAmount.setText(cart.getTotalAmount() + " " + Constant.VND);
        database.updateCart(cart);
    }

    public void onChangeCartQuantity(CartItem cartItem) {
        Toast.makeText(this, String.valueOf(cartItem.getQuantity()), Toast.LENGTH_SHORT).show();
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    private void initView() {
        totalOrder = findViewById(R.id.totalOrder);
        discountAddText = findViewById(R.id.discountAddText);
        btnCheckout = findViewById(R.id.btnCheckout);
        addDiscountBtn = findViewById(R.id.btnDiscountPopup);
        discountAdapter = new DiscountAdapter(discountCodes, this::onDiscountSelected);
        recyclerCartView = findViewById(R.id.cartView);
        totalShip = findViewById(R.id.totalShip);
        totalDiscount = findViewById(R.id.totaDiscount);
        totalAmount = findViewById(R.id.totalAmount);
    }

}