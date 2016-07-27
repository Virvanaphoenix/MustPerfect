package com.feng.mustwin.view.dialog;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.feng.mustwin.R;

/**
 * Created by sks on 2016/1/25.
 */
public class AlertDialog {
    private Context context;
    private android.app.AlertDialog ad;
    private TextView titleView;
    private TextView mYes;
    private TextView mNo;

    public AlertDialog(Context context) {
        this.context = context;
        ad = new android.app.AlertDialog.Builder(context).create();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        window.setContentView(R.layout.intercept_phone_dialog);
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        mYes = (TextView) window.findViewById(R.id.tv_phone_dialog_yes);
        mNo = (TextView) window.findViewById(R.id.tv_phone_dialog_no);
    }

    public void setTitle(int resId) {
        titleView.setText(resId);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    /**
     * 设置按钮
     *
     * @param text
     * @param listener
     */
    public void setPositiveButton(String text, final View.OnClickListener listener) {
        mYes.setText(text);
        mYes.setOnClickListener(listener);
    }

    /**
     * 设置按钮
     *
     * @param text
     * @param listener
     */
    public void setNegativeButton(String text, final View.OnClickListener listener) {
        mNo.setText(text);
        mNo.setOnClickListener(listener);
    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
    }
}
