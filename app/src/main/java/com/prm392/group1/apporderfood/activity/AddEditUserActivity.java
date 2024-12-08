package com.prm392.group1.apporderfood.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prm392.group1.apporderfood.DBHelper.DatabaseHelper;
import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.model.User;

public class AddEditUserActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText edtUsername, edtPassword, edtName, edtAddress, edtPhone, edtMail;
    private Button btnSaveUser;
    private Spinner spinnerRole;
    private int userId = -1;
    private String[] roles = {"Admin", "User"}; // Chỉ có 2 role

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);

        // Khởi tạo database helper
        databaseHelper = new DatabaseHelper(this);

        // Gán ID cho các view
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        spinnerRole = findViewById(R.id.spinnerRole);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        edtMail = findViewById(R.id.edtMail);
        btnSaveUser = findViewById(R.id.btnSaveUser);

        // Thiết lập adapter cho Spinner
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(roleAdapter);

        // Lấy userId từ intent (nếu có)
        userId = getIntent().getIntExtra("userId", -1);

        // Nếu userId != -1, load thông tin User để sửa
        if (userId != -1) {
            loadUser(userId);
        }

        // Lưu User khi nhấn nút Save
        btnSaveUser.setOnClickListener(view -> saveUser());
    }

    /**
     * Load thông tin User dựa trên userId
     */
    private void loadUser(int id) {
        User user = databaseHelper.getUserById(id);
        if (user != null) {
            edtUsername.setText(user.getUsername());
            edtPassword.setText(user.getPassword());
            edtName.setText(user.getName());
            edtAddress.setText(user.getAddress());
            edtPhone.setText(user.getPhone());
            edtMail.setText(user.getMail());

            // Thiết lập role cho Spinner
            int rolePosition = user.getRole().equals("Admin") ? 0 : 1;
            spinnerRole.setSelection(rolePosition);
        }
    }

    /**
     * Lưu thông tin User
     */
    private void saveUser() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String role = spinnerRole.getSelectedItem().toString(); // Lấy giá trị từ Spinner
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String mail = edtMail.getText().toString().trim();

        // Kiểm tra thông tin đầu vào
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill required fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thêm hoặc cập nhật User vào Database
        if (userId == -1) {
            databaseHelper.addUser(new User(0, username, password, role, name, address, phone, mail));
            Toast.makeText(this, "User added!", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelper.updateUser(new User(userId, username, password, role, name, address, phone, mail));
            Toast.makeText(this, "User updated!", Toast.LENGTH_SHORT).show();
        }

        // Đóng Activity
        finish();
    }
}
