package com.jarhead.common.commonutils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jmsoldier on 2018/1/11.
 */

public class DateUtils {

	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_MONTH = 2592000;
	private static final long ONE_YEAR = 31104000;
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static String paresLongToDate(long param) {
		Date date = new Date(param);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		long time = date.getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟前";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时前" ;
		else if (ago <= ONE_DAY * 2)
			return "昨天";
		else if (ago <= ONE_DAY * 3)
			return "前天" ;
		else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			return day + "天前";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			return month + "个月前";
		} else {
			long year = ago / ONE_YEAR;
			return year + "年前" ;
		}

	}

}
