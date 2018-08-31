package com.metro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseUtil {
	private static Logger logger = LoggerFactory.getLogger(BaseUtil.class);
    private static SimpleDateFormat sdf8 = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat sdf10 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf14 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdf19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdfSSS =new SimpleDateFormat("yyyyMMddHHmmssSSS");
    
    private static SimpleDateFormat sdfymd = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat sdfmdHm = new SimpleDateFormat("MM月dd日 HH:mm");
    
	public static String getUUID(){
		UUID uuid = UUID.randomUUID(); 
		return uuid.toString();
	}
	/**
	 * 精确到毫秒 yyyyMMddHHmmssSSS
	 * @return
	 */
	public static String getDateStringSSS(){
	   return sdfSSS.format(new Date());	
	}
	/**
	 * yyyyMMdd
	 * @return
	 */
	public static String getDateString8(Date date){
		return sdf8.format(date);
	}
	/**
	 * yyyy-MM-dd
	 * @return
	 */
	public static String getDateString10(Date date){
		return sdf10.format(date);
	}
	/**
	 * yyyyMMddHHmmss
	 * @return
	 */
	public static String getDateString14(Date date){
		return sdf14.format(date);
	}
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateString19(){
		return sdf19.format(new Date());
	}
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateString19(Date date){
		return sdf19.format(date);
	}
	/**
	 * yyyy年MM月dd日
	 * @return
	 */
	public static String getDateStringYmd(Date date){
		return sdfymd.format(date);
	}
	/**
	 * MM月dd日 HH:mm
	 * @param date
	 * @return
	 */
	public static String getDateStringMdHm(Date date){
		return sdfmdHm.format(date);
	}
	
	/**
	 * 当前日期加上x天<br>
	 * yyyy-MM-dd
	 * @return
	 */
	public static String getDateString10(int days){
		return sdf10.format(DateUtil.addDaysToDate(new Date(),days));
	}
	
	public static String getDateString19(int days){
		return sdf19.format(DateUtil.addDaysToDate(new Date(),days));
	}
	
	public static String getDateString10(Date date,int days){
		return sdf10.format(DateUtil.addDaysToDate(date,days));
	}
	/**
	 * 日期加上x天<br>
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateString19(Date date,int days){
		return sdf19.format(DateUtil.addDaysToDate(date,days));
	}
	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getDate10(String date) throws Exception{
		return sdf10.parse(date);
	}
	
	
	public static Date getDate19(String date) throws Exception{
		return sdf19.parse(date);
	}
	
	/**
	 * 当前日期加上x天<br>
	 * yyyy年MM月dd日
	 * @return
	 */
	public static String getDateStringYmd(int days){
		return sdfymd.format(DateUtil.addDaysToDate(new Date(),days));
	}

	public static boolean isNull(Object obj)
    {
        if (null == obj || "".equals(obj.toString()) )
        {
            return true;
        } else
            return false;
    }
    public static boolean isNull(String str)
    {
        if (null == str || "".equals(str))
        {
            return true;
        } else
            return false;
    }
    
   
    /**
     * 将一个字节数组转换为一个十六进制数字的字符串。
     */
    private static String toHex(byte buffer[])
    {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++)
        {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
        }
        return sb.toString();
    }
   
    
    
  
    /**
     * 日期比较<br>
     * if(date1--date2) return -1  <br>
     * if(date1==date2)  return 0  <br>
     * if(date2--date1)  return 1
     * @param date1  格式 yyyy-MM-dd
     * @param date2  格式 yyyy-MM-dd
     * @return
     */
    public static int compareDate(String date1,String date2){
        try {
            Date dt1 = sdf10.parse(date1);
            Date dt2 = sdf10.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    public static int compareDateTime(String date1,String date2){
    	try {
    		Date dt1 = sdf19.parse(date1);
    		Date dt2 = sdf19.parse(date2);
    		if (dt1.getTime() > dt2.getTime()) {
    			return 1;
    		} else if (dt1.getTime() < dt2.getTime()) {
    			return -1;
    		} else {
    			return 0;
    		}
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    	return 0;
    }
    
    public static int compareDateTimeStr(Date dt1,String date2){
    	try {
    		Date dt2 = sdf19.parse(date2);
    		if (dt1.getTime() > dt2.getTime()) {
    			return 1;
    		} else if (dt1.getTime() < dt2.getTime()) {
    			return -1;
    		} else {
    			return 0;
    		}
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    	return 0;
    }
    
    /**
     * 两日期相隔天数 date2 - date1
     * @param date1  格式 yyyyMMdd
     * @param date2  格式 yyyyMMdd
     * @return
     */
    public final static int getNumBetween2date(String date1,String date2){
		Date d1 = null;
		Date d2=null;
		try {
			d1 = sdf10.parse(date1);
			d2 = sdf10.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance(); 
		c2.setTime(d2);
		long diff = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24);
		return ((Long)diff).intValue();
	
    }
    public final static int getNumBetweenHour(String date1,String date2){
    	Date d1 = null;
    	Date d2=null;
    	try {
    		d1 = sdf19.parse(date1);
    		d2 = sdf19.parse(date2);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	
    	Calendar c1 = Calendar.getInstance();
    	c1.setTime(d1);
    	Calendar c2 = Calendar.getInstance(); 
    	c2.setTime(d2);
    	long diff = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60);
    	return ((Long)diff).intValue();
    	
    }
    public final static long getTimeMillisBetween2date(String date1,String date2){
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf19.parse(date1);
			d2 = sdf19.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance(); 
		c2.setTime(d2);
		long diff = (c2.getTimeInMillis()-c1.getTimeInMillis())/1000;
		return diff;
	
    }
   
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(getDateStringMdHm(new Date()));
		
	}
}
