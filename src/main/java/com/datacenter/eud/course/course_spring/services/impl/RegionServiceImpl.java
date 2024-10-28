package com.datacenter.eud.course.course_spring.services.impl;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.RegionDTO;
import com.datacenter.eud.course.course_spring.persistence.entities.RegionEntity;
import com.datacenter.eud.course.course_spring.persistence.repositories.RegionRepository;
import com.datacenter.eud.course.course_spring.services.RegionService;
import com.datacenter.eud.course.course_spring.util.exception.DatoNoExisteException;
import com.datacenter.eud.course.course_spring.util.exception.DatoYaExisteException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

	@NonNull
	private final RegionRepository repository;

	@Override
	public void execute() {
		this.repository.findAll().forEach(reg -> {
			System.out.println("region -> " + reg.getId() + " " + reg.getName());
		});
		// Count
		System.out.println("Cantidad de registros Regions ==> " + this.repository.count());
	}

	@Override
	public List<RegionDTO> getAll() {
		return StreamSupport.stream(this.repository.findAll().spliterator(), false)
				.map(r -> RegionDTO.builder().id(r.getId()).name(r.getName()).build()).toList();
	}

	@Override
	public RegionDTO getById(Long id) {
		return this.repository.findById(id)
				.map(r -> RegionDTO.builder().id(r.getId()).name(r.getName()).build())
				.orElse(null);	
	}

	@Override
	public void create(RegionDTO dto) {
		
		if(!this.repository.existsById(dto.getId())) {
			RegionEntity entity = RegionEntity.builder().id(dto.getId()).name(dto.getName()).build();
			this.repository.save(entity);
		}else {
			throw new DatoYaExisteException("El ID " + dto.getId() + " ya existe en DB");			
		}

	}

	@Override
	public void update(RegionDTO dto) {
		
		if(this.repository.existsById(dto.getId())) {
			RegionEntity entity = RegionEntity.builder().id(dto.getId()).name(dto.getName()).build();
			this.repository.save(entity);
		}else {
			throw new DatoNoExisteException("El ID " + dto.getId() + " ya existe en DB");			
		}

	}

	@Override
	public void delete(Long id) {
		
		if(this.repository.existsById(id)) {						
			this.repository.deleteById(id);
		}else {
			throw new DatoNoExisteException("El ID " + id + " NO existe en DB");
		}
	}

}
