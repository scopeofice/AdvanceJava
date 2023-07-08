package com.app.customexception;

public class CourseNotFoundException extends RuntimeException {
	public CourseNotFoundException(String msg) {
		super(msg);
	}
}
