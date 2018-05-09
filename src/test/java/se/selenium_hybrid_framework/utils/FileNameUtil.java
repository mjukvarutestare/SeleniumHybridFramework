package se.selenium_hybrid_framework.utils;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;

public class FileNameUtil {
	
	public static String getFileName(String fileFormat){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(Date.from(Instant.now()));
		return String.format(fileFormat, cal);
	}
	
}
