package com.feng.mustwin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

public class MainActivity2 extends ActionBarActivity {
    private CallPhoneBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBroadcastReceiver = new CallPhoneBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);//添加此Action来拦截来去电电话
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);//添加此Action来拦截来电电话状态
        registerReceiver(mBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onDestroy() {
        //取消注册，防止内存溢出
        unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }

    public class CallPhoneBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

            String inComingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);//去电
            String outGogingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER); //来电
            String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if (Intent.ACTION_NEW_OUTGOING_CALL.equals(intent.getAction())) {
                if (outGogingNumber == null) {
                    outGogingNumber = getResultData();
                }
                if (shouldCancel(outGogingNumber)) //判断号码是不是需要拦截
                {
                    Log.i("fengzhongxia", "去电号码1：phoneState=" + phoneState + ",拦截");
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    setResultData(null);
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                } else {
                    Log.i("fengzhongxia", "去电号码2：phoneState=" + phoneState + ",不拦截");
                    setResultData(reformatNumber(outGogingNumber));
                }
                Log.i("fengzhongxia", "去电号码3：phoneState=" + phoneState + ",phoneNumber=" + outGogingNumber);
            } else if (!TextUtils.isEmpty(inComingNumber) && TelephonyManager.EXTRA_STATE_RINGING.equals(phoneState)) {
                //关于来电拦截，网上例子很多，如：http://blog.csdn.net/wangfayinn/article/details/8004023
                //注意TelephonyManager.EXTRA_STATE_RINGING.equals(phoneState)必须判断，否则来去电判断不准确
                Log.e("fengzhongxia", "来电号码：inComingNumber=" + phoneState + ",phoneNumber=" + inComingNumber);
            }

        }

        //拦截变更号码
        private String reformatNumber(String phoneNumber) {
            return "12593".concat(phoneNumber);
        }

        //判断该号码是否为危险号码
        private boolean shouldCancel(String phoneNumber) {
            Log.i("fengzhongxia", "shouldCancel = " + phoneNumber);
            Log.i("fengzhongxia", "\"18710235507\".equals(phoneNumber) = " + "18710235507".equals(phoneNumber));
            return "18710235507".equals(phoneNumber);
        }
    }

//    /**
//     * 摸你去电
//     *
//     * @param v
//     */
//    public void onClickToCallPhone(View v) {
//        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13724551306"));
//        MainActivity2.this.startActivity(intent);
//    }
}
