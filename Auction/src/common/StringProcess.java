package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.sf.json.JSONArray;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jul 5, 2018
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jul 5, 2018        	Vinh          	Create
 */

public class StringProcess {

	public static String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0").format(Calendar.getInstance().getTime());
	
	public static String toJSONArrayString(Object arrayList){
		
		JSONArray jsonArray = JSONArray.fromObject(arrayList);
		
		return jsonArray.toString();
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
}
