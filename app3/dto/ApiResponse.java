package com.app.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ApiResponse {
	private String msg;
	private LocalDateTime timestamp;
	public ApiResponse(String msg) {
		super();
		this.msg = msg;
		this.timestamp = LocalDateTime.now();
	}
	
}
