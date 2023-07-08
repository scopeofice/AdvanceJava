package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CourseResponse;
import com.app.entites.Course;

public interface CourseService {
	List<Course> getAllCourse();
	CourseResponse addCourse(CourseResponse obj);
	String updateCourse(CourseResponse obj,Long cid);
	String deleteCourse(Long cid);
	List<Course> getCourseByName(String name);
}
