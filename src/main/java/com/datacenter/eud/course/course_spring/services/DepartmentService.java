package com.datacenter.eud.course.course_spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.DepartmentDTO;

@Service
public interface DepartmentService {
	public List<DepartmentDTO> getAll();

	public DepartmentDTO getById(Long id);

	public void create(DepartmentDTO dto);

	public void update(DepartmentDTO dto);

	public void delete(Long id);
}
