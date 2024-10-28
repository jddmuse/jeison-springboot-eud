package com.datacenter.eud.course.course_spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.EmployeeDTO;
import com.datacenter.eud.course.course_spring.dto.PageParamRequestDTO;
import com.datacenter.eud.course.course_spring.dto.PageResponseDTO;
import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;

@Service
public interface EmployeeQueriesService {
	
	public List<EmployeeDTO> findByDepartmentIdIn(List<Long> departmentIds);
	public PageResponseDTO<EmployeeDTO> findByNameLength(PageParamRequestDTO pageDTO);
	public List<EmployeeDTO> findByHireDateYear(int year);
	public PageResponseDTO<EmployeeDTO> findByDepartmentAndJob(Long departmentId, String jobId, PageParamRequestDTO pageDTO);
	public List<EmployeeDTO> findByFirstLetterEqualsLastLetter(String letter);
	PageResponseDTO<EmployeeDTO> findByManagerFirstAndLastName(String firstName, String lastName, PageParamRequestDTO pageDTO);
	public List<EmployeeDTO> findByFirstNameLike(String pattern);
	public PageResponseDTO<EmployeeDTO> findByPhoneNumberPrefix(String prefix, PageParamRequestDTO pageDTO);
	
	// =====================================================================================================
	public List<EmployeeDTO> findByFirstNameLetterEqualsLastName(String letter);
}
