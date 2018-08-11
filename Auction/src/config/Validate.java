package config;

import org.apache.commons.lang.StringUtils;

public class Validate {
	
	public static boolean isExistsData(String value){
		return !(StringUtils.equals(value, "")||StringUtils.equals(value, null));
	}
}
