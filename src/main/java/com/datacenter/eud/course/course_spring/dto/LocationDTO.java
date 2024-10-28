package com.datacenter.eud.course.course_spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDTO {
	private Long id;
	private String streetAddress;
	private String postalCode;	
	private String city;
	private String stateProvince;
	private String countryId;
	private String countryName;
}
