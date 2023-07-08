package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CourseResponse;
import com.app.entites.Course;
import com.app.service.CourseService;

@RestController
@RequestMapping("/course")
@Validated
public class CourseController {
	public CourseController() {
		System.out.println("inside ctor of "+getClass());
	}
	@Autowired
	private CourseService cs;
	
	@GetMapping
	public List<Course> listAll(){
		return cs.getAllCourse();
	}
	@GetMapping("/name/{name}")
	public List<Course> getByCourseName(String name) {
		return cs.getCourseByName(name);
	}
	@PostMapping
	public ResponseEntity<?> addCourse(@RequestBody CourseResponse obj) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cs.addCourse(obj));
	}
	@PutMapping("{cid}")
	public ApiResponse updateCourse(@RequestBody CourseResponse obj, @PathVariable Long cid) {
		return new ApiResponse(cs.updateCourse(obj, cid));
	}
	@DeleteMapping
	public ApiResponse deleteCourse(@PathVariable Long cid) {
		return new ApiResponse(cs.deleteCourse(cid));
	}
}
