package com.app.entites;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {
	private String name;
	@Enumerated(EnumType.STRING)
	private Category type;
	private LocalDate startDate;
	private LocalDate endDate;
	private double fees;
	private LocalDateTime updateDateTime;
	public Course(String name, Category type, LocalDate startDate, LocalDate endDate, double fees) {
		super();
		this.name = name;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fees = fees;
	}
	
	
	
	
}
