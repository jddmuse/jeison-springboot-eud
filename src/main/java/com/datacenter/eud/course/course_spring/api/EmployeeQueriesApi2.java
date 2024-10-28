package com.datacenter.eud.course.course_spring.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.eud.course.course_spring.dto.DepartmentEmployeeDTO;
import com.datacenter.eud.course.course_spring.dto.EmployeeTipoDTO;
import com.datacenter.eud.course.course_spring.dto.JobDTO;
import com.datacenter.eud.course.course_spring.services.EmployeeQueriesService2;

@RestController
@RequestMapping("/api/employee/queries2")
public class EmployeeQueriesApi2 {

	@Autowired
	private EmployeeQueriesService2 service;

	@GetMapping("/firstandlastnameletter")
	public ResponseEntity<?> findByFirstNameLetterEqualsLastName(
			@RequestParam(required = false, defaultValue = "S") String letter) {
		List<EmployeeTipoDTO> result = service.findByFirstNameLetterEqualsLastName(letter);

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}

	@GetMapping("/salaryRange")
	public ResponseEntity<?> findBySalaryRange(
			@RequestParam(required = false, defaultValue = "5000.0") Double salaryStart,
			@RequestParam(required = false, defaultValue = "10000.0") Double salaryEnd) {
		List<EmployeeTipoDTO> result = service.findBySalaryRange(salaryStart, salaryEnd);

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}

	@GetMapping("/nameManager")
	public ResponseEntity<?> findByNameManager(@RequestParam(required = false, defaultValue = "Neena") String firstName,
			@RequestParam(required = false, defaultValue = "Kochhar") String lastName) {

		List<EmployeeTipoDTO> result = service.findByNameManager(firstName, lastName);

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}

	@GetMapping("/firstQuartil")
	public ResponseEntity<?> findByNameManager(@RequestParam(required = false, defaultValue = "2006") int year) {

		List<EmployeeTipoDTO> result = service.findEmployeesByHireDateInFirstQuartile(year);

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}

	@GetMapping("/departmentName")
	public ResponseEntity<?> findByDepartmentName(
			@RequestParam(required = false, defaultValue = "Sales") String department) {

		List<EmployeeTipoDTO> result = service.findByDepartmentName(department);
		System.out.println(result.size());

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}

	@GetMapping("/jobPercent")
	public ResponseEntity<?> findByJobAndPercent(
			@RequestParam(required = false, defaultValue = "Sales Representative") String jobName,
			@RequestParam(required = false, defaultValue = "0.25") Double percent) {

		List<EmployeeTipoDTO> result = service.findByJobAndPercent(jobName, percent);

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}

	@GetMapping("/employeeBoss")
//	public ResponseEntity<?> findByEmployeeIsBoss(@RequestParam(required = false, defaultValue = "Sales") String department) {
	public ResponseEntity<?> findByEmployeeIsBoss() {
		List<EmployeeTipoDTO> result = service.findByEmployeeIsBoss();

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}

	@GetMapping("/employeesOlder")
	public ResponseEntity<?> findEmployeesOlder(@RequestParam(required = false, defaultValue = "100") int employeeId) {
		List<EmployeeTipoDTO> result = service.findEmployeesOlder(employeeId);

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/employeeMoreJob")
	public ResponseEntity<?> findByEmployeesMoreOneJob() {
		List<EmployeeTipoDTO> result = service.findByEmployeeIsBoss();

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/employeeNoChangeJobs")
	public ResponseEntity<?> findEmployeesNoChangeJobs() {
		List<EmployeeTipoDTO> result = service.findEmployeesNoChangeJobs();
		System.out.println(result.size());
		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/historyEmployeeJobs")
	public ResponseEntity<?> findByHistoryEmployeeJobs(@RequestParam(required = false, defaultValue = "101") int employeeId) {
		List<EmployeeTipoDTO> result = service.findByHistoryEmployeeJobs(employeeId);

		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/jobSalaryRange/{salary}")
	public ResponseEntity<?> findJobsBySalaryRange(@PathVariable Double salary) {
		List<JobDTO> result = service.findJobsBySalaryRange(salary);
		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/departmentsQtyEmployees/{qty}")
	public ResponseEntity<?> findDepartmentsQtyEmployees(@PathVariable int qty) {
		List<DepartmentEmployeeDTO> result = service.findDepartmentsQtyEmployees(qty);
		return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
	}
}
