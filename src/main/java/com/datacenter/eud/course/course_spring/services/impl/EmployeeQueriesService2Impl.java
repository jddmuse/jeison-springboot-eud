package com.datacenter.eud.course.course_spring.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.DepartmentEmployeeDTO;
import com.datacenter.eud.course.course_spring.dto.EmployeeTipoDTO;
import com.datacenter.eud.course.course_spring.dto.JobDTO;
import com.datacenter.eud.course.course_spring.persistence.repositories.EmployeeQueriesRepository2;
import com.datacenter.eud.course.course_spring.services.EmployeeQueriesService2;
import com.github.dozermapper.core.DozerBeanMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeQueriesService2Impl implements EmployeeQueriesService2 {

	@NonNull
	private EmployeeQueriesRepository2 repository;
	
	@NonNull
	private final DozerBeanMapper dbm;

	@Override
	public List<EmployeeTipoDTO> findByFirstNameLetterEqualsLastName(String letter) {
		List<Object[]> result = this.repository.findByFirstNameLetterEqualsLastNameLetter(letter.toLowerCase());

		return result.stream()
				.map(objects -> EmployeeTipoDTO.builder().firstName((String) objects[0]).lastName((String) objects[1])
						.hierDate((Date) objects[2]).departmentName((String) objects[3]).jobTitle((String) objects[4])
						.email((String) objects[5]).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findBySalaryRange(Double salaryStart, Double salaryEnd) {

		return this.repository.findBySalaryRange(salaryStart, salaryEnd).stream()
				.map(e -> EmployeeTipoDTO.builder().id(e.getId()).firstName(e.getFirstName()).lastName(e.getLastName())
						.salary(e.getSalary()).commissionPct(e.getCommissionPct()).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findByNameManager(String firstName, String lastName) {
		List<Object[]> result = this.repository.findByNameManager(firstName, lastName);

		return result.stream()
				.map(objects -> EmployeeTipoDTO.builder().firstName((String) objects[0]).lastName((String) objects[1])
						.hierDate((Date) objects[2]).departmentName((String) objects[3]).jobTitle((String) objects[4])
						.email((String) objects[5]).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findEmployeesByHireDateInFirstQuartile(int year) {

		return this.repository.findEmployeesByHireDateInFirstQuartile(year).stream()
				.map(e -> EmployeeTipoDTO.builder().id(e.getId()).firstName(e.getFirstName()).lastName(e.getLastName())
						.email(e.getEmail()).hierDate(e.getHireDate()).phoneNumber(e.getPhoneNumber()).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findByDepartmentName(String department) {

		return this.repository.findByDepartmentName(department).stream()
				.map(e -> EmployeeTipoDTO.builder().id(e.getId()).firstName(e.getFirstName()).lastName(e.getLastName())
						.email(e.getEmail()).hierDate(e.getHireDate()).phoneNumber(e.getPhoneNumber()).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findByJobAndPercent(String jobName, Double percent) {

		return this.repository.findByJobAndPercent(jobName, percent).stream()
				.map(e -> EmployeeTipoDTO.builder().id(e.getId()).firstName(e.getFirstName()).lastName(e.getLastName())
						.salary(e.getSalary()).commissionPct(e.getCommissionPct()).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findByEmployeeIsBoss() {
		List<Object[]> result = this.repository.findByEmployeeIsBoss();

		return result.stream()
				.map(objects -> EmployeeTipoDTO.builder().firstName((String) objects[0]).lastName((String) objects[1])
						.hierDate((Date) objects[2]).departmentName((String) objects[3]).jobTitle((String) objects[4])
						.email((String) objects[5]).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findEmployeesOlder(int employeeId) {

		return this.repository.findEmployeesOlder(employeeId).stream()
				.map(e -> EmployeeTipoDTO.builder().id(e.getId()).firstName(e.getFirstName()).lastName(e.getLastName())
						.email(e.getEmail()).hierDate(e.getHireDate()).phoneNumber(e.getPhoneNumber()).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findByEmployeesMoreOneJob() {
		List<Object[]> result = this.repository.findByEmployeesMoreOneJob();
		
		return result.stream()
				.map(objects -> EmployeeTipoDTO.builder().firstName((String) objects[0]).lastName((String) objects[1])
						.hierDate((Date) objects[2]).departmentName((String) objects[3]).jobTitle((String) objects[4])
						.email((String) objects[5]).build())
				.toList();
	}

	@Override
	public List<EmployeeTipoDTO> findEmployeesNoChangeJobs() {
		
		return this.repository.findEmployeesNoChangeJobs().stream()
		.map(e -> EmployeeTipoDTO.builder().id(e.getId()).firstName(e.getFirstName()).lastName(e.getLastName())
				.salary(e.getSalary()).commissionPct(e.getCommissionPct()).build())
		.toList();

	}

	@Override
	public List<EmployeeTipoDTO> findByHistoryEmployeeJobs(int employeeId) {
		List<Object[]> result = this.repository.findByHistoryEmployeeJobs(employeeId);

		return result.stream()
				.map(objects -> EmployeeTipoDTO.builder().firstName((String) objects[0]).lastName((String) objects[1])
						.hierDate((Date) objects[2]).departmentName((String) objects[3]).jobTitle((String) objects[4])
						.email((String) objects[5]).build())
				.toList();
	}

	@Override
	public List<JobDTO> findJobsBySalaryRange(Double salary) {

		return this.repository.findJobsBySalaryRange(salary).stream()
				.map(job -> this.dbm.map(job, JobDTO.class)).toList();
	}

	@Override
	public List<DepartmentEmployeeDTO> findDepartmentsQtyEmployees(int qty) {
		List<Object[]> result = this.repository.findDepartmentsQtyEmployees(qty);
		
		return result.stream()
				.map(d -> DepartmentEmployeeDTO.builder()
						.departmentId((Integer) d[0])
						.departmentName((String) d[1])
						.bossName((String) d[2])
						.qtyEmployees((BigDecimal) d[3]).build()).toList();

	}

}
