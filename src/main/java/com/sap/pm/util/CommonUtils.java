package com.sap.pm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {
	
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String convertToString(Date date){

		String str = formatter.format(date);
		return str;
	}
	public static Date convertToDate(String strDate){
		Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return date;
	}
	
	public static Date addMinutesToDate(Date date, int minutes){
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.MINUTE, minutes);
		 
		 return cal.getTime();
	}
}
