package com.sensiple.qa.utils;

import com.sensiple.qa.enums.LogAs;
/**
 * Get Logs for reports
 * @author BaPs
 *
 */
public class Steps {

	private String actions;
	private String description;
	private String inputValue;
	private String expectedValue;
	private String actualValue;
	private String time;
	private String lineNum;
	private String screenShot;
	private LogAs logAs;
	
	public String getActions()  {
		return this.actions;
	}

	public void setActions(String paramString)  {
		this.actions = paramString;
	}

	public String getDescription()  {
		return this.description;
	}

	public void setDescription(String paramString)  {
		this.description = paramString;
	}

	public String getInputValue()  {
		return this.inputValue;
	}

	public void setInputValue(String paramString)  {
		this.inputValue = paramString;
	}

	public String getExpectedValue()  {
		return this.expectedValue;
	}

	public void setExpectedValue(String paramString)  {
		this.expectedValue = paramString;
	}

	public String getActualValue()  {
		return this.actualValue;
	}

	public void setActualValue(String paramString)  {
		this.actualValue = paramString;
	}

	public String getTime()  {
		return this.time;
	}

	public void setTime(String paramString)  {
		this.time = paramString;
	}

	public String getLineNum()  {
		return this.lineNum;
	}

	public void setLineNum(String paramString)  {
		this.lineNum = paramString;
	}

	public String getScreenShot()  {
		return this.screenShot;
	}

	public void setScreenShot(String paramString)  {
		this.screenShot = paramString;
	}

	public LogAs getLogAs()  {
		return this.logAs;
	}

	public void setLogAs(LogAs paramLogAs)  {
		this.logAs = paramLogAs;
	}
}

