package com.feng.mustwin.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.mustwin.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by fengzhongxia on 2015/11/6.
 */
public class MainFouthFragment extends LazyFragment {

    private View mView;
    private Context mContext;
    @ViewInject(R.id.tv_title)
    private TextView mTitle;


    private Intent mIntent = null;
    @Override
    protected void lazyLoad() {
    }


    @Override
    protected View doCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_forth,container,false);
        ViewUtils.inject(this, mView);
        mTitle.setText(getString(R.string.main_about));
        return mView;
    }

    private void backClick(View view){
//        mContext.finish();
    }
    private void onLogin(View view){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    /**
     * 加载本地图片
     * http://bbs.3gstdy.com
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
            File f  = new File(url);
        try {
            FileInputStream fis = new FileInputStream(f);//=========
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void onResume() {
        super.onResume();

    }

}
