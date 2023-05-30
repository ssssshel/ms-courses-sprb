package com.tests.ms_courses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tests.ms_courses.dto.CourseDto;
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

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getCourseById(@PathVariable Long id) {
    ResponseDto response = courseService.getCourseById(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createCourse(@RequestBody CourseDto courseDto) {
    ResponseDto response = courseService.createCourse(courseDto);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateCourse(@RequestBody CourseDto courseDto) {
    ResponseDto response = courseService.updateCourse(courseDto);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseDto> deleteCourse(@PathVariable Long id) {
    ResponseDto response = courseService.deleteCourse(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }
}
