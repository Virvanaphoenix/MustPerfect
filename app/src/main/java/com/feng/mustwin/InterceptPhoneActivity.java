package com.feng.mustwin;

import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Created by sks on 2016/1/25.
 */
public class InterceptPhoneActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intercept_phone_dialog);
        getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
    }
}
