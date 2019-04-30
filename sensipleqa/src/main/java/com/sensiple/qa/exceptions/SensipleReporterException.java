package com.sensiple.qa.exceptions;

public class SensipleReporterException
extends Exception
{
	private String message;

	public SensipleReporterException() {}

	public SensipleReporterException(String message)  {
		this.message = message;
	}

	public SensipleReporterException(String message, Throwable ex)  {
		super(message, ex);
		this.message = message;
	}
	public String toString()  {
		return "[Custom Reporter Exception] " + this.message;
	}
}
