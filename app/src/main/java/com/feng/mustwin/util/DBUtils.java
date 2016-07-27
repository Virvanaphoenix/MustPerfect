package com.feng.mustwin.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.feng.mustwin.R;
import com.feng.mustwin.bean.BlackBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sks on 2016/1/25.
 */
public class DBUtils {

    public static SQLiteDatabase openDatabase(Context context) {

        String DATABASE_PATH = context.getFilesDir().getAbsolutePath();
        String DATABASE_FILENAME = "phone_nomber";
        try {
            // 获得文件的绝对路径
            String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
            File dir = new File(DATABASE_PATH);

            if (!dir.exists()) {
                dir.mkdir();
            };

            if (!(new File(databaseFilename)).exists()) {
                InputStream is = context.getResources().openRawResource(R.raw.black);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                // 开始复制文件
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
            Log.i("feng","database = " + database);
            return database;
        } catch (Exception e) {
            Log.i("open error", e.getMessage());
        }
        return null;
    }

    /**
     * 没写完
     * @param context
     * @param phone
     * @return
     */
    public static List<BlackBean> query(Context context,String phone){
        SQLiteDatabase db = openDatabase(context);
        List<BlackBean> num = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from black where tel =? ", new String[]{phone});
        while (cursor.moveToNext()){
            BlackBean bean = new BlackBean();
            bean.setmPhoneNum(cursor.getString(cursor.getColumnIndex("tel")));
            bean.setmReportContent(cursor.getString(cursor.getColumnIndex("story")));
            bean.setmReportTime(cursor.getString(cursor.getColumnIndex("create_time")));
            num.add(bean);
        }
        return num;
    }
}
