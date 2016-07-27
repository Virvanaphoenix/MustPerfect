package com.feng.mustwin.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.mustwin.R;
import com.feng.mustwin.UploadSuccessActivity;
import com.feng.mustwin.util.AbToastUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


/**
 * Created by feng on 2015/11/1
 */
public class MainThirdFragment extends LazyFragment {

    private View mView;

    @ViewInject(R.id.tv_title)
    private TextView mTitle;

//    @ViewInject(R.id.et_phone_num)
//    private EditText mPhoneNum;
//    @ViewInject(R.id.et_after)
//    private EditText mAfter;
//    @ViewInject(R.id.et_intermediary_name)
//    private EditText mIntermediaryName;
//    @ViewInject(R.id.et_intermediary_company)
//    private EditText mIntermediaryCompany;
//    @ViewInject(R.id.et_house)
//    private EditText mHouseAdd;
//    @ViewInject(R.id.et_bank_card)
//    private EditText mBankCardNum;
//    @ViewInject(R.id.et_ID)
//    private EditText mIDNum;

    //选项卡控件

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected View doCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_third, container, false);
        ViewUtils.inject(this, mView);
        mTitle.setText("上传黑中介信息");
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @OnClick(R.id.tv_upload)
    private void uploadData(View v){
        AbToastUtil.showToast(mContext,"上传成功...");
        startActivity(new Intent(mContext, UploadSuccessActivity.class));
    }
//    private void sendData(){
//        HttpUtils http = new HttpUtils();
//        String url = "";
//        RequestParams params = new RequestParams();
//        params.addQueryStringParameter("mPhoneNum",mPhoneNum.getText().toString());
//        params.addQueryStringParameter("mAfter",mAfter.getText().toString());
//        params.addQueryStringParameter("mIntermediaryName",mIntermediaryName.getText().toString());
//        params.addQueryStringParameter("mIntermediaryCompany",mIntermediaryCompany.getText().toString());
//        params.addQueryStringParameter("mHouseAdd",mHouseAdd.getText().toString());
//        params.addQueryStringParameter("mBankCardNum",mBankCardNum.getText().toString());
//        params.addQueryStringParameter("mIDNum",mIDNum.getText().toString());
//        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<Object>() {
//            @Override
//            public void onSuccess(ResponseInfo<Object> responseInfo) {
//
//                try {
//                    JSONObject js = new JSONObject(responseInfo.result.toString());
//                    js.getString("");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//
//            }
//        });
//    }
}
