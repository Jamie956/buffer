package uCal.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LastDays {

	public static Date getLastDays(int days) {
		Date currentTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentTime);
		cal.add(Calendar.DATE, -days);
		Date lastDay = cal.getTime();
		return lastDay;
	}
	
	public static Date getNextDays(int days) {
		Date currentTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentTime);
		cal.add(Calendar.DATE, days);
		Date lastDay = cal.getTime();
		return lastDay;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		
		System.out.println("toDay -> "+formatter.format(new Date()));
		System.out.println("L7D-> "+formatter.format(getLastDays(7)));
		System.out.println("L30D-> "+formatter.format(getLastDays(30)));
		
		
	}

}
