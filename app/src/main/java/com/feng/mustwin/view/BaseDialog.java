package com.feng.mustwin.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.feng.mustwin.R;

/**
 * 带动画的对话框
 *
 * @author feng
 */
public abstract class BaseDialog extends AlertDialog {
    /**
     * 对话框的布局
     */
    protected ViewGroup mDialogView;
    protected ButtonSureClickListener mButtonSureClickListener;
    protected Context mContext;

    public BaseDialog(Context context) {
        super(context, R.style.dialog);
        mContext = context;
        mDialogView = (ViewGroup) View.inflate(context, setLayoutView(), null);
        this.setContentView(mDialogView);
        setOwnerActivity((Activity) context);
    }

    public BaseDialog(Context context, boolean isTranseBackGround) {
        super(context);
        mContext = context;
        mDialogView = (ViewGroup) View.inflate(context, setLayoutView(), null);
        this.setContentView(mDialogView);
        setOwnerActivity((Activity) context);
    }

    /**
     * 一个按钮
     *
     * @param buttonSureClickListener
     */
    public void setSureClickListener(ButtonSureClickListener buttonSureClickListener) {
        mButtonSureClickListener = buttonSureClickListener;
    }

    /**
     * 设置对话框全屏
     */
    public void setWidthFullScreen() {
        // 对话框设置全屏
        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int width = (int) (dm.widthPixels * 0.8);
        int high = (int) (dm.heightPixels * 0.4);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = width; // 设置宽度
        lp.height = high;
    }

    /**
     * 设置用哪个布局
     */
    protected abstract int setLayoutView();

    public void show() {
        super.show();
    }

    /**
     * 一个确定
     */
    public interface ButtonSureClickListener {
        void okClick();
        void cancel();
    }
}
