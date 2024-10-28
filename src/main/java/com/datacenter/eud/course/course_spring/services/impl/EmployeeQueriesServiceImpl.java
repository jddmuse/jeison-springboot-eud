package com.datacenter.eud.course.course_spring.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.CountryDTO;
import com.datacenter.eud.course.course_spring.dto.EmployeeDTO;
import com.datacenter.eud.course.course_spring.dto.PageParamRequestDTO;
import com.datacenter.eud.course.course_spring.dto.PageResponseDTO;
import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;
import com.datacenter.eud.course.course_spring.persistence.repositories.EmployeeQueriesReository;
import com.datacenter.eud.course.course_spring.services.EmployeeQueriesService;
import com.datacenter.eud.course.course_spring.utils.UtilStrings;

@Service
public class EmployeeQueriesServiceImpl implements EmployeeQueriesService {

	@Autowired
	private EmployeeQueriesReository repository;

	private EmployeeDTO builderEmployeeDto(EmployeeEntity entity) {
		return EmployeeDTO.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName())
				.email(entity.getEmail()).phoneNumber(entity.getPhoneNumber()).hireDate(entity.getHireDate())
				.jobId(entity.getJobId()).salary(entity.getSalary()).commissionPct(entity.getCommissionPct())
				.managerId(entity.getManagerId()).departmentId(entity.getDepartmentId()).build();
	}

	@Override
	public List<EmployeeDTO> findByDepartmentIdIn(List<Long> departmentIds) {
		if (departmentIds == null || departmentIds.isEmpty()) {
			return Collections.emptyList();
		}
		return repository.findByDepartmentIdIn(departmentIds).stream().map(this::builderEmployeeDto)
				.collect(Collectors.toList());

	}

	@Override
	public PageResponseDTO<EmployeeDTO> findByNameLength(PageParamRequestDTO page) {

		Page<EmployeeEntity> entities = repository.findByFirstNameLikeOrderByFirstNameAscLastNameAsc("___",
				PageRequest.of(page.getSize(), page.getPageSize()));

		PageResponseDTO<EmployeeDTO> response = PageResponseDTO.<EmployeeDTO>builder().currentPage(page.getSize())
				.currentRows(entities.getNumberOfElements()).totalPages(entities.getTotalPages())
				.totalRows(entities.getTotalElements())
				.data(entities.getContent().stream().map(e -> builderEmployeeDto(e)).toList()).build();

		return response;
	}

	@Override
	public List<EmployeeDTO> findByHireDateYear(int year) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = sdf.parse("01/01/" + year);
			endDate = sdf.parse("31/12/" + year);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return repository.findByHireDateBetweenOrderByHireDateDesc(startDate, endDate).stream()
				.map(e -> builderEmployeeDto(e)).toList();
	}

	@Override
	public PageResponseDTO<EmployeeDTO> findByDepartmentAndJob(Long departmentId, String jobId,
			PageParamRequestDTO page) {

		Page<EmployeeEntity> entities = repository.findByDepartmentIdAndJobIdOrderBySalaryAsc(departmentId, jobId,
				PageRequest.of(page.getSize(), page.getPageSize()));

		return PageResponseDTO.<EmployeeDTO>builder().currentPage(page.getSize())
				.currentRows(entities.getNumberOfElements()).totalPages(entities.getTotalPages())
				.totalRows(entities.getTotalElements())
				.data(entities.getContent().stream().map(e -> builderEmployeeDto(e)).toList()).build();

	}

	@Override
	public List<EmployeeDTO> findByFirstLetterEqualsLastLetter(String letter) {

		if (!letter.isEmpty()) {
			String upperLetter = letter.toUpperCase();
			return repository.findByFirstNameStartingWithAndLastNameEndingWithOrderByHireDateDesc(upperLetter, letter)
					.stream().map(this::builderEmployeeDto).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@SuppressWarnings("null")
	@Override
	public PageResponseDTO<EmployeeDTO> findByManagerFirstAndLastName(String firstName, String lastName,
			PageParamRequestDTO page) {

		List<EmployeeEntity> managerList = repository.findByFirstNameAndLastName(UtilStrings.capitalize(firstName),
				UtilStrings.capitalize(lastName));

		Long managerId = 0L;

		if (managerList != null || !managerList.isEmpty()) {
			managerId = managerList.get(0).getId();
		}

		Page<EmployeeEntity> entities = repository.findByManagerIdOrderByFirstNameAscLastNameAsc(managerId,
				PageRequest.of(page.getSize(), page.getPageSize()));

		return PageResponseDTO.<EmployeeDTO>builder().currentPage(page.getSize())
				.currentRows(entities.getNumberOfElements()).totalPages(entities.getTotalPages())
				.totalRows(entities.getTotalElements())
				.data(entities.getContent().stream().map(e -> builderEmployeeDto(e)).toList()).build();

	}

	// 9
	@Override
	public List<EmployeeDTO> findByFirstNameLike(String firstName) {

		if (!firstName.isEmpty()) {
			Character characterRepeated = UtilStrings.repeatedLetter(firstName);

			if (characterRepeated == null)
				return Collections.emptyList();

			String pattern = "%" + characterRepeated + characterRepeated + "%";

			return repository.findByFirstNameLikeOrderByDepartmentIdAsc(pattern).stream().map(this::builderEmployeeDto)
					.toList();

		}

		return Collections.emptyList();
	}

	@Override
	public PageResponseDTO<EmployeeDTO> findByPhoneNumberPrefix(String prefix, PageParamRequestDTO page) {

		Page<EmployeeEntity> entities = repository
				.findByPhoneNumberStartingWithOrderByFirstNameAscLastNameAscDepartmentIdDesc(prefix,
						PageRequest.of(page.getSize(), page.getPageSize()));

		return PageResponseDTO.<EmployeeDTO>builder().currentPage(page.getSize())
				.currentRows(entities.getNumberOfElements()).totalPages(entities.getTotalPages())
				.totalRows(entities.getTotalElements())
				.data(entities.getContent().stream().map(e -> builderEmployeeDto(e)).toList()).build();
	}

	@Override
	public List<EmployeeDTO> findByFirstNameLetterEqualsLastName(String letter) {
		
		List<Object[]> result = this.repository.findByFirstNameLetterEqualsLastNameLetter(letter.toLowerCase());
		System.out.println(result.size());
		
		return null;
	}

}
