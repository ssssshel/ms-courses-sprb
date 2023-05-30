package com.tests.ms_courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tests.ms_courses.model.CourseModel;

public interface CourseRespository extends JpaRepository<CourseModel, Long> {

}
