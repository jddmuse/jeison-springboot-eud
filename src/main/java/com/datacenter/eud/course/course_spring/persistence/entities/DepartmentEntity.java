package com.datacenter.eud.course.course_spring.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "DEPARTMENTS")
@Data
@Builder
@AllArgsConstructor
public class DepartmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DepartmentEntityGnerator")
	@SequenceGenerator(sequenceName = "DEPARTMENTS_SEQ", name = "DepartmentEntityGnerator", allocationSize = 1, initialValue = 1)
	@Column(name = "DEPARTMENT_ID")
	private Long id;
	@Column(name = "DEPARTMENT_NAME")
	private String name;
	@Column(name = "MANAGER_ID")
	private Long managerId;
	@Column(name = "LOCATION_ID")
	private Long locationId;
	
	public DepartmentEntity(Long id) {
		super();
		this.id = id;
	}
	
	
}
