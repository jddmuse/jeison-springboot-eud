package com.datacenter.eud.course.course_spring.services;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.LocationDTO;

@Service
public interface LocationService {
	
	public Long create(LocationDTO dto);
}
