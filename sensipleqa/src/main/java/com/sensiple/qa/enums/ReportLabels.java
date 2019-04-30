package com.sensiple.qa.enums;
/**
 * Labels for Reporting
 * @author BaPs
 *
 */
public enum ReportLabels {

	HEADER_TEXT("Sensiple Reports"),  
	PASS("Passed"),  
	FAIL("Failed"), 
	SKIP("Skipped"),  
	X_AXIS("Test Iterations <font size = 1> <i> (The graph illustrates last 10 executions) </i> </font>"),  
	//\n <font size = 1> <i> (The graph illustrates last 10 executions) </i> </font>"),
	Y_AXIS("No.of Test Cases"),  
	LINE_CHART_TOOLTIP("Test Cases"),  
	SENSIPLE_LOGO("sensiple.png"),  
	PROJ_LOGO(""),  PROJ_CAPTION(""), 
	PIE_CHART_LABEL("Test Cases Percent Distribution"),  
	TC_INFO_LABEL("Requirement Coverage/Build Info/Cycle - Description");

	private String label;

	private ReportLabels(String paramString)  {
		setLabel(paramString);
	}

	public String getLabel()  {
		return this.label;
	}

	public void setLabel(String paramString)  {
		this.label = paramString;
	}
}