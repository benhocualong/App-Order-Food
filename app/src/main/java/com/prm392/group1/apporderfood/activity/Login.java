package com.prm392.group1.apporderfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.database.DataBaseHelper;

public class Login extends AppCompatActivity {
    DataBaseHelper dbHelper;
    Button btnLogin;
    EditText etUsername, etPwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DataBaseHelper(this);
        etUsername = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedId = dbHelper.checkUsernamePassword(etUsername.getText().toString(), etPwd.getText().toString());
                if (isLoggedId) {
                    Intent intent = new Intent(Login.this, CartActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}