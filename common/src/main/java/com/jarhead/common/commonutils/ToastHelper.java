package com.jarhead.common.commonutils;

import android.util.Log;
import android.widget.Toast;

import com.jarhead.common.baseapp.BaseApplication;


/**
 * Created by jiming1 on 2016/7/6.
 */
public class ToastHelper {

    public static boolean DEBUG = true;

    public static void toast(String msg) {
        try {
            Toast.makeText(BaseApplication.getBaseApplication(), msg, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            toastException(e);
        }

    }


    public static void toastException(Exception e) {

        if (DEBUG) {
            try {
                Toast.makeText(BaseApplication.getBaseApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("XXX", e.getMessage());
            } catch (Exception e1) {
                Toast.makeText(BaseApplication.getBaseApplication(), "toast出错", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(BaseApplication.getBaseApplication(), "服务器数据错误", Toast.LENGTH_LONG).show();
        }


    }
}
