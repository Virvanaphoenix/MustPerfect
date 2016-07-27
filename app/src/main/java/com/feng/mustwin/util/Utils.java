package com.feng.mustwin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by my on 2015/11/26.
 */
public class Utils {

    public static String string2IntTime(int time) {
        String t = "";
        int hour = time / 3600;
        int min;
        if (hour > 0) {
            min = (time - hour * 3600) / 60;
        } else {
            min = time / 60;
        }
        int sec;
        if (min > 0) {
            sec = time - hour * 3600 - min * 60;
        } else if (hour > 0) {
            sec = time - hour * 3600;
        } else {
            sec = time;
        }

        if (hour < 10) {
            t = "0" + hour + ":";
        } else {
            t = hour + ":";
        }
        if (min < 10) {
            t = t + "0" + min + ":";
        } else {
            t = t + min + ":";
        }
        if (sec < 10) {
            t = t + "0" + sec;
        } else {
            t = t + sec;
        }
        return t;
    }
//    public static String time2Date(String time){
//        return getStandardDate(getStringToDate(time));
//    }

//    File externalFiles = getExternalFilesDir(null);
//    Log.e("fffff", "Environment.getExternalStorageDirectory() = " + Environment.getExternalStorageDirectory());
//    Log.e("fffff", "externalFiles.getAbsolutePath() = " + externalFiles.getAbsolutePath());
//    Log.e("fffff", "getFilesDir().getAbsolutePath() = " + getFilesDir().getAbsolutePath());
    /**
     * 时间转换为时间戳
     *
     * @param time
     * @return
     */
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date.getTime();
    }

    //    /**
//     * 将时间戳转为代表"距现在多久之前"的字符串
//     * @param timeStr   时间戳
//     * @return
//     */
//    public static String getStandardDate(long timeStr) {
//
//        StringBuffer sb = new StringBuffer();
//
//        long t = timeStr;
//        long time = System.currentTimeMillis() - t;
//        long mill = (long) Math.ceil(time /1000);//秒前
//
//        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前
//
//        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时
//
//        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前
//        if(day -1 >30){
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date d = new Date(t);
//            return sdf.format(d);
//        }else
//        if (day - 1 > 0&& day-1 <30) {
//            sb.append(day + MyApplication.getInstance().getString(R.string.day));
//        } else if (hour - 1 > 0) {
//            if (hour >= 24) {
//                sb.append("1"+MyApplication.getInstance().getString(R.string.day));
//            } else {
//                sb.append(hour + MyApplication.getInstance().getString(R.string.hour));
//            }
//        } else if (minute - 1 > 0) {
//            if (minute == 60) {
//                sb.append("1"+MyApplication.getInstance().getString(R.string.hour));
//            } else {
//                sb.append(minute + MyApplication.getInstance().getString(R.string.min));
//            }
//        } else if (mill - 1 > 0) {
//            if (mill == 60) {
//                sb.append("1"+MyApplication.getInstance().getString(R.string.min));
//            } else {
//                sb.append(mill +MyApplication.getInstance().getString(R.string.sec));
//            }
//        } else {
//            sb.append(MyApplication.getInstance().getString(R.string.just));
//        }
//        if (!sb.toString().equals(MyApplication.getInstance().getString(R.string.just))) {
//            sb.append(MyApplication.getInstance().getString(R.string.ago));
//        }
//        return sb.toString();
//    }
    public static void hideKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static boolean isGPRS(Context context) {
        ConnectivityManager conMan = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetworkInfo = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mobileNetworkInfo.isConnected()) {
            return true;
        } else
//            (wifiNetworkInfo.isConnected())
        {
            return false;
        }
    }


}