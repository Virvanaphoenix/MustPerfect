package com.feng.mustwin.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.feng.mustwin.util.AbToastUtil;

/**
 * Created by feng on 2016/1/25.
 */

public class PhoneStatReceiver extends BroadcastReceiver {

    private static final String TAG = "ffffff";
    private static boolean mIncomingFlag = false;
    private static String mIncomingNumber = null;

    @Override
    public void onReceive(final Context context, Intent intent) {
        // 如果是拨打电话
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            mIncomingFlag = false;
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            setResultData(null); //清除电话
            Log.i(TAG, "call OUT:" + phoneNumber);
            if (phoneNumber.contains("18710235507")){
                AbToastUtil.showToast(context,"您拨打的电话疑似黑中介\n小心上当受骗。。。");
            }

        } else {
            // 如果是来电
            TelephonyManager tManager = (TelephonyManager) context
                    .getSystemService(Service.TELEPHONY_SERVICE);
            switch (tManager.getCallState()) {
                case TelephonyManager.CALL_STATE_RINGING:
                    mIncomingNumber = intent.getStringExtra("incoming_number");
                    Log.i(TAG, "RINGING :" + mIncomingNumber);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if (mIncomingFlag) {
                        Log.i(TAG, "incoming ACCEPT :" + mIncomingNumber);
                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    if (mIncomingFlag) {
                        Log.i(TAG, "incoming IDLE");
                    }
                    break;
            }
        }
    }


    /*@Override
    public void onReceive(Context context, Intent intent) {
        String number = getResultData();
        if("5556".equals(number)){
            setResultData(null);//挂断
        }else{
            number = "12593"+ number; //其他，则加区号
            setResultData(number);
        }
    }*/
}