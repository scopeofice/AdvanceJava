package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.entites.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseResponse {
	
	private String name;
	private Category type;
	private double fees;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime updateDateTime;
	public CourseResponse(String name, Category type, double fees, LocalDate startDate, LocalDate endDate,
			LocalDateTime updateDateTime) {
		super();
		this.name = name;
		this.type = type;
		this.fees = fees;
		this.startDate = startDate;
		this.endDate = endDate;
		this.updateDateTime = LocalDateTime.now();
	}
}
