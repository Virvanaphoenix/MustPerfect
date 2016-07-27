package com.feng.mustwin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.feng.mustwin.adapter.CommonAdapter;
import com.feng.mustwin.adapter.MainSexangleAdapter;
import com.feng.mustwin.application.MyApplication;
import com.feng.mustwin.bean.BlackBean;
import com.feng.mustwin.util.Utils;
import com.feng.mustwin.view.ViewHolder;
import com.feng.mustwin.view.edittext.DeletableEditText;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//
///**
// * 搜索页面
// */
public class SearchActivity extends BaseActivity {

    /**
     * 记录按钮
     */
    @ViewInject(R.id.main_first_search_edit)
    private DeletableEditText mSearchEdit;
//
    /**
     * 搜索无结果
     */
    @ViewInject(R.id.search_nothing_rel)
    private RelativeLayout mSearchNothingResult;
    /**
     * 搜索无结果
     */
    @ViewInject(R.id.result_nothing_search)
    private TextView mSearchNothingTv;
    /**
     * 搜索结果
     */
    @ViewInject(R.id.lv_search)
    private ListView mSearchLv;

    //
    private List<BlackBean> mListBean = null;
    private MainSexangleAdapter adapter;

    //
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        ViewUtils.inject(this);
        MyApplication.getInstance();
        overridePendingTransition(0, 0);
        mListBean = new ArrayList<>();
        json();
    }


    @OnClick(R.id.iv_search)
    private void searchClick2(View v) {
        Utils.hideKeyboard(SearchActivity.this, v);
        if (mListBean.size()>0){
            List<BlackBean> list = new ArrayList<>();
            for (BlackBean bean : mListBean){
                if (mSearchEdit.getText().toString().equals(bean.getmPhoneNum())){
                    list.add(bean);
                }
            }
            if (list.size()>0){
                mSearchLv.setAdapter(new CommonAdapter<BlackBean>(mContext, list, R.layout.found_fragment_list_item) {
                    @Override
                    public void convert(ViewHolder helper, BlackBean item) {
                        helper.setText(R.id.tv_phonenum, item.getmPhoneNum());
                        helper.setText(R.id.tv_report_time, getString(R.string.report_time) + item.getmReportTime());
                        helper.setText(R.id.tv_report_con, getString(R.string.report_con) + item.getmReportContent());
                    }
                });
            }else {
                mSearchNothingResult.setVisibility(View.VISIBLE);
                mSearchLv.setVisibility(View.GONE);
                mSearchNothingTv.setText("没有您要搜索的内容");
            }

        }else {
            mSearchNothingResult.setVisibility(View.VISIBLE);
            mSearchLv.setVisibility(View.GONE);
            mSearchNothingTv.setText("没有您要搜索的内容");
        }
    }

    private void searchClick(View v) {
        Utils.hideKeyboard(SearchActivity.this, v);
        AVQuery<AVObject> query = new AVQuery<AVObject>("TestObject");
        query.whereEqualTo("tel", mSearchEdit.getText().toString());
        query.findInBackground(new FindCallback<AVObject>() {
            public void done(List<AVObject> avObjects, AVException e) {
                Log.i("fffff", "查询到" + avObjects.size() + " 条符合条件的数据");
                if (avObjects.size() > 0) {
                    for (AVObject object : avObjects) {
                        BlackBean bean = new BlackBean();
                        bean.setmPhoneNum(object.getString("tel"));
                        bean.setmReportTime(object.getString("creat_time"));
                        bean.setmReportContent(object.getString("content"));
                        mListBean.add(bean);
                    }
                    mSearchLv.setAdapter(new CommonAdapter<BlackBean>(mContext, mListBean, R.layout.found_fragment_list_item) {
                        @Override
                        public void convert(ViewHolder helper, BlackBean item) {
                            helper.setText(R.id.tv_phonenum, item.getmPhoneNum());
                            helper.setText(R.id.tv_report_time, getString(R.string.report_time) + item.getmReportTime());
                            helper.setText(R.id.tv_report_con, getString(R.string.report_con) + item.getmReportContent());
                        }
                    });
                } else {
                    Log.i("fffff", "查询错误: " + e.getMessage());
                    mSearchNothingResult.setVisibility(View.VISIBLE);
                    mSearchLv.setVisibility(View.GONE);
                    mSearchNothingTv.setText("没有您要搜索的内容");
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(0, 0);
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
              Log.i("fffff","Exception = " + e.toString());
        }
    }

}