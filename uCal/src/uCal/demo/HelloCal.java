package uCal.demo;

import java.util.Calendar;

public class HelloCal {
	public static void main(String[] args) {
		test1();
//		test2();
		
	}
	
	public static void test1() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 8, 19);
		
		int year =cal.get(Calendar.YEAR);   
		int month=cal.get(Calendar.MONTH)+1;
		int day =cal.get(Calendar.DAY_OF_MONTH);
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		int weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		System.out.println("year -> "+year);
		System.out.println("month -> "+month);
		System.out.println("day -> "+day);
		System.out.println("weekOfYear -> "+weekOfYear);
		System.out.println("dayOfYear -> "+dayOfYear);
		System.out.println("weekOfMonth -> "+weekOfMonth);
		System.out.println("dayOfWeek -> "+dayOfWeek);
	}
	
	
	public static void test2() {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
//		System.out.println("Today -> " + format.format(cal.getTime()));
		
		int year =cal.get(Calendar.YEAR);   
		int month=cal.get(Calendar.MONTH)+1;   
		int day =cal.get(Calendar.DAY_OF_MONTH);  
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		int weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		
		System.out.println("year -> "+year);
		System.out.println("month -> "+month);
		System.out.println("day -> "+day);
		System.out.println("weekOfYear -> "+weekOfYear);
		System.out.println("dayOfYear -> "+dayOfYear);
		System.out.println("weekOfMonth -> "+weekOfMonth);
		System.out.println("dayOfWeek -> "+dayOfWeek);
	}
}
