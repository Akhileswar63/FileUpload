package com.ems.api.exception;

public class NoContentFoundException  extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoContentFoundException(String msg) {
		super(msg);
	}

}
