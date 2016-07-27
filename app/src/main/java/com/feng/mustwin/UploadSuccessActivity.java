package com.feng.mustwin;

import android.os.Bundle;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by sks on 2016/1/26.
 */
public class UploadSuccessActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_success);
        ViewUtils.inject(this);
        mTitle.setText("上传结果");
    }
}
