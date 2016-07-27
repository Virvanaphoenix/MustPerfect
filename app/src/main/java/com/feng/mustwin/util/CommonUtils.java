package com.feng.mustwin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.Time;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by FengZhongXia on 2015/11/23.
 */
public class CommonUtils {

    /**
     * 获取当年年月日
     *
     * @param type
     */
    public static int getdate(int type) {
        Time time = new Time("GMT+8");
        time.setToNow();
        int date = 0;
        if (type == 1) {
            date = time.year;
        } else if (type == 2) {
            date = time.month + 1;
        } else {
            date = time.monthDay;
        }
        return date;
    }

    /**
     * 是否为手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}d{1}-?d{8}$)|"
                + "(^0[3-9] {1}d{2}-?d{7,8}$)|"
                + "(^0[1,2]{1}d{1}-?d{8}-(d{1,4})$)|"
                + "(^0[3-9]{1}d{2}-? d{7,8}-(d{1,4})$))";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(mobiles);
        return matcher.matches();
    }

    /**
     * 验证是否为邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void hideKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context, View v) {
        ((InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE))
                .toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
    }

    /**
     * 时间戳转行成时间
     *
     * @param time
     * @return
     */
    public static String conversionTime(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        return sdr.format(new Date(Long.parseLong(time) * 1000L));

    }

    public static String getDateToString(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        return sf.format(new Date(time));
    }

    public static String formatTime(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }

    public static boolean checkNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


//    /**
//     * 判断是否登录
//     * @param context
//     * @return
//     */
//    public static boolean checkLogin(Context context){
//        String session = AbSharedUtil.getString(context, Constant.SESSION_ID, null);
//        if(null == session){
//            AbToastUtil.showToast(context, context.getString(R.string.please_login));
//            Intent i = new Intent(context, LoginActivity.class);
//            context.startActivity(i);
//            return false;
//        }
//        return true;
//    }
}
