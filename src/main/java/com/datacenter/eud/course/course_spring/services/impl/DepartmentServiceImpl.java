package com.datacenter.eud.course.course_spring.services.impl;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.DepartmentDTO;
import com.datacenter.eud.course.course_spring.persistence.entities.DepartmentEntity;
import com.datacenter.eud.course.course_spring.persistence.repositories.DepartmentRepository;
import com.datacenter.eud.course.course_spring.services.DepartmentService;
import com.datacenter.eud.course.course_spring.util.exception.DatoNoExisteException;
import com.datacenter.eud.course.course_spring.util.exception.DatoYaExisteException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	
	@NonNull
	private DepartmentRepository repository;

	@Override
	public List<DepartmentDTO> getAll() {

		return StreamSupport.stream(this.repository.findAll().spliterator(), false).map(d -> DepartmentDTO.builder()
				.id(d.getId()).name(d.getName()).managerId(d.getManagerId()).locationId(d.getLocationId()).build())
				.toList();
	}

	@Override
	public DepartmentDTO getById(Long id) {

		return this.repository.findById(id).map(d -> DepartmentDTO.builder().id(d.getId()).name(d.getName())
				.managerId(d.getManagerId()).locationId(d.getLocationId()).build()).orElse(null);
	}

	@Override
	public void create(DepartmentDTO dto) {
		if (!this.repository.existsById(dto.getId())) { // Se valida que el registro no exista en BD
			DepartmentEntity entity = DepartmentEntity.builder().id(dto.getId()).name(dto.getName())
					.managerId(dto.getManagerId()).locationId(dto.getLocationId()).build();

			this.repository.save(entity);
		} else {
			throw new DatoYaExisteException("El ID " + dto.getId() + " ya existe en DB");
		}
	}

	@Override
	public void update(DepartmentDTO dto) {
		if (this.repository.existsById(dto.getId())) { // Se valida que el registro no exista en BD
			DepartmentEntity entity = DepartmentEntity.builder().id(dto.getId()).name(dto.getName())
					.managerId(dto.getManagerId()).locationId(dto.getLocationId()).build();

			this.repository.save(entity);
		} else {
			throw new DatoNoExisteException("El ID " + dto.getId() + " no existe en DB");
		}
	}

	@Override
	public void delete(Long id) {
		if (this.repository.existsById(id)) {
			this.repository.deleteById(id);
		} else {
			throw new DatoNoExisteException("El ID " + id + " NO existe en DB");
		}
	}

}
