package com.sensiple.qa.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.sensiple.qa.exceptions.SensipleReporterException;
/**
 * Referring local setting property for Report
 * @author BaPs
 *
 */
public class SettingsFile {
	private static Properties properties;

	public static void initSettingsFile() throws SensipleReporterException  {
		create(Directory.SETTINGSFile);
		set("run", "0");
		set("passedList", "");
		set("failedList", "");
		set("skippedList", "");
		set("testRunDT", "");
	}

	public static void create(String paramString) throws SensipleReporterException  {
		File localFile = new File(paramString);
		try
		{
			if (!localFile.exists())
			{
				localFile.createNewFile();
			}
			else
			{
				localFile.delete();
				localFile.createNewFile();
			}
		}
		catch (IOException localIOException)
		{
			throw new SensipleReporterException("Unable To Create Required Files for Custom Reports");
		}
	}

	public static void open() throws SensipleReporterException  {
		properties = new Properties();
		try
		{
			properties.load(new FileReader(Directory.SETTINGSFile));
		}
		catch (FileNotFoundException localFileNotFoundException)
		{
			throw new SensipleReporterException("Settings File Not Available");
		}
		catch (IOException localIOException)
		{
			throw new SensipleReporterException("Unable To Create Required Files for Custom Reports");
		}
	}

	public static void close() throws SensipleReporterException  {
		try
		{
			properties.store(new FileWriter(Directory.SETTINGSFile), "");
		}
		catch (FileNotFoundException localFileNotFoundException)
		{
			throw new SensipleReporterException("Settings File Not Available");
		}
		catch (IOException localIOException)
		{
			throw new SensipleReporterException("Unable To Create Required Files for Custom Reports");
		}
		finally
		{
			properties = null;
		}
	}

	public static void correctErrors() throws NumberFormatException, SensipleReporterException  {
		int i = Integer.parseInt(get("run"));
		int j = get("passedList").split(";").length;
		int k = get("failedList").split(";").length;
		int m = get("skippedList").split(";").length;
		if (isFirstParamBig(i, j, k, m))
		{
			int n = i - j;
			String str1 = get("passedList");
			for (int i1 = 0; i1 < n; i1++) {
				str1 = str1 + 0 + ';';
			}
			set("passedList", str1);
			n = i - k;
			String str2 = get("failedList");
			for (int i2 = 0; i2 < n; i2++) {
				str2 = str2 + 0 + ';';
			}
			set("failedList", str2);
			n = i - m;
			String str3 = get("skippedList");
			for (int i3 = 0; i3 < n; i3++) {
				str3 = str3 + 0 + ';';
			}
			set("skippedList", str3);
			return;
		}
		if (isFirstParamBig(j, i, k, m)) {
			return;
		}
		if (isFirstParamBig(k, j, i, m)) {
			return;
		}
		if (isFirstParamBig(m, j, k, i)) {}
	}

	private static boolean isFirstParamBig(int paramInt1, int paramInt2, int paramInt3, int paramInt4)  {
		return (paramInt1 > paramInt2) && (paramInt1 > paramInt3) && (paramInt1 > paramInt4);
	}

	public static int getHighestTestCaseNumber() throws SensipleReporterException  {
		String[] arrayOfString1 = get("passedList").split(";");
		String[] arrayOfString2 = get("failedList").split(";");
		String[] arrayOfString3 = get("skippedList").split(";");
		int[] arrayOfInt1 = getIntArrayFromStringArray(arrayOfString1);
		int[] arrayOfInt2 = getIntArrayFromStringArray(arrayOfString2);
		int[] arrayOfInt3 = getIntArrayFromStringArray(arrayOfString3);
		int i = getBiggestNumber(arrayOfInt1);
		int j = getBiggestNumber(arrayOfInt2);
		int k = getBiggestNumber(arrayOfInt3);
		int m = getBiggestNumber(new int[] { i, j, k });
		return m;
	}

	public static int getBiggestNumber(int[] paramArrayOfInt) {
		int i = paramArrayOfInt[0];
		for (int j = 1; j < paramArrayOfInt.length; j++) {
			if (paramArrayOfInt[j] > i) {
				i = paramArrayOfInt[j];
			}
		}
		return i;
	}

	public static int[] getIntArrayFromStringArray(String[] paramArrayOfString)  {
		int[] arrayOfInt = new int[paramArrayOfString.length];
		for (int i = 0; i < paramArrayOfString.length; i++) {
			arrayOfInt[i] = Integer.parseInt(paramArrayOfString[i]);
		}
		return arrayOfInt;
	}

	public static String get(String propertyKey) throws SensipleReporterException  {
		open();
		String str = properties.getProperty(propertyKey);
		close();
		return str;
	}

	public static void set(String key, String value) throws SensipleReporterException  {
		open();
		properties.setProperty(key, value);
		close();
	}
}
