package com.datacenter.eud.course.course_spring.persistence.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOB_HISTORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobHistoryEntity {
	
	@Id
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "JOB_ID")
	private String jobId;
	@Column(name = "DEPARTMENT_ID")
	private Long depatrtmentId;
}
