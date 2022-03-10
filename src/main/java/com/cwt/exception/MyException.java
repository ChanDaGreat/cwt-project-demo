package com.cwt.exception;

public class MyException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public MyException(String exception) {
		super(exception);
	}
}
