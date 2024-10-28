package com.datacenter.eud.course.course_spring.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.eud.course.course_spring.dto.PageParamRequestDTO;
import com.datacenter.eud.course.course_spring.services.impl.EmployeeQueriesServiceImpl;

@RestController
@RequestMapping("/api/employee/queries")
public class EmployeeQueriesApi {

	@Autowired
	private EmployeeQueriesServiceImpl service;

	@GetMapping
	public ResponseEntity<?> findByDepartmentIdIn(@RequestParam List<Long> departmentIds) {
		return ResponseEntity.ok(service.findByDepartmentIdIn(departmentIds));
	}

	@GetMapping("/letters")
	public ResponseEntity<?> findByFirstNameContainThreeLetters(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int pageSize) {

		PageParamRequestDTO pageParam = new PageParamRequestDTO(page, pageSize);
		return ResponseEntity.ok(service.findByNameLength(pageParam));
	}

	@GetMapping("/hiredate")
	public ResponseEntity<?> findByHireDateYear(@RequestParam(required = false, defaultValue = "2008") int year) {

		return ResponseEntity.ok(service.findByHireDateYear(year));
	}

	@GetMapping("/department/job")
	public ResponseEntity<?> findByDepartmentAndJob(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int pageSize, @RequestParam Long departmentId,
			String jobId) {

		PageParamRequestDTO pageParam = new PageParamRequestDTO(page, pageSize);
		return ResponseEntity.ok(service.findByDepartmentAndJob(departmentId, jobId, pageParam));
	}

	@GetMapping("/likeletters")
	public ResponseEntity<?> findByFirstLetterEqualsLastLetter(
			@RequestParam(required = false, defaultValue = "S") String letter) {

		return ResponseEntity.ok(service.findByFirstLetterEqualsLastLetter(letter.toLowerCase()));
	}

	@GetMapping("/findmanager")
	public ResponseEntity<?> findByManagerFirstAndLastName(
			@RequestParam(required = false, defaultValue = "Harrison") String firstName,
			@RequestParam(required = false, defaultValue = "Bloom") String lastName,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {

		PageParamRequestDTO pageParam = new PageParamRequestDTO(page, pageSize);
		return ResponseEntity.ok(service.findByManagerFirstAndLastName(firstName, lastName, pageParam));
	}

	@GetMapping("/findsameletter")
	public ResponseEntity<?> findByFirstNameLike(
			@RequestParam(required = false, defaultValue = "Harrison") String firstName) {
		
		return ResponseEntity.ok(service.findByFirstNameLike(firstName));

	}
	
	@GetMapping("/phonenumber")
	public ResponseEntity<?> findByPhoneNumberPrefix(
			@RequestParam(required = false, defaultValue = "515") String prefix,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		
		PageParamRequestDTO pageParam = new PageParamRequestDTO(page, pageSize);
		
		return ResponseEntity.ok(service.findByPhoneNumberPrefix(prefix, pageParam));
	}
	
	// ===============================================================================================
	@GetMapping("/2/firstnameandlastname")
	public ResponseEntity<?> findByFirstNameLetterEqualsLastName(
//			@RequestParam(required = false, defaultValue = "S") String firstName,
			@RequestParam(required = false, defaultValue = "S") String lastName
			) {
		
		return ResponseEntity.ok(service.findByFirstNameLetterEqualsLastName(lastName));
	}
}
