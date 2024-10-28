package com.datacenter.eud.course.course_spring.services;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.EmployeeDTO;
import com.datacenter.eud.course.course_spring.dto.PageParamRequestDTO;
import com.datacenter.eud.course.course_spring.dto.PageResponseDTO;

@Service
public interface EmployeeService {
	
	public void execute();
	public void executeQuery();
	public PageResponseDTO<EmployeeDTO> getAll(PageParamRequestDTO page);
}
