package com.datacenter.eud.course.course_spring.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeTipoDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private Date hierDate;
	private String departmentName;
	private String jobTitle;
	private String email;
	private Double salary;
	private Double commissionPct;
	private String phoneNumber;
	private String managerName;

	public EmployeeTipoDTO(String firstName, String lastName, Date hierDate, String departmentName, String jobTitle,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.hierDate = hierDate;
		this.departmentName = departmentName;
		this.jobTitle = jobTitle;
		this.email = email;
	}

	public EmployeeTipoDTO(Long id, String firstName, String lastName, Double salary, Double commissionPct) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.commissionPct = commissionPct;
	}

	public EmployeeTipoDTO(Long id, String firstName, String lastName, String email, Date hierDate,
			String phoneNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.hierDate = hierDate;
		this.phoneNumber = phoneNumber;
	}
	
	

}
