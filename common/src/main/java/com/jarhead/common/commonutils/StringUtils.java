package com.jarhead.common.commonutils;

import android.content.Context;
import android.text.ClipboardManager;

import java.text.DecimalFormat;

/**
 * Created by jmsoldier on 2017/8/23.
 */

public class StringUtils {

	/**
	 * 实现文本复制功能
	 * add by wangqianzhou
	 *
	 * @param content
	 */
	public static void copy(String content, Context context) {
// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setText(content.trim());
	}

	/**
	 * 实现粘贴功能
	 * add by wangqianzhou
	 *
	 * @param context
	 * @return
	 */
	public static String paste(Context context) {
// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		return cmb.getText().toString().trim();
	}

	public static String getMoney(String money) {
		double d = Double.parseDouble(money);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}

}
