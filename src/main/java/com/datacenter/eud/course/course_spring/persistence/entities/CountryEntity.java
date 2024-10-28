package com.datacenter.eud.course.course_spring.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COUNTRIES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {
	@Id
	@Column(name = "COUNTRY_ID")
	private String id;
	@Column(name = "COUNTRY_NAME")
	private String name;
	@Column(name = "region_id")
	private Long regionId;
}
