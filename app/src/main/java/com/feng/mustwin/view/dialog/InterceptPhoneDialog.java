package com.feng.mustwin.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.feng.mustwin.R;
import com.feng.mustwin.view.BaseDialog;

/**
 * Created by feng on 2016/1/25.
 */
public class InterceptPhoneDialog extends BaseDialog {

    private TextView mYes;

    private TextView mNo;


    public InterceptPhoneDialog(Context context) {
        super(context);
        init();
        setOnClick();
    }

    private void init(){
        mYes = (TextView) findViewById(R.id.tv_phone_dialog_yes);
        mNo = (TextView) findViewById(R.id.tv_phone_dialog_no);
    }

    private void setOnClick(){
        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonSureClickListener.okClick();
            }
        });
        mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonSureClickListener.cancel();
            }
        });
    }

    @Override
    protected int setLayoutView() {
        return R.layout.intercept_phone_dialog;
    }
}
