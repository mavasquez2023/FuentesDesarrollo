package ztest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSimpleDateFormar {
	public static void main(String[] args) {
		//"2021-02-16T09:59:42-0300"
		
		
		//System.out.println(getStringDate(new Date()));
		
		
	}
	
	public static String getStringDate(Date input) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-ssss");
		String out = format.format(input);
		out = out.replace(" ", "T");
		return out;
	}
}
