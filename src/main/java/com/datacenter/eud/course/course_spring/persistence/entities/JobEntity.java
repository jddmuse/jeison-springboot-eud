package com.datacenter.eud.course.course_spring.persistence.entities;

import com.datacenter.eud.course.course_spring.utils.SQLConstants;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOBS")
@Data
@AllArgsConstructor
@NoArgsConstructor

@NamedNativeQuery(
		name = SQLConstants.NAME_JOB_ENTITY_RESULTSET_MAPPING, 
		query = SQLConstants.FIND_JOBS_BY_SALARY_RANGE, 
		resultSetMapping = "JobEntityRSM")

@SqlResultSetMapping(
		name = "JobEntityRSM",
		classes = @ConstructorResult(
				targetClass = JobEntity.class,
				columns = {
						@ColumnResult(name = "JOB_ID", type = String.class),
						@ColumnResult(name = "JOB_TITLE", type = String.class),
						@ColumnResult(name = "MIN_SALARY", type = Double.class),
						@ColumnResult(name = "MAX_SALARY", type = Double.class)
				}
		)
)

public class JobEntity {

	@Id
	@Column(name = "JOB_ID")
	private String jobId;
	@Column(name = "JOB_TITLE")
	private String jobTitle;
	@Column(name = "MIN_SALARY")
	private Double minSalary;
	@Column(name = "MAX_SALARY")
	private Double maxSalary;
}
