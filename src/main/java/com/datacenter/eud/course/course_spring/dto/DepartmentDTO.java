package com.datacenter.eud.course.course_spring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DepartmentDTO {
	private Long id;
	@NotBlank
	private String name;
	private Long managerId;
	private Long locationId;
}
