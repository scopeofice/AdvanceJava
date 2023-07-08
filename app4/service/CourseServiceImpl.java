package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.customexception.CourseNotFoundException;
import com.app.customexception.InvalidDataException;
import com.app.customexception.InvalidIDException;
import com.app.dto.ApiResponse;
import com.app.dto.CourseResponse;
import com.app.entites.Course;
import com.app.repository.CourseRepo;

@Service
@Transactional
public class CourseServiceImpl  implements CourseService{
	@Autowired
	private CourseRepo cr;
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Course> getAllCourse() {
		return cr.findAll();
	}

	@Override
	public CourseResponse addCourse(CourseResponse obj) {
		if(obj.getStartDate().isBefore(obj.getEndDate())) {
			Course c=mapper.map(obj, Course.class);
			cr.save(c);
			CourseResponse c1=mapper.map(c, CourseResponse.class);
			return c1;
		}
		throw new InvalidDataException("Invalid data entered");
	}

	@Override
	public String updateCourse(CourseResponse obj, Long cid) {
		if(cr.existsById(cid)) {
			Course c=mapper.map(obj, Course.class);
			cr.save(c);
			String msg="Course updated";
			return msg;
		}
		throw new InvalidIDException("Invalid id");
	}

	@Override
	public String deleteCourse(Long cid) {
		String msg="Not Deleted";
		if(cr.existsById(cid)) {
			 cr.deleteById(cid);
			 msg="Deleted";
		}
		return msg;
	}

	@Override
	public List<Course> getCourseByName(String name) {
		List<Course> list= cr.findByName(name);
		if(list.isEmpty()) {
			throw new CourseNotFoundException("Name invalid");
		}
		return list;
	}

}
