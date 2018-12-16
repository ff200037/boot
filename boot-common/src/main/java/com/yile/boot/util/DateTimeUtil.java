package com.yile.boot.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {

	public static String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 时间字符串  转  时间 yyyy-MM-dd 格式
	 * @param date
	 */
	 public static Date stringToDate(String date) throws ParseException{
		 SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
		 Date resultDate = sf.parse(date);
		 return resultDate;
	 }
	 
	  /**获取当前日期时间  yyyy-MM-dd
	   */
	  public static String nowDateToString(){
		  SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
		  String date = sf.format(new Date());
		  return date;
	  }
		 
	/**计算两个日期相差多少天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getDays(Date startDate, Date endDate) {
		Long ms=endDate.getTime()-startDate.getTime();
		long days=ms/(1000*60*60*24);
		long remain=ms%(1000*60*60*24);
		if (remain>0) {
			days++;
		}
		return days;
	}
	/**计算两个日期相差多少分钟
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMinutes(Date startDate, Date endDate) {
		Long ms=getTotleMils(startDate, endDate);
//		Long ms=endDate.getTime()-startDate.getTime();//原先的写法
		//1分钟=60000毫秒
		Long minuts=ms/60000;
		return minuts.intValue();
	}
	/**计算两个日期直接的毫秒数，按0秒算
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getTotleMils(Date startDate, Date endDate) {
		Calendar startCalendar=Calendar.getInstance();
		startCalendar.setTime(startDate);
		startCalendar.set(Calendar.SECOND, 0);//按0秒算
		
		Calendar endCalendar=Calendar.getInstance();
		endCalendar.setTime(endDate);
		endCalendar.set(Calendar.SECOND, 0);//按0秒算
		
		Long ms=endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis();
//		long totalMills=endDate.getTime()-startDate.getTime();//总的秒数
		return ms;
	}

	public static int getYears(Date startDate, Date endDate) {
		Long ms=endDate.getTime()-startDate.getTime();
		//1年=31536000000毫秒
		Long years=ms/31536000000L;
		return years.intValue();
	}

	public static int getHours(Date startDate, Date endDate) {
		Long ms=endDate.getTime()-startDate.getTime();
		//1小时=3600000毫秒
		Long hours=ms/3600000;
		Long zzz=ms%3600000;
		//不足一小时算成一小时
		if (zzz>0) {
			hours=hours+1;
		}
		return hours.intValue();
	}

	public static int getHours2(long ms) {
		//1小时=3600000毫秒
		Long hours = ms / 3600000;
		Long zzz = ms % 3600000;
		// 不足一小时算成一小时
		if (zzz > 0) {
			hours = hours + 1;
		}
		return hours.intValue();
	}

	public static Date getBeginTimeOfDate(Date date,int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (days>0) {
			calendar.add(Calendar.DATE, days);
		}
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getEndTimeOfDate(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (days>0) {
			calendar.add(Calendar.DATE, days);
		}
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	public static boolean isInSameDay(Date startTime, Date nowDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (format.format(startTime).equals(format.format(nowDate))) {
			return true;
		} else {
			return false;
		}
		
	}
	/**计算几天后的日期
	 * @param startTime
	 * @param days
	 * @return
	 */
	public static Date computeDateAfterXDays(Date startTime,int days) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(startTime);
		calendar.add(Calendar.DAY_OF_MONTH,days);
		Date date=calendar.getTime();
		return date;
	}
	
	public static void main(String[] args) throws Exception {
		Date startDate = DateTimeUtil.stringToDate("2018-12-11");
		Date endDate = DateTimeUtil.stringToDate(DateTimeUtil.nowDateToString());
		Long n = DateTimeUtil.getDays(startDate, endDate);
		System.out.println(n);
		
		Date startDate2 = DateTimeUtil.stringToDate("2018-12-13");
		Date endDate2 = DateTimeUtil.stringToDate(DateTimeUtil.nowDateToString());
		Long n2 = DateTimeUtil.getDays(startDate2, endDate2);
		System.out.println(n2);
	}
}
