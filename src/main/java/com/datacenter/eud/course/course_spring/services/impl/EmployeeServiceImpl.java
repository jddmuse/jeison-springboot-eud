package com.datacenter.eud.course.course_spring.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.EmployeeDTO;
import com.datacenter.eud.course.course_spring.dto.PageParamRequestDTO;
import com.datacenter.eud.course.course_spring.dto.PageResponseDTO;
import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;
import com.datacenter.eud.course.course_spring.persistence.repositories.EmployeeRepository;
import com.datacenter.eud.course.course_spring.persistence.repositories.EmployeeRepositoryPagination;
import com.datacenter.eud.course.course_spring.services.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	@NonNull
	private EmployeeRepositoryPagination repositoryPagination;
	
	@NonNull
	private EmployeeRepository repository;

	@Override
	public void execute() {
//		Pageable pageable = PageRequest.of(0, 20);

		Pageable pageable = PageRequest.of(0, 20, Sort.by(Order.asc("lastName")));
		Page<EmployeeEntity> page = this.repositoryPagination.findAll(pageable);

		System.out.println("Total elementos " + page.getTotalElements());
		System.out.println("Size " + page.getSize());
		System.out.println("Número total de paginas " + page.getTotalPages());

		page.get().forEach(e -> {
			System.out.println(e.getFirstName() + " " + e.getLastName());
		});

		/*
		 * Iterable<EmployeeEntity> list =
		 * this.repository.findAll(Sort.by(Order.asc("firstName")));
		 * Iterable<EmployeeEntity> list =
		 * this.repository.findAll(Sort.by(Order.asc("firstName"),
		 * Order.desc("lastName"))); Iterable<EmployeeEntity> list =
		 * this.repository.findAll(Sort.by("firstName asc", "lastName desc"));
		 * Iterable<EmployeeEntity> list =
		 * this.repository.findAll(Sort.by(Direction.ASC, "firstName"));
		 * 
		 * 
		 * for(EmployeeEntity e : list) { System.out.println(e.getFirstName() + " " +
		 * e.getLastName()); }
		 */

	}

	@Override
	public void executeQuery() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//this.repository.getByDepartmentId(20L).stream()
		//this.repository.findByJobId("ST_MAN").stream()
		//this.repository.findBySalaryGreaterThan(8000.0).stream()
		//this.repository.findBySalaryLessThan(8000.0).stream()
		//this.repository.findByJobIdNot("ST_MAN").stream()
		//this.repository.findByFirstNameContaining("ee").stream()
		//this.repository.findByFirstNameNotContaining("ee").stream()
		//this.repository.findByDepartmentIdIn(Arrays.asList(10L, 20L)).stream()
		//this.repository.findByDepartmentIdNotIn(Arrays.asList(10L, 20L)).stream()
		//this.repository.findBySalaryBetween(6000.0, 8000.0).stream()
		//this.repository.findByCommissionPctIsNotNull().stream()
		//this.repository.findByCommissionPctIsNull().stream()
		//this.repository.findByFirstNameStartingWith("E").stream()
		//this.repository.findByFirstNameEndingWith("s").stream()
		//this.repository.findByDepartmentIdAndJobId(10L, "AD_ASST").stream()
		//this.repository.findByDepartmentIdOrJobId(20L, "AD_ASST").stream()
		//this.repository.findByDepartmentIdAndJobIdAndSalaryGreaterThan(10L, "AD_ASST", 10000.0).stream()
		//this.repository.findByFirstNameLike("A_a%").stream()
		//this.repository.findByOrderByFirstNameAscLastNameDesc().stream()
		//this.repository.findByDepartmentIdOrderByFirstNameAsc(50L).stream()
		/*this.repository.findByDepartmentIdAndJobIdNotOrderByFirstNameAscLastNameAscSalaryDesc(50L, "SH_CLERK").stream()
			.forEach(e -> {
				System.out.println(e);
			});*/
		
		//System.out.println("Existe: " + this.repository.existsByDepartmentId(500L));
		//System.out.println("Conteo: " + this.repository.countBySalaryGreaterThan(10000.0));	
		
		try {
//			//this.repository.findByHireDateLessThanEqual(sdf.parse("31/12/2007")).stream()
//			//this.repository.findByHireDateGreaterThan(sdf.parse("31/12/2007")).stream()
//			//this.repository.findByHireDateBetween(sdf.parse("01/01/2007"), sdf.parse("31/12/2007")).stream()
//			//this.repository.findByHireDateAfterOrSalaryLessThanAndCommissionPctIsNotNull(sdf.parse("01/01/2007"), 12000.0).stream()
			this.repository.findByFirstNameStartingWithAndLastNameEndingWithAndHireDateBetween("A", "s", sdf.parse("01/01/2007"), sdf.parse("01/01/2008"))
				.forEach(e -> {
					System.out.println(e);
				});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Pageable pageable = PageRequest.of(0, 10);
		//Pageable pageable = PageRequest.of(0, 20, Sort.by(Order.asc("salary")));
		
		/*this.repository2.findByDepartmentId(50L, pageable).stream()
		.forEach(e -> System.out.println(e));*/
		
		//this.repository2.findByFirstNameContaining("e", pageable).stream()
		//this.repository2.findByDepartmentIdAndJobIdAndSalaryGreaterThan(50L, "SH_CLERK", 2000.0, pageable).stream()
		
		//this.repository2.findByJobId("SH_CLERK", Sort.unsorted()).stream()
		//this.repository2.findByJobId("SH_CLERK", Sort.by(Order.asc("firstName"))).stream()
		//this.repository2.findByJobId("SH_CLERK", Sort.by(Order.asc("firstName"), Order.desc("lastName"))).stream()
		
		/*this.repository2.findByDepartmentIdAndJobId(50L, "SH_CLERK", pageable).stream()
		.forEach(System.out::println);*/
		
		//this.repository.deleteByDepartmentId(500L);
		//this.repository.removeByDepartmentIdAndJobId(500L, "SH_CLERK");
		
		//this.repository3.consultarPorDepartamento(50L).stream()
		//this.repository3.consultarPorDepartamentoYCargo(50L, "SH_CLERK").stream()
//		this.repository3.consultarPorSalarioONoTieneComision(5000.0).stream()
//			.forEach(System.out::println);

	}

	@Override
	public PageResponseDTO<EmployeeDTO> getAll(PageParamRequestDTO page) {
		
		Page<EmployeeEntity> entities = this.repositoryPagination.findAll(
			PageRequest.of(page.getSize(), page.getPageSize(), Sort.by(Order.asc("firstName"))));
		
//		PageResponseDTO<EmployeeDTO> response = new PageResponseDTO<EmployeeDTO>();
//		
//		response.setCurrentPage(page.getSize());
//		response.setCurrentRows(entities.getNumberOfElements());
//		response.setTotalPages(entities.getTotalPages());
//		response.setTotalRows(entities.getTotalElements());
//		response.setData(entities.getContent().stream()
//				.map(e -> castToEmployeeDTO(e)).toList());
//		
//		return response;
		
		PageResponseDTO<EmployeeDTO> response = PageResponseDTO.<EmployeeDTO>builder()
				.currentPage(page.getSize())
				.currentRows(entities.getNumberOfElements())
				.totalPages(entities.getTotalPages())
				.totalRows(entities.getTotalElements())
				.data(entities.getContent().stream()
				.map(e -> castToEmployeeDTO(e)).toList())
				.build();
		
		return response;

	}

	private EmployeeDTO castToEmployeeDTO(EmployeeEntity e) {
		EmployeeDTO dto = EmployeeDTO.builder().departmentId(e.getDepartmentId())
				.salary(e.getSalary()).jobId(e.getJobId()).firstName(e.getFirstName())
				.lastName(e.getLastName()).build();
		
		return dto;
	}

}
