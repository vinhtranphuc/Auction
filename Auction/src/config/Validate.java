package config;

import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Validate {

	public static boolean isExistsData(String value) {
		return !(StringUtils.equals(value, "") || StringUtils.equals(value, null));
	}

	public static boolean validateDate(String strDate) {
		/* Check if date is 'null' */
		if (strDate.trim().equals("")) {
			return true;
		}
		/* Date is not 'null' */
		else {
			/*
			 * Set preferred date format, For example MM-dd-yyyy,
			 * MM.dd.yyyy,dd.MM.yyyy etc.
			 */
			SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-mm-dd");
			sdfrmt.setLenient(false);
			/*
			 * Create Date object parse the string into date
			 */
			try {
				Date javaDate = sdfrmt.parse(strDate);
				System.out.println(strDate + " is valid date format");
			}
			/* Date format is invalid */
			catch (ParseException e) {
				System.out.println(strDate + " is Invalid Date format");
				return false;
			}
			/* Return true if date format is valid */
			return true;
		}
	}
	
	public static boolean validateDateTime(String strDate) {
		/* Check if date is 'null' */
		if (strDate.trim().equals("")) {
			return true;
		}
		/* Date is not 'null' */
		else {
			/*
			 * Set preferred date format, For example MM-dd-yyyy,
			 * MM.dd.yyyy,dd.MM.yyyy etc.
			 */
			SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			sdfrmt.setLenient(false);
			/*
			 * Create Date object parse the string into date
			 */
			try {
				Date javaDate = sdfrmt.parse(strDate);
				System.out.println(strDate + " is valid datetime format");
			}
			/* Date format is invalid */
			catch (ParseException e) {
				System.out.println(strDate + " is Invalid datetime format");
				return false;
			}
			/* Return true if date format is valid */
			return true;
		}
	}
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}
	
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	public static void main(String[] args) {
		System.out.println(Validate.validateDateTime("2018-08-03 15:20:30"));
	}
}
