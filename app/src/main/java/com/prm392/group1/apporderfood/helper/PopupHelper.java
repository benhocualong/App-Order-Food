package com.prm392.group1.apporderfood.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.prm392.group1.apporderfood.R;

import java.util.List;

public class PopupHelper {
    private Context mContext;
    private PopupWindow mPopupWindow;

    public PopupHelper(Context context) {
        mContext = context;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(String selectedCode);
    }

    private OnItemSelectedListener mListener;

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        mListener = listener;
    }

    public void showDiscountPopup(View anchorView, int layout, List<String> discountCodes) {
        if (mPopupWindow == null) {
            View contentView = LayoutInflater.from(mContext).inflate(layout, null);

            mPopupWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);

            mPopupWindow.setTouchable(true);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(true);

            Button closeButton = contentView.findViewById(R.id.close_button);
            closeButton.setOnClickListener(v -> mPopupWindow.dismiss());

            final ListView listView = contentView.findViewById(R.id.discountList);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, discountCodes);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                String selectedCode = discountCodes.get(position);

                if (mListener != null) {
                    mListener.onItemSelected(selectedCode);
                }

                mPopupWindow.dismiss();
            });
        }

        mPopupWindow.showAsDropDown(anchorView);
    }
}