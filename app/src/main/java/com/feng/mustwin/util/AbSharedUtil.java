package com.feng.mustwin.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//TODO: Auto-generated Javadoc

/**
 * © 2012 amsoft.cn 名称：AbSharedUtil.java 描述：保存到 SharedPreferences 的数据.
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2014-10-09 下午11:52:13
 */
public class AbSharedUtil {
    private static final String SHARED_PATH = AbAppConfig.SHARED_PATH;
    private SharedPreferences sp = null;
    private Editor edit = null;

    /**
     * 创建默认sp
     *
     * @param context
     */
    public AbSharedUtil(Context context) {
        this(context, PreferenceManager.getDefaultSharedPreferences(context));
    }

    /**
     * 通过sp创建sp
     *
     * @param context
     * @param sp
     */
    public AbSharedUtil(Context context, SharedPreferences sp) {
        this.sp = sp;
        edit = sp.edit();
    }

    /**
     * 通过文件名创建sp
     *
     * @param context
     * @param filename
     */
    public AbSharedUtil(Context context, String filename) {
        this(context, context.getSharedPreferences(filename,
                Context.MODE_WORLD_WRITEABLE));
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE);
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }

    public static void putLong(Context context, String key, Long value) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor edit = sharedPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public static Long getLong(Context context, String key) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getLong(key, 0l);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }

    public static String getString(Context context, String key, String def) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, def);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 是否第一次启动应用
     *
     * @param context
     * @return
     */
    public boolean isFirstStart(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            int curVersion = info.versionCode;
            int lastVersion = sp.getInt("version", 0);
            // 如果当前版本大于上次版本，该版本属于第一次启动
// 将当前版本写入preference中，则下次启动的时候，据此判断，不再为首次启动
            return curVersion > lastVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 是否第一次安装应用
     *
     * @param context
     * @return
     */
    public boolean isFirstInstall(Context context) {
        int install = sp.getInt("first_install", 0);
        return install == 0;

    }

    /**
     * 应用已启动
     *
     * @param context
     */
    public void setStarted(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            int curVersion = info.versionCode;
            sp.edit().putInt("version", curVersion).commit();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 应用已安装并启动
     *
     * @param context
     */
    public void setInstalled(Context context) {
        sp.edit().putInt("first_install", 1).commit();
    }


    /**
     * 是否需要改变数据
     *
     * @param context
     * @return
     */
    public boolean needChangeIndexContent(Context context, String openID) {

        String save = sp.getString(openID, "");
        String cur = getDateByNumber();
        return !save.equals(cur);
    }

    /**
     * 记录日期，决定是否数据是否需要改动
     *
     * @return
     */
    public static String getDateByNumber() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd",
                new Locale("zh"));
        String cur = s.format(new Date());
        return cur;
    }

    /**
     * 保存更新日期
     *
     * @param context
     */
    public void saveChangeIndexContent(Context context, String openID) {

        String cur = getDateByNumber();
        sp.edit().putString(openID, cur).commit();
    }

}
