package com.prm392.group1.apporderfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.prm392.group1.apporderfood.DBHelper.DatabaseHelper;
import com.prm392.group1.apporderfood.R;
import com.prm392.group1.apporderfood.activity.AddEditUserActivity;
import com.prm392.group1.apporderfood.model.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> userList;
    private DatabaseHelper databaseHelper;

    public UserAdapter(Context context, List<User> userList, DatabaseHelper databaseHelper) {
        this.context = context;
        this.userList = userList;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDetails = convertView.findViewById(R.id.tvDetails);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        User user = userList.get(position);
        tvName.setText(user.getName());
        tvDetails.setText("Username: " + user.getUsername() + " | Role: " + user.getRole());

        // Xử lý nút sửa
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditUserActivity.class);
            intent.putExtra("userId", user.getId());
            context.startActivity(intent);
        });

        // Xử lý nút xóa
        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xóa người dùng")
                    .setMessage("Bạn có chắc chắn muốn xóa người dùng này?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        databaseHelper.deleteUser(user.getId());
                        userList.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Đã xóa người dùng!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });

        return convertView;
    }
}
