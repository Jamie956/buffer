package uCal.demo;

import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Calendar;  
import java.util.Date;  
import java.util.GregorianCalendar;  
import java.util.List;  
  
public class UtilsContinuousTime {  
    public static int getBetweenDays(Date startDate, Date endDate) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(startDate);  
        long startTime = cal.getTimeInMillis();  
        cal.setTime(endDate);  
        long endTime = cal.getTimeInMillis();  
        long between_days = (endTime - startTime) / (1000 * 3600 * 24);  
        return Integer.parseInt(String.valueOf(between_days));  
    }  
  
    public static List<String> getContinuousTime(String startTime, String endTime) throws ParseException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
        Date startDate = sdf.parse(startTime);  
        Date endDate = sdf.parse(endTime);  
        int betweenDay = getBetweenDays(startDate, endDate) + 1;  
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int _year = cal.get(Calendar.YEAR);
        int _month = cal.get(Calendar.MONTH);
        int _day = cal.get(Calendar.DAY_OF_MONTH);
        
//        System.out.println("_day -> "+cal.get(Calendar.WEEK_OF_YEAR));
//        cal.add(Calendar.WEEK_OF_YEAR, 1);
//        System.out.println("_day -> "+cal.get(Calendar.WEEK_OF_YEAR));
        
        Calendar curTime = new GregorianCalendar(_year, _month, _day);  
        List<String> dataList = new ArrayList<String>();  
  
        for (int i = 1; i <= betweenDay; i++) {  
            String dateKey = sdf.format(curTime.getTime());  
            dataList.add(dateKey);  
            curTime.add(Calendar.DATE, 1);
        }  
        return dataList;  
    }  
  
    public static void main(String[] args) {  
        try {  
            for (String str : getContinuousTime("20171002","20171031")) {  
                System.out.println(str);  
            }  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
    }  
  
}  