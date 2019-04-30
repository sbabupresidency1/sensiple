package com.sensiple.qa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Common utils
 *
 */
public class Utils {

	public static String getCurrentTime()  {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		Date localDate = new Date();
		return localSimpleDateFormat.format(localDate);
	}

	public static boolean isGrid() {
		return System.getProperty("zado.grid", "").equalsIgnoreCase("grid");
	}
}
