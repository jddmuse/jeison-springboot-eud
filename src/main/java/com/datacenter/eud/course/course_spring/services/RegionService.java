package com.datacenter.eud.course.course_spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.RegionDTO;

@Service
public interface RegionService {
    public void execute();

	public List<RegionDTO> getAll();
	
	public RegionDTO getById(Long id);
	
	public void create(RegionDTO dto);
	
	public void update(RegionDTO dto);
	
	public void delete(Long id);

}
