package com.datacenter.eud.course.course_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
	private String id;
	private String name;
	private Long regionId;
}
