package com.metro.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);

	public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_STA = "yyyy-MM-dd 00:00:00";
	public static final String DATE_FORMAT_END = "yyyy-MM-dd 23:59:59";
	private static SimpleDateFormat sdfymd = new SimpleDateFormat("yyyy年MM月dd日");
	public static final String DATE_FORMAT_LONGEXS = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_ALL = "yyyyMMddHHmmss";

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 * @author Yanming.Lv
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 得到现在小时
	 * 
	 * @return
	 * @author Yanming.Lv
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}
	
	/**
	 * 获取当前时分秒
	 * @return
	 * @author Yanming.Lv
	 * @since  2017年12月8日下午3:59:57
	 */
	public static String getHhmmss(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(currentTime);
	}
	
	/**
	 * 获取系统当前年
	 * 
	 * @return
	 */
	public static int getSystemYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * 
	 * @return
	 * @author Yanming.Lv
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 比较当前时间与活动时间的毫秒数，小于当前结束时间返回当前毫秒数，大于返回-1
	 * 
	 * @param giveDate
	 * @return
	 */
	public static long compare_time(String giveDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_LONG);
		long lg = -1;
		try {
			String nowDateTime = getNowDateTime();
			Date endDate = sdf.parse(giveDate);
			Date nowDate = sdf.parse(nowDateTime);
			if (nowDate.getTime() < endDate.getTime()) {
				lg = endDate.getTime() - nowDate.getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lg;
	}

	public static Date formatDateStr(String dateStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_LONG).parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date formatShortDateStr(String dateStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_SHORT).parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 设置当前时间为当天的最初时间（即00时00分00秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setStartDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	public static Calendar setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal;
	}

	/**
	 * 把源日历的年月日设置到目标日历对象中
	 * 
	 * @param destCal
	 * @param sourceCal
	 */
	public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal) {
		destCal.set(Calendar.YEAR, sourceCal.get(Calendar.YEAR));
		destCal.set(Calendar.MONTH, sourceCal.get(Calendar.MONTH));
		destCal.set(Calendar.DAY_OF_MONTH, sourceCal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 格式化日期为
	 * 
	 * @return
	 */
	public static String formatEnDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
	}

	public static String getDateAndTime() {
		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());

	}

	public static String getDateAndTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);

	}

	public static String getDateAndTimeLong(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHORT);
		return sdf.format(date) + " 00:00:00";

	}

	public static String getYearAndMonth() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		return simpleDateFormat.format(calendar.getTime());
	}

	public final static Timestamp stringToTS(String time) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = null;
		try {
			ts = new Timestamp(sf.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	public final static Timestamp stringToTsDate(String time) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = null;
		try {
			ts = new Timestamp(sf.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * @function 得到现在的时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @auther jiangwanyu
	 * 
	 * @return String
	 */
	public final static String getNowDateTime() {

		Date nowDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sf.format(nowDate);
	}

	public final static String getNowDate() {

		Date nowDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(nowDate);
	}

	/**
	 * @function 自定义 日期格式
	 * @param dateFormat
	 * @return
	 */
	public final static String getNowDateTime(String dateFormat) {
		Date nowDate = new Date();

		SimpleDateFormat sf = null;
		try {
			sf = new SimpleDateFormat(dateFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		return sf.format(nowDate);
	}

	/**
	 * @funciton 得到 Timestamp 类型的nowDateTime
	 * 
	 * @author credit004
	 * 
	 * @return Timestamp
	 */
	public final static Timestamp getNowDateTimeTs() {
		return stringToTS(getNowDateTime());
	}

	public final static Timestamp getNowDayTimeTs() {
		return stringToTsDate(getNowDateTime());
	}

	/**
	 * @funciton 节点任务期限---XXXX年XX月XX日 星期X
	 * @param dayLimit
	 * @return
	 */
	public final static String getTaskTimeLimit(int dayLimit) {

		Date nowDate = new Date();

		int mod = dayLimit % 5;
		int other = dayLimit / 5 * 7;

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);

		/*
		 * 判断双休日 jiang
		 */
		for (int i = 0; i < mod;) {
			calendar.add(Calendar.DATE, 1);
			switch (calendar.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
			case Calendar.SATURDAY:
				break;
			default:
				i++;
				break;
			}
		}
		if (other > 0) {
			calendar.add(Calendar.DATE, other);
		}

		// calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)
		// + dayLimit);

		return sf.format(calendar.getTime());
	}

	/**
	 * @funciton 节点任务期限---XXXX年XX月XX日 星期X ---放假天数【非双休人为放假 比如 春节 比如 十一】
	 * @param dayLimit
	 * @return
	 */
	public final static String getTaskTimeLimit(int dayLimit, int holidays) {

		Date nowDate = new Date();

		dayLimit = dayLimit + holidays;

		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 E");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + dayLimit);

		return sf.format(calendar.getTime());
	}

	/**
	 * @funciton 得到 （time）时间 后的第（months）个月
	 */
	public final static Timestamp getNextTerm(Timestamp time, int months) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(time);
		calendar.add(Calendar.MONTH, months);

		Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

		return timestamp;
	}

	/** 两个日期的 日期差 参数s1：被减数 参数s2 :减数 */
	public final static int getSurplusDays(Timestamp s1, Timestamp s2) {

		Calendar c1 = Calendar.getInstance();
		c1.setTime(s1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(s2);

		long diff = (c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60 * 60 * 24);

		return ((Long) diff).intValue();
	}
	
	/**
	 * 忽略时分秒 如2018-01-01 ~ 2018-01-02 
	 * 返回1
	 */
	public final static int getSurplusDays(String s1, String s2) {

		long diff = (strToDate(s1).getTime() - strToDate(s2).getTime()) / (1000 * 60 * 60 * 24);

		return ((Long) diff).intValue();
	}

	/**
	 * 在日期上加days天，得到新的日期
	 * 
	 * @return
	 */
	public final static Date addDaysToDate(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	/**
	 * @function 得到自定义 日期格式
	 * @param dateFormat
	 * @return
	 */
	public final static String getFormatDateTime(Date date, String dateFormat) {
		SimpleDateFormat sf = null;
		try {
			sf = new SimpleDateFormat(dateFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		return sf.format(date);
	}

	 /**
     * 在日期上加minutes分钟，得到新的日期
     * @return
     * @throws ParseException 
     */
    public final static Date addMinutesToDate(String time, int minutes) throws ParseException {
    	SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_LONG);
    	Date dt = sf.parse(time);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();
    }
    
    /**
	 * 在日期上加小时月，得到新的日期
	 * 
	 * @return
	 */
	public final static Date addMinutesToDate(Date date, int minutes) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, minutes); // 目前世间加小時
		return c.getTime();
	}
	
	/**
	 * 在日期上加小时月，得到新的日期
	 * 
	 * @return
	 */
	public final static Date addHourToDate(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, hour); // 目前世间加小時
		return c.getTime();
	}

	/**
	 * 指定日期添加制定小时
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public final static Date addHourToDate2(Date date, int hour) {
		int i = date.getHours();
		date.setHours(i + hour); // 目前世间加小時
		return date;
	}

	/**
	 * 在日期上加months月，得到新的日期
	 * 
	 * @return
	 */
	public final static Date addMonthsToDate(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return c.getTime();
	}

	/**
	 * 在日期上加months月和日，得到新的日期
	 * 
	 * @return
	 */
	public final static Date addMonthsToDate(Date date, int months, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		c.add(Calendar.DATE, days);
		// c.roll(Calendar.DATE, days);
		return c.getTime();
	}

	/**
	 * 计算两日期之间相隔月份和天数
	 * 
	 * @return
	 */
	public static Map<Integer, Integer> getMonthAndDaysBetweenDate(String date1, String date2) {
		Map<Integer, Integer> map = new HashMap();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		try {
			d1 = sd.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sd.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int months = 0;// 相差月份
		int days = 0;
		int y1 = d1.getYear();
		int y2 = d2.getYear();
		int dm1 = d2.getMonth();// 起始日期月份
		int dm2 = d2.getMonth();// 结束日期月份
		int dd1 = d1.getDate(); // 起始日期天
		int dd2 = d2.getDate(); // 结束日期天
		if (d1.getTime() < d2.getTime()) {
			months = d2.getMonth() - d1.getMonth() + (y2 - y1) * 12;
			if (dd2 < dd1) {
				months = months - 1;
			}
			days = getDaysBetweenDate(
					getFormatDateTime(addMonthsToDate(DateUtil.parseDate(date1, "yyyy-MM-dd"), months), "yyyy-MM-dd"),
					date2);
			map.put(1, months);
			map.put(2, days);
		}
		return map;
	}

	/**
	 * 计算两日期之间相隔月份和天数，目前只有按月计算用到 2012.03.09
	 * 
	 * @return
	 */
	public static Map<Integer, Integer> getMonthAndDaysBetweenDate2(String date1, String date2) {
		Map<Integer, Integer> map = new HashMap();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		try {
			d1 = sd.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sd.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int months = 0;// 相差月份
		int days = 0;
		int y1 = d1.getYear();
		int y2 = d2.getYear();
		int dm1 = d2.getMonth();// 起始日期月份
		int dm2 = d2.getMonth();// 结束日期月份
		int dd1 = d1.getDate(); // 起始日期天
		int dd2 = d2.getDate(); // 结束日期天
		if (d1.getTime() < d2.getTime()) {
			months = d2.getMonth() - d1.getMonth() + (y2 - y1) * 12;
			if (dd2 < dd1) {
				months = months - 1;
			}
			if (dd2 == dd1 - 1) {
				months = months + 1;
			}
			days = getDaysBetweenDate2(
					getFormatDateTime(addMonthsToDate(DateUtil.parseDate(date1, "yyyy-MM-dd"), months), "yyyy-MM-dd"),
					date2);
			map.put(1, months);
			map.put(2, days);
		}
		return map;
	}

	/**
	 * 计算两日期之间相隔月份和天数，目前只有按月计算用到 2012.03.09
	 * 
	 * @return
	 */
	public static Map<Integer, Integer> getMonthAndDaysBetweenDate3(String date1, String date2) {
		Map<Integer, Integer> map = new HashMap();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		try {
			d1 = sd.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sd.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int months = 0;// 相差月份
		int days = 0;
		int y1 = d1.getYear();
		int y2 = d2.getYear();
		int dm1 = d2.getMonth();// 起始日期月份
		int dm2 = d2.getMonth();// 结束日期月份
		int dd1 = d1.getDate(); // 起始日期天
		int dd2 = d2.getDate(); // 结束日期天
		if (d1.getTime() < d2.getTime()) {
			months = d2.getMonth() - d1.getMonth() + (y2 - y1) * 12;
			if (dd2 < dd1) {
				months = months - 1;
			}
			days = getDaysBetweenDate2(
					getFormatDateTime(addMonthsToDate(DateUtil.parseDate(date1, "yyyy-MM-dd"), months), "yyyy-MM-dd"),
					date2);
			if (dd2 == dd1) {
				days = 0;
			}
			map.put(1, months);
			map.put(2, days);
		}
		return map;
	}

	/**
	 * 计算两日期之间的天数
	 * 
	 * @return
	 */
	public final static int getDaysBetweenDate(String date1, String date2) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		try {
			d1 = sd.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sd.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c1 = Calendar.getInstance();

		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		long diff = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		return ((Long) diff).intValue();
	}

	/**
	 * 计算两日期之间的天数,目前只有按月计算用到2012.03.09
	 * 
	 * @return
	 */
	public final static int getDaysBetweenDate2(String date1, String date2) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		try {
			d1 = sd.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sd.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int rdays = 0;
		if (d2.getDate() >= d1.getDate()) {
			rdays = d2.getDate() - d1.getDate() + 1;
		} else {
			rdays = d2.getDate() + 30 - d1.getDate() + 1;
		}
		return rdays;
	}

	/**
	 * 
	 * @param dateString
	 *            日期字符串 如2011-01-03
	 * @param dateFormate
	 *            日期格式 如yyyy-MM-dd
	 * @return 根据传入的日期字符串和日期格式返回指定格式的日期
	 */
	public final static Date parseDate(String dateString, String dateFormate) {
		SimpleDateFormat sd = new SimpleDateFormat(dateFormate);
		Date date = null;
		try {
			date = sd.parse(dateString);
		} catch (Exception ex) {
			logger.error("Pase the Date(" + dateString + ") occur errors:" + ex.getMessage());

		}
		return date;
	}

	public static String getNowDate(String formart) {
		String temp_str = "";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formart);
		temp_str = sdf.format(dt);
		return temp_str;
	}

	/**
	 * 计算两日期之间的相差月份和天数
	 * 
	 * @return
	 */
	public final static int getMonthDaysBetweenDate(String date1, String date2) {

		Date d1 = new Date(date1);
		return 0;

	}

	public static Date strToDate(String format, String dateStr) {
		Date date = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static String dateToStr(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/** 得到今天中小时（24H） */
	public final static int getHourOfDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(calendar.HOUR_OF_DAY);
	}

	/** 得到今天中分钟 */
	public final static int getMinOfDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(calendar.MINUTE);
	}

	public final static long getDateTimeNow() {
		Date date = new Date();
		return date.getTime();
	}

	/**
	 * 返回两个日期的大小
	 * 
	 * @param dt1
	 * @param dt2
	 * @return dt1大于dt2 return -1 * dt1等于dt2 return 0 * dt2>dt1 return >0
	 */
	public static long compare_date(Date dt1, Date dt2) {
		if (dt1.getTime() > dt2.getTime()) {
			return -1;
		} else if (dt1.getTime() < dt2.getTime()) {
			long msPerDay = 1000 * 60 * 60 * 24; // 一天的毫秒数
			long msSum = dt2.getTime() - dt1.getTime();
			long day = msSum / msPerDay;
			return day;
		} else {
			return 0;
		}
	}

	public final static Date getRoundDaysDate(String dateStr) throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sd.parse(dateStr);
		Date date1 = sd1.parse(dateStr);
		if (date1.getMonth() == date.getMonth()) {// 同一月 即"2014-02-25"
													// 不是"2014-02-30"的情况
			return date1;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();

	}

	/**
	 * 在日期上加months月，得到新的日期
	 * 
	 * @return
	 */
	public final static Date setDayToDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}

	/**
	 * 在日期上设置天，当天数大于 本月最大天数时，取当月的最大天数
	 * 
	 * @return
	 */
	public final static Date setRoundDayToDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (c.getActualMaximum(Calendar.DAY_OF_MONTH) < day) {
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else {
			c.set(Calendar.DAY_OF_MONTH, day);
		}
		return c.getTime();
	}

	/**
	 * @param date1
	 *            起始日期
	 * @param date2
	 *            截止日期
	 * @return 两日期之间的天数
	 */
	public final static int getDaysBetweenDate(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);
		long diff = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		return ((Long) diff).intValue();
	}

	public static String getMaxMonthDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	public static String getMinMonthDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 格式化日期为
	 * 
	 * @return
	 */
	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHORT);
		if (date == null) {
			return null;
		}
		return sdf.format(date);
	}

	/**
	 * 
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */

	public static String getSpecifiedDayBefore(String specifiedDay) {

		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();

		Date date = null;

		try {

			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		c.setTime(date);

		int day = c.get(Calendar.DATE);

		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

		return dayBefore;

	}

	/**
	 * 
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * 
	 * @return
	 * 
	 */

	public static String getSpecifiedDayAfter(String specifiedDay) {

		Calendar c = Calendar.getInstance();

		Date date = null;

		try {

			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		c.setTime(date);

		int day = c.get(Calendar.DATE);

		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

		return dayAfter;

	}

	/**
	 * yyyy年MM月dd日
	 * 
	 * @return
	 */
	public static String getDateStringYmd(Date date) {
		return sdfymd.format(date);
	}

	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	public static Date longStrToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_LONG);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static String getNextDay() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}


	public static String formatDate(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_LONG).format(date);
	}

	public static String formatDateSta(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_STA).format(date);
	}

	public static String formatDateEnd(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_END).format(date);
	}

	public static String parseDate(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHORT);
		Date buyTime = null;
		try {
			buyTime = sdf.parse(dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(buyTime);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		return year + "年" + month + "月" + day + "日";
	}

	/**
	 * 获取过去或者未来 任意天内的日期数组
	 * 
	 * @param intervals
	 *            intervals天内
	 * @param type
	 *            获取过去日期 还是未来日期
	 * @return 日期数组
	 */
	public static List<String> getPastOrFuture(int intervals, String type, Date date) {
		List<String> pastDaysList = new ArrayList<>();
		List<String> fetureDaysList = new ArrayList<>();
		for (int i = 1; i <= intervals; i++) {
			pastDaysList.add(getPastDate(i, date));
			fetureDaysList.add(getFetureDate(i, date));
		}
		// 获取过去的多少天
		if ("1".equals(type)) {
			return pastDaysList;
		} else {
			return fetureDaysList;
		}
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取未来 第 past 天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getFetureDate(int past, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取当前月份的所有日期
	 * 
	 * @param date
	 * @return
	 */
	public static List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) == month) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	/**
	 *
	 * @param now
	 * @return 返回格式为 'yyyy-MM-dd' 的字符串
	 */
	public static String getFormatedDayStr(Date now) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT_SHORT);
		String str = (now == null ? null : df.format(now));
		return str;
	}
	
	public synchronized static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date == null) {
			return null;
		}
		return sdf.format(date);
	}

	public static String getStrDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yymmdd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 根据指定的时间和数字，为分钟+valueof
	 *
	 * @return
	 */
	public static String getAddMinuteDay(String curTime, String valueOf) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date now = sdf.parse(curTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.MINUTE, Integer.parseInt(valueOf));
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 比较String类型的时间大小
	 * 
	 * @param a
	 * @param b
	 * @return true 表示a<b
	 */
	public static boolean compareString(String a, String b) {
		java.util.Date x = stringToUtilDate(a);
		java.util.Date y = stringToUtilDate(b);
		return compareAWithB(x, y);
	}
	
	/**
	 * @函数名称：stringToUtilDate
	 * @功能描述：将String型的日期格式转换为Util型的日期格式
	 * @param str
	 * @return
	 */
	public static java.util.Date stringToUtilDate(String str) {
		if (null != str && str.length() > 0) {
			try {
				// 对两种时间格式进行转化。
				if (str.length() <= DATE_FORMAT_LONG.length()) { // 只包含日期。
					return (new SimpleDateFormat(DATE_FORMAT_LONG)).parse(str);
				} else { // 日期和时间都有
					return (new SimpleDateFormat(DATE_FORMAT_LONG)).parse(str);
				}
			} catch (ParseException ex) {
				ex.printStackTrace();
				return null;
			}
		} else
			return null;
	}
	
	public static boolean compareAWithB(Date a, Date b) {
		return a.before(b);
	}
	
	/**
	 * 计算指定的两个日期之间有多少天
	 *
	 * @return
	 */
	public static int getDayNum(Date startTime, Date endTime) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			long time1 = cal.getTimeInMillis();
			cal.setTime(endTime);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getEndTime(Date startTime, Date endTime) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			long time1 = cal.getTimeInMillis();
			cal.setTime(endTime);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / 1000;

			return String.valueOf(between_days);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
//		System.out.println("============1>: " + getNextDay(DateUtil.getStringDateShort(),"1"));
//		System.out.println("============1>: " + getAddDay(DateUtil.getStringDateShort(),1));
		
		
//		System.out.println(DateUtil.formatShortDateStr("2016-06-01"));
//		System.out.println(DateUtil.getHourOfDay());
//		System.out.println(
//				DateUtil.compare_date(DateUtil.strToDate(DateUtil.format(new Date())), DateUtil.strToDate("2017-5-4")));
	}
}
