package com.datacenter.eud.course.course_spring.services.impl;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.CountryDTO;
import com.datacenter.eud.course.course_spring.persistence.entities.CountryEntity;
import com.datacenter.eud.course.course_spring.persistence.repositories.CountryRepository;
import com.datacenter.eud.course.course_spring.services.CountryService;
import com.datacenter.eud.course.course_spring.util.exception.DatoNoExisteException;
import com.datacenter.eud.course.course_spring.util.exception.DatoYaExisteException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

	@NonNull
	private CountryRepository repository;

	@Override
	public List<CountryDTO> getAll() {
		return StreamSupport.stream(this.repository.findAll().spliterator(), false)
				.map(r -> CountryDTO.builder().id(r.getId()).name(r.getName()).regionId(r.getRegionId()).build())
				.toList();
	}

	@Override
	public CountryDTO getById(String id) {
		return this.repository.findById(id)
				.map(c -> CountryDTO.builder().id(c.getId()).name(c.getName()).regionId(c.getRegionId()).build())
				.orElse(null);
	}

	@Override
	public void create(CountryDTO dto) {
		if (!this.repository.existsById(dto.getId())) {
			CountryEntity entity = CountryEntity.builder().id(dto.getId()).name(dto.getName())
					.regionId(dto.getRegionId()).build();

			this.repository.save(entity);
		} else {
			throw new DatoYaExisteException("El ID " + dto.getId() + " ya existe en DB");
		}
	}

	@Override
	public void update(CountryDTO dto) {
		if (this.repository.existsById(dto.getId())) {
			CountryEntity entity = CountryEntity.builder().id(dto.getId()).name(dto.getName())
					.regionId(dto.getRegionId()).build();

			this.repository.save(entity);
		} else {
			throw new DatoNoExisteException("El ID " + dto.getId() + " no existe en DB");
		}

	}

	@Override
	public void delete(String id) {
		if (this.repository.existsById(id)) {
			this.repository.deleteById(id);
		} else {
			throw new DatoNoExisteException("El ID " + id + " NO existe en DB");
		}
	}
	
}
