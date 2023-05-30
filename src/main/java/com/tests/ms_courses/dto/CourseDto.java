package com.tests.ms_courses.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDto {
  private Long id;
  private String name;
  private String description;
  private Boolean state;
}
