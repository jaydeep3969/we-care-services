package com.wecare.userservice.exception;

public class WecareException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public WecareException() {
		super();
	}

	public WecareException(String errors) {
		super(errors);
	}
}
