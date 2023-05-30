package com.tests.ms_courses.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tests.ms_courses.dto.CourseDto;
import com.tests.ms_courses.dto.ResponseDto;
import com.tests.ms_courses.model.CourseModel;
import com.tests.ms_courses.repository.CourseRespository;
import com.tests.ms_courses.service.inter.CourseServiceInter;
import com.tests.ms_courses.shared.Utils;

@Service
public class CourseService implements CourseServiceInter {
  @Autowired
  private CourseRespository courseRespository;

  @Override
  public ResponseDto getAllCourses() {
    try {
      List<CourseModel> coursesList = this.courseRespository.findAll();
      if (coursesList.isEmpty()) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, coursesList, false);
      }

      List<CourseDto> coursesDtoList = new ArrayList<CourseDto>();
      for (CourseModel courseObject : coursesList) {
        if (courseObject.getState() == false) {
          continue;
        }
        coursesDtoList.add(CourseDto.builder()
            .id(courseObject.getId())
            .name(courseObject.getName())
            .description(courseObject.getDescription())
            .state(courseObject.getState())
            .build());
      }
      return Utils.getResponse(HttpStatus.OK, coursesDtoList, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto getCourseById(Long id) {
    try {
      CourseModel courseModel = courseRespository.findById(id).orElse(null);
      if (courseModel == null || courseModel.getState() == false) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, null, false);
      }
      CourseDto courseDto = CourseDto.builder()
          .id(courseModel.getId())
          .name(courseModel.getName())
          .description(courseModel.getDescription())
          .state(courseModel.getState())
          .build();

      return Utils.getResponse(HttpStatus.OK, courseDto, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto createCourse(CourseDto course) {
    try {
      CourseModel courseModel = CourseModel.builder()
          .name(course.getName())
          .description(course.getDescription())
          .state(course.getState())
          .build();

      courseRespository.save(courseModel);
      course.setId(courseModel.getId());
      return Utils.getResponse(HttpStatus.CREATED, courseModel, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto updateCourse(CourseDto course) {
    try {
      CourseModel courseModel = courseRespository.findById(course.getId()).orElse(null);
      if (courseModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, courseModel, false);
      }

      courseModel.setName(course.getName());
      courseModel.setDescription(course.getDescription());
      courseModel.setState(course.getState());

      courseRespository.save(courseModel);
      return Utils.getResponse(HttpStatus.OK, courseModel, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto deleteCourse(Long id) {
    try {
      CourseModel courseModel = courseRespository.findById(id).orElse(null);
      if (courseModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, courseModel, false);
      }

      courseModel.setState(false);
      courseRespository.save(courseModel);
      return Utils.getResponse(HttpStatus.OK, courseModel, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }
}