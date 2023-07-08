package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entites.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
	List<Course> findByName(String name);
}
