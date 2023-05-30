package com.tests.ms_courses.service.inter;

import com.tests.ms_courses.dto.CourseDto;
import com.tests.ms_courses.dto.ResponseDto;

public interface CourseServiceInter {
  public ResponseDto getAllCourses();

  public ResponseDto getCourseById(Long id);

  public ResponseDto createCourse(CourseDto course);

  public ResponseDto updateCourse(CourseDto course);

  public ResponseDto deleteCourse(Long id);
}
