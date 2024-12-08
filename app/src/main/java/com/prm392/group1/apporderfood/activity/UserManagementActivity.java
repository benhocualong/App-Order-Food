package com.prm392.group1.apporderfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prm392.group1.apporderfood.DBHelper.DatabaseHelper;
import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.adapter.UserAdapter;
import com.prm392.group1.apporderfood.model.User;

import java.util.List;

public class UserManagementActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ListView listViewUsers;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        databaseHelper = new DatabaseHelper(this);
        listViewUsers = findViewById(R.id.listViewUsers);
        FloatingActionButton fabAddUser = findViewById(R.id.fabAddUser);

        loadUsers();

        fabAddUser.setOnClickListener(view -> {
            Intent intent = new Intent(UserManagementActivity.this, AddEditUserActivity.class);
            startActivity(intent);
        });

        listViewUsers.setOnItemClickListener((adapterView, view, i, l) -> {
            User selectedUser = userList.get(i);
            Intent intent = new Intent(UserManagementActivity.this, AddEditUserActivity.class);
            intent.putExtra("userId", selectedUser.getId());
            startActivity(intent);
        });
    }

    private void loadUsers() {
        userList = databaseHelper.getAllUsers();
        UserAdapter adapter = new UserAdapter(this, userList, databaseHelper);
        listViewUsers.setAdapter(adapter);
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadUsers();
    }
}
