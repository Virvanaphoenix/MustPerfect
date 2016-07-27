package com.feng.mustwin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.feng.mustwin.view.AbCommonProgressDialog;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by feng on 2015/10/29.
 */
public class BaseActivity extends FragmentActivity {
    private AbCommonProgressDialog progressDialog;
    protected Context mContext;


    public void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public void showProgressDialog(String... hint) {
        if (progressDialog == null) {
            progressDialog = new AbCommonProgressDialog(mContext);
        }
        progressDialog.setMessage(hint != null && hint.length > 0 ? hint[0]
                : "正在加载...");
        // progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCanceledOnTouchOutside(false);

        if (!progressDialog.isShowing() && !this.isFinishing())
            progressDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(mContext);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(mContext);
    }
}
