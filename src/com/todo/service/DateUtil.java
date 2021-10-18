package com.todo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getDays(String day1){
		long days = 0;
		String result ="";
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date current = new Date();
			Date last = f.parse(day1);
			long diff = current.getTime() - last.getTime();
			days = diff / (24*60*60*1000);
			if (days>0)
				result= "+" + Math.abs(days);
			else if (days<0)
				result= "-" + Math.abs(days);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
				
	}

}
