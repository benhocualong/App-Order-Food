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

public class SignUp extends AppCompatActivity {
    EditText etUser, etPad, etRepad;
    Button btnSignUp, btnGotoLogin;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etUser = findViewById(R.id.etUsername);
        etPad = findViewById(R.id.etPassword);
        etRepad = findViewById(R.id.etRepassword);
        btnSignUp = findViewById(R.id.btnSignup);
        dbHelper = new DataBaseHelper(this);
        btnGotoLogin = findViewById(R.id.gtLogin);
        btnGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pwd, Repwd;
                user = etUser.getText().toString();
                pwd = etPad.getText().toString();
                Repwd = etRepad.getText().toString();
                if(user.equals("") || pwd.equals("") || Repwd.equals("")){
                    Toast.makeText(SignUp.this, "Please fill all the field", Toast.LENGTH_LONG).show();
                }else{
                    if (pwd.equals(Repwd)){
                        if (dbHelper.checkUsername(user)){
                            Toast.makeText(SignUp.this, "User already exist", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean registeredSuccess = dbHelper.insertData(user,pwd);
                        if(registeredSuccess){
                            Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(SignUp.this, "User Registration failed", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(SignUp.this, "Password do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}