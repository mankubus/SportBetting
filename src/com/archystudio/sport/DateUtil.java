package com.archystudio.sport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	public static Date parseDate(String date)
	{
		SimpleDateFormat _dateformat = new SimpleDateFormat();
		_dateformat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		_dateformat.applyPattern("MM/dd/yy HH:mm:ss");
		
		try {
			return _dateformat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String deparseDate(Date date)
	{
		SimpleDateFormat _dateformat = new SimpleDateFormat();
		_dateformat.applyPattern("MM/dd/yy HH:mm:ss");
		
		return _dateformat.format(date);
	}
	
	public static String getStrDate(Date _date)
	{
		SimpleDateFormat _dateformat = new SimpleDateFormat();
		_dateformat.setTimeZone(TimeZone.getDefault());
		_dateformat.applyPattern("dd.MM.yyyy, EEEEE");
		
		return _dateformat.format(_date);
	}
	
	public static String getStrTime(Date _date)
	{
		SimpleDateFormat _dateformat = new SimpleDateFormat();
		_dateformat.setTimeZone(TimeZone.getDefault());
		_dateformat.applyPattern("HH:mm");
		
		return _dateformat.format(_date);
	}
	
	public static String getShortDate(Date _date)
	{
		SimpleDateFormat _dateformat = new SimpleDateFormat();
		_dateformat.setTimeZone(TimeZone.getDefault());
		_dateformat.applyPattern("dd.MM.yy, HH:mm");
		
		return _dateformat.format(_date);
	}
}
