package com.qingshu.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final SimpleDateFormat DAFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 字符串转日期
	 * 
	 * @param str
	 *            (yyyy-MM-dd)
	 * @return
	 */
	public static Date dstrToDate(String dstr) {
		return getDate(dstr, DAFAULT_DATE_FORMAT);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param str
	 *            (yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static Date tstrToDate(String tstr) {
		return getDate(tstr, TIMESTAMP_FORMAT);
	}

	/**
	 * 日期转字符串
	 * 
	 * @return(yyyy-MM-dd)
	 */
	public static String DateTodstr(Date date) {
		return getString(date, DAFAULT_DATE_FORMAT);
	}
	public static String DateToStr(Date date,String pattern){
		return getString(date, TIMESTAMP_FORMAT);
	}

	/**
	 * 日期转字符串
	 * 
	 * @param str
	 * @return(yyyy-MM-dd HH:mm:ss)
	 */
	public static String DateTotstr(Date date) {
		return getString(date, TIMESTAMP_FORMAT);
	}

	/**
	 * 字符串转Timestamp
	 * 
	 * @param str
	 *            (yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static Timestamp tstrToTimestamp(String tstr) {
		return new Timestamp(tstrToDate(tstr).getTime());
	}

	/**
	 * 字符串转Timestamp
	 * 
	 * @param str
	 *            (yyyy-MM-dd)
	 * @return
	 */
	public static Timestamp dstrToTimestamp(String tstr) {
		return new Timestamp(dstrToDate(tstr).getTime());
	}

	/**
	 * 字符串转Timestamp
	 * 
	 * @param str
	 *            (yyyy-MM-dd)
	 * @return
	 */
	public static String TimestampTodstr(Timestamp timestamp) {
		return getString(timestamp, DAFAULT_DATE_FORMAT);
	}

	/**
	 * 字符串转Timestamp
	 * 
	 * @param str
	 *            (yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String TimestampTotstr(Timestamp timestamp) {
		return getString(timestamp, TIMESTAMP_FORMAT);
	}

	/**
	 * 获取当期日期
	 * 
	 * @return (yyyy-MM-dd)
	 * 
	 */
	public static String getStrDate() {
		return getString(new Date(), DAFAULT_DATE_FORMAT);
	}
	/**
	 * 获取当期日期
	 * 
	 * @return (yyyyMMdd)
	 * 
	 */
	public static String getStrNo_Date() {
		return getString(new Date(), DATE_FORMAT);
	}

	/**
	 * 获取当期日期
	 * 
	 * @return (yyyy-MM-dd)
	 * 
	 */
	public static Date getDate() {
		return dstrToDate(getString(new Date(), DAFAULT_DATE_FORMAT));
	}

	/**
	 * 日期转时间戳
	 * 
	 * @return (yyyy-MM-dd)
	 * 
	 */
	public static Timestamp DateToTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 时间戳转日期
	 * 
	 * @return (yyyy-MM-dd)
	 * 
	 */
	public static Date TimestampToDate(Timestamp timestamp) {
		String str = TimestampTodstr(timestamp);
		return dstrToDate(str);
	}

	/**
	 * 获取当期时间戳
	 * 
	 * @return (yyyy-MM-dd HH:mm:ss)
	 * 
	 */
	public static String getStringTimestamp() {
		return getString(new Date(), TIMESTAMP_FORMAT);
	}

	/**
	 * 获取当期时间戳
	 * 
	 * @return (yyyy-MM-dd HH:mm:ss)
	 * 
	 */
	public static Timestamp getTimestamp() {
		return tstrToTimestamp(getString(new Date(), TIMESTAMP_FORMAT));
	}

	/**
	 * 根据Date获取整型月份
	 * 
	 * @param d
	 * @return
	 */
	public static Integer getIntMonth(Date d) {
		return getInteger(d, Calendar.MONDAY) + 1;
	}

	/**
	 * 获取日期的年份
	 * 
	 * @param date日期
	 * @return 年份
	 */
	public static Integer getIntYear(Date date) {
		return getInteger(date, Calendar.YEAR);
	}

	/**
	 * 获取日期的天数
	 * 
	 * @param date日期
	 * @return 年份
	 */
	public static Integer getIntDay(Date date) {
		return getInteger(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到某年某月的第一天
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return DAFAULT_DATE_FORMAT.format(cal.getTime());
	}

	/**
	 * 得到某年某月最后一天
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return DAFAULT_DATE_FORMAT.format(cal.getTime());
	}

	/**
	 * 取得Integer型的当前日期
	 * 
	 * @return
	 */
	public static Integer getIntToday() {
		return getIntDate(getDate());
	}

	/**
	 * 取得String型的当前月份(01)
	 */
	public static String getFormatMonthNow() {
		return String.format("%tm", getDate());
	}

	/**
	 * 格式化月份"%02d"
	 */
	public static String formatMonthNow(Integer month) {
		return String.format("%02d", month);
	}

	public static String[] getDays(String startTime, String endTime) {
		Integer integer = daysBwByString(startTime, endTime) + 1;
		String[] day = new String[integer];
		Integer sday = getIntDay(DateUtils.dstrToDate(startTime));
		Integer eday = getIntDay(DateUtils.dstrToDate(endTime));
		int j = 0;
		while (sday <= eday) {
			day[j] = sday.toString();
			sday++;
			j++;
		}
		return day;
	}

	public static String[] getDay(String startTime, String endTime) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		Integer integer = daysBwByString(startTime, endTime) + 1;
		String[] day = new String[integer];
		int[] date = parseTime(startTime);
		int[] edate = parseTime(endTime);
		start.set(date[0], date[1], date[2]);
		end.set(edate[0], edate[1], edate[2]);
		int i = date[2];
		int j = 0;
		while (i <= edate[2]) {
			System.out.println(start.get(Calendar.YEAR) + "-" + start.get(Calendar.MONTH) + "-" + start.get(Calendar.DATE));
			int d = start.get(Calendar.DATE);
			start.add(Calendar.DATE, 1);
			day[j] = formatMonthNow(d);
			i++;
			j++;
		}
		return day;
	}

	// format : YYYY-MM-DD
	private static int[] parseTime(final String timeString) {
		final int[] ret = new int[3];
		int index = 0;
		for (final String field : timeString.split("-")) {
			ret[index] = Integer.parseInt(field);
			index++;
		}
		return ret;
	}

	/**
	 * 取得Integer型的当前年份
	 * 
	 * @return
	 */
	public static Integer getIntYearNow() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 取得Integer型的当前年份
	 * 
	 * @return
	 */
	public static String getYearNow() {
		Calendar c = Calendar.getInstance();
		Integer year = c.get(Calendar.YEAR);
		return year.toString();
	}

	/**
	 * 取得Integer型的当前月份
	 * 
	 * @return
	 */
	public static Integer getIntMonthNow() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 根据Date获取整型日期
	 * 
	 * @param d
	 * @return
	 */
	public static Integer getIntDate(Date d) {
		if (d == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return getIntDate(c);
	}

	/**
	 * 计算两个整型日期之间的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer daysBetweenDate(Integer startDate, Integer endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		Calendar c1 = newCalendar(startDate);
		Calendar c2 = newCalendar(endDate);

		Long lg = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000 / 60 / 60 / 24;
		return lg.intValue();
	}

	/**
	 * 计算两个整型日期之间的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer daysBetweenDate(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		Long interval = endDate.getTime() - startDate.getTime();
		interval = interval / (24 * 60 * 60 * 1000);
		return interval.intValue();
	}

	/**
	 * 计算两个整型日期之间的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer daysBwByString(String startDate, String endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		Long interval = 0l;
		try {
			interval = dstrToDate(endDate).getTime() - dstrToDate(startDate).getTime();
			interval = interval / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return interval.intValue();
	}

	/**
	 * 计算两个日期之间的小时数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer hoursBetweenDate(String startDate, String endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		Long interval = dstrToDate(endDate).getTime() - dstrToDate(startDate).getTime();
		interval = interval / (60 * 60 * 1000);
		return interval.intValue();
	}

	/**
	 * 根据整型日期生成Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar newCalendar(int date) {
		int year = date / 10000;
		int month = (date % 10000) / 100;
		int day = date % 100;

		Calendar ret = Calendar.getInstance();
		ret.set(year, month - 1, day);
		return ret;
	}

	/**
	 * 根据Calendar获取整型日期
	 * 
	 * @param c
	 * @return
	 */
	private static Integer getIntDate(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		return year * 10000 + month * 100 + day;
	}

	/**
	 * 根据传入的年份和月份获得该月份的天数
	 * 
	 * @param year
	 *            年份-正整数
	 * @param month
	 *            月份-正整数
	 * @return 返回天数
	 */
	public static int getDayNumber(int year, int month) {
		int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (2 == month && 0 == (year % 4) && (0 != (year % 100) || 0 == (year % 400))) {
			days[1] = 29;
		}
		return (days[month - 1]);
	}

	/**
	 * 根据传入的年份和月份获得该月份的天数
	 * 
	 * @param year
	 *            年份-正整数
	 * @param month
	 *            月份-正整数
	 * @return 返回天数
	 */
	public static Integer getDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);// Java月份才0开始算
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		return dateOfMonth;
	}

	/**
	 * 判断当前日期是星期几<br>
	 * <br>
	 * 
	 * @param pTime
	 *            修要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	public static int dayForWeek(String pTime) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(DAFAULT_DATE_FORMAT.parse(pTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * long日期转string日期
	 */
	public static String longToString(long currentTime) {
		Date date = longToDate(currentTime);
		String strTime = DateTodstr(date);
		return strTime;
	}

	/**
	 * 增加日期的月份。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param monthAmount
	 *            增加数量。可为负数
	 * @return 增加月份后的日期字符串
	 */
	public static String addMonth(String date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * 
	 * @param date
	 *            日期字符串
	 * @param dateType
	 *            类型
	 * @param amount
	 *            数值
	 * @return 计算后日期字符串
	 */
	public static String addInteger(String date, int dateType, int amount) {
		Date myDate = dstrToDate(date);
		myDate = addInteger(myDate, dateType, amount);
		return DateTodstr(myDate);
	}
	public static String addInteger(String date, int dateType, int amount,String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date myDate = sdf.parse(date);
		myDate = addInteger(myDate, dateType, amount);
		return sdf.format(myDate);
	}

	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * 
	 * @param date
	 *            日期
	 * @param dateType
	 *            类型
	 * @param amount
	 *            数值
	 * @return 计算后日期
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	/**
	 * 增加日期的月份。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param monthAmount
	 *            增加数量。可为负数
	 * @return 增加月份后的日期
	 */
	public static Date addMonth(Date date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/**
	 * long日期转date
	 */
	public static Date longToDate(long currentTime) {
		Date dateOld = new Date(currentTime);
		String sDateTime = DateTodstr(dateOld);
		Date date = null;
		try {
			date = dstrToDate(sDateTime);
		} catch (Exception e) {

		}
		return date;
	}

	private static String getString(Object date, SimpleDateFormat format) {
		String str = null;
		try {
			str = format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	private static Date getDate(String str, SimpleDateFormat format) {
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取日期中的某数值。如获取月份
	 */
	private static int getInteger(Date date, int dateType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(dateType);
	}

	public static String getDateSx() {
		String dateSx = "";
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour >= 6 && hour < 8) {
			dateSx = "早上好,";
		} else if (hour >= 8 && hour < 11) {
			dateSx = "上午好,";
		} else if (hour >= 11 && hour < 13) {
			dateSx = "中午好,";
		} else if (hour >= 13 && hour < 18) {
			dateSx = "下午好,";
		} else {
			dateSx = "晚上好,";
		}
		return dateSx;
	}
	/**
	 * 得到年份的开始时间
	 * @param year
	 * @return
	 */
	public static String getYearStart(Integer year){
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONTH, 0);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		return DateTodstr(cl.getTime());
	}
	/**
	 * 得到年份的结束时间
	 * @param year
	 * @return
	 */
	public static String getYearEnd(Integer year){
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONTH, 11);
		cl.set(Calendar.DAY_OF_MONTH, 31);
		return DateTodstr(cl.getTime());
	}
	/**
	 * 得到 月份的开始时间
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthStart(String dateStr,String pattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		return DAFAULT_DATE_FORMAT.format(cl.getTime());
	}
	/**
	 * 得到月份的 结束时间
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthEnd(String dateStr,String pattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.add(Calendar.MONTH, 1);
		cl.add(Calendar.DAY_OF_MONTH, -1);
		return DAFAULT_DATE_FORMAT.format(cl.getTime());
	}
	
	/**
	 * 得到当前日期是当年的第几周
	 * @param dateStr
	 * @return
	 * @throws ParseException 
	 */
	public static int getWeekNum(String dateStr) throws ParseException{
		Date date = DAFAULT_DATE_FORMAT.parse(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int week_of_year = calendar.get(Calendar.WEEK_OF_YEAR);
		return week_of_year;
	}
	
	/**
	 * 获取昨天的日期
	 * @return
	 */
	public static String getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}
	/**
	 * 获取上个月的日期，形如 2014-04
	 * @return
	 */
	public static String getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
	}
	
	/**
	* 取得当前日期所在周的第一天
	*/
    public static String getFirstDayOfWeek(String date){
    	Date d = dstrToDate(date);
    	Calendar  c = Calendar.getInstance();
    	c.setFirstDayOfWeek(Calendar.MONDAY);
    	c.setTime(d);
    	c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
    	Date dd = c.getTime();
    	return DateTodstr(dd);
    }
    /**
    * 取得当前日期所在周的最后一天
    */
    public static String getLastDayOfWeek(String date){
    	Date d = dstrToDate(date);
    	Calendar  c = Calendar.getInstance();
    	c.setFirstDayOfWeek(Calendar.MONDAY);
    	c.setTime(d);
    	c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()+6);
    	Date dd = c.getTime();
    	return DateTodstr(dd);
    }
    /**
     * 得到当前日期所在季度的第一天
     * @param date
     * @return
     */
    public static String getFirstDayOfQuarter(String date){
    	String[] str = date.split("-");
    	Integer month = Integer.parseInt(str[1]);
    	if((month-1)%3!=0){
    		month = month - (month-1)%3;
    	}
    	if(month<10)
    		return str[0]+"-0"+month+"-01";
    	else
    		return str[0]+"-"+month+"-01";
    	
    }
    
    /**
     * 得到当前日期所在季度的最后一天
     * @param date
     * @return
     * @throws ParseException 
     */
    public static String getLastDayOfQuarter(String date) throws ParseException{
    	String[] str = date.split("-");
    	Integer month = Integer.parseInt(str[1]);
    	if(month%3!=0){
    		month = month + 3-month%3;
    	}
    	return getMonthEnd(str[0]+"-"+month+"-01", "yyyy-MM") ;
    	
    }
    
    /**
     * 根据月份得到季度
     * @return
     */
    public static int getQuarterByMonth(int month){
    	  if (month==1||month==2||month==3)
    		  return 1;
    	  else if (month==4||month==5||month==6)
    		  return 2;
    	  else if (month==7||month==8||month==9)
    		  return 3;
    	  else if (month==10||month==11||month==12)
    		  return 4;
    	  else 
    		  return 0;
    }

}
