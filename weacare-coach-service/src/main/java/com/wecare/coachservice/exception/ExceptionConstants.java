package com.wecare.coachservice.exception;

public enum ExceptionConstants {
	SERVER_ERROR("server.error"),
	USER_NOT_FOUND("user.not.found"),
	GENERAL_EXCEPTION_MESSAGE("general.exception");
	
	private final String type;
	
	private ExceptionConstants(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}
