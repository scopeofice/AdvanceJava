package com.app.customexception;

public class InvalidDataException extends RuntimeException {
	public InvalidDataException(String msg) {
		super(msg);
	}
}
