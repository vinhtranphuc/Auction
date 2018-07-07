package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import model.bean.AuctionCouponBean;
import net.sf.json.JSONArray;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jan 20, 2015
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jan 20, 2015        	VinhTP1          Create
 */

public class StringProcess {

	public static String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0").format(Calendar.getInstance().getTime());
	
	public static String toJSONArrayString(Object arrayList){
		
		JSONArray jsonArray = JSONArray.fromObject(arrayList);
		
		return jsonArray.toString();
	}
	
}

