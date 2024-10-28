package com.datacenter.eud.course.course_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDTO {
	private String jobId;
	private String jobTitle;
	private Double minSalary;
	private Double maxSalary;
}
