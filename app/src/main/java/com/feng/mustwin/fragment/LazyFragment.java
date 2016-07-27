package com.feng.mustwin.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.mustwin.view.AbCommonProgressDialog;

/**
 * Created by wuwf on 2015/1/14.
 * 延迟加载的fragment，只有获取用户焦点后才获取数据 ，布局不变而只修改数据
 */
public abstract class LazyFragment extends Fragment {
    /**
     * 是否是创建布局
     */
    private boolean isInit;

    /**
     * 是否可见
     */
    protected boolean isVisible;

    protected Activity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isInit = true;
        return doCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        mContext = activity;
        super.onAttach(activity);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            doVisible();
        } else {
            isVisible = false;
            doInVisible();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 判断当前fragment是否显示
        if (getUserVisibleHint()) {
            doVisible();
        }
    }

    /**
     * 界面可见时做相应的处理
     */
    private void doVisible() {
        if (isInit) {
            isInit = false;//加载数据完成
            lazyLoad();
        }
    }

    /**
     * 界面不可见时，做的操作
     */
    protected void doInVisible() {

    }

    /**
     * 界面可见时加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 填充布局的抽象方法
     */
    protected abstract View doCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    private AbCommonProgressDialog progressDialog;

    public void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void showProgressDialog(String... hint) {
        if (progressDialog == null) {
            progressDialog = new AbCommonProgressDialog(mContext);
        }
        progressDialog.setMessage(hint != null && hint.length > 0 ? hint[0]
                : "正在加载...");
        // progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCanceledOnTouchOutside(false);

        if (!progressDialog.isShowing())
            progressDialog.show();
    }
}
