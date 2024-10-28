package com.datacenter.eud.course.course_spring.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	private Long id;
	private String firstName;
	private String lastName;	
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private Double salary;
	private Double commissionPct;
	private Long managerId;
	private Long departmentId;
}
