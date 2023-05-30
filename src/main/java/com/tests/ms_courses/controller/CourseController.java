package com.tests.ms_courses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tests.ms_courses.dto.ResponseDto;
import com.tests.ms_courses.service.CourseService;

@RestController
@RequestMapping("/v1/courses")
public class CourseController {
  @Autowired
  private CourseService courseService;

  @GetMapping
  public ResponseEntity<ResponseDto> getAllCourses() {
    ResponseDto response = courseService.getAllCourses();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }
}
