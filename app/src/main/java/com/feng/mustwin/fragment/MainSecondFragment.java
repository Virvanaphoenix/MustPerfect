package com.feng.mustwin.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feng.mustwin.R;
import com.feng.mustwin.SearchActivity;
import com.feng.mustwin.adapter.CommonAdapter;
import com.feng.mustwin.bean.BlackBean;
import com.feng.mustwin.view.ViewHolder;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by feng on 2015/11/1
 */
public class MainSecondFragment extends LazyFragment {

    private View mView;

    @ViewInject(R.id.relative_search)
    private RelativeLayout mRelative;

    @ViewInject(R.id.tv_search)
    private TextView mTvSearch;

    private float y;
    private int height;

    @ViewInject(R.id.lv_fragment)
    private ListView mListView;


    private List<BlackBean> mListBean = null;
    @Override
    protected void lazyLoad() {
    }

    @Override
    protected View doCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_second, container, false);
        ViewUtils.inject(this,mView);
        mListBean = new ArrayList<>();
        json();
        init();
        mListView.setAdapter(new CommonAdapter<BlackBean>(mContext, mListBean, R.layout.found_fragment_list_item) {
            @Override
            public void convert(ViewHolder helper, BlackBean item) {
                helper.setText(R.id.tv_phonenum, getString(R.string.report_num)+item.getmPhoneNum());
                helper.setText(R.id.tv_report_time, getString(R.string.report_time) + item.getmReportTime());
                helper.setText(R.id.tv_report_con, getString(R.string.report_con) + item.getmReportContent());
            }
        });
        return mView;
    }

    private void init() {
        mTvSearch.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                y = mTvSearch.getY() - 5;
                height = mTvSearch.getHeight();
                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -y);
                animation.setDuration(100);
                animation.setFillAfter(true);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, SearchActivity.class);
                        startActivityForResult(intent, 100);
                        mContext.overridePendingTransition(R.anim.animation_2, 0);
                    }
                });
                mRelative.startAnimation(animation);
            }
        });
    }

    private void json() {
        try {
            //将json文件读取到buffer数组中
            InputStream is = this.getResources().openRawResource(R.raw.blackall);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            //将字节数组转换为以GB2312编码的字符串
            String json = new String(buffer, "utf-8");
            //将字符串json转换为json对象，以便于取出数据
            JSONObject jsonObject = new JSONObject(json);
            //解析info数组，解析中括号括起来的内容就表示一个数组，使用JSONArray对象解析
            JSONArray array = jsonObject.getJSONArray("RECORDS");
            //遍历JSONArray数组
            for (int i = 0; i < array.length(); i++) {
                //取出数组的第一项
                JSONObject item = array.getJSONObject(i);
                BlackBean bean = new BlackBean();
                bean.setmPhoneNum(item.getString("tel"));
                bean.setmReportTime(item.getString("content"));
                bean.setmReportContent(item.getString("create_time"));
                mListBean.add(bean);
            }
        } catch (Exception e) {
            Log.i("fffff", "Exception = " + e.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        TranslateAnimation animation = new TranslateAnimation(0, 0, -y, 0);
        animation.setDuration(100);
        animation.setFillAfter(true);
        mRelative.startAnimation(animation);
    }
}
