package com.datacenter.eud.course.course_spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.DepartmentEmployeeDTO;
import com.datacenter.eud.course.course_spring.dto.EmployeeTipoDTO;
import com.datacenter.eud.course.course_spring.dto.JobDTO;

@Service
public interface EmployeeQueriesService2 {
	public List<EmployeeTipoDTO> findByFirstNameLetterEqualsLastName(String letter);
	public List<EmployeeTipoDTO> findBySalaryRange(Double salaryStart, Double salaryEnd);
	public List<EmployeeTipoDTO> findByNameManager(String firstName, String lastName);
	public List<EmployeeTipoDTO> findEmployeesByHireDateInFirstQuartile(int year);
	public List<EmployeeTipoDTO> findByDepartmentName(String department);
	public List<EmployeeTipoDTO> findByJobAndPercent(String jobName, Double percent);
	public List<EmployeeTipoDTO> findByEmployeeIsBoss(); //(String department);
	public List<EmployeeTipoDTO> findEmployeesOlder(int employeeId);
	public List<EmployeeTipoDTO> findByEmployeesMoreOneJob();
	public List<EmployeeTipoDTO> findEmployeesNoChangeJobs();
	public List<EmployeeTipoDTO> findByHistoryEmployeeJobs(int employeeId);
	
	public List<JobDTO> findJobsBySalaryRange(Double salary);
	public List<DepartmentEmployeeDTO> findDepartmentsQtyEmployees(int qty);
}
