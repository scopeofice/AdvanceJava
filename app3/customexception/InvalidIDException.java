package com.app.customexception;

public class InvalidIDException extends RuntimeException {
	public InvalidIDException(String msg) {
		super(msg);
	}
}
