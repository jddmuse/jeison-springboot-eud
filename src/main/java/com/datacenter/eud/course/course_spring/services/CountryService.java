package com.datacenter.eud.course.course_spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.CountryDTO;

@Service
public interface CountryService {
	public List<CountryDTO> getAll();

	public CountryDTO getById(String id);

	public void create(CountryDTO dto);

	public void update(CountryDTO dto);

	public void delete(String id);
}
