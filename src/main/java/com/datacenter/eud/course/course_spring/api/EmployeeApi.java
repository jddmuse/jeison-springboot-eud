package com.datacenter.eud.course.course_spring.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.eud.course.course_spring.dto.PageParamRequestDTO;
import com.datacenter.eud.course.course_spring.services.EmployeeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeApi {

	@NonNull
	private EmployeeService service;

	@GetMapping()
	public ResponseEntity<?> getAll(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int pageSize) {

		PageParamRequestDTO pageParam = new PageParamRequestDTO(page, pageSize);

		return ResponseEntity.ok(this.service.getAll(pageParam));
	}

	@PostMapping
	public void execute() {
		this.service.execute();
	}
	
	@PostMapping("/query")
	public void executeQuery() {
		this.service.executeQuery();
	}
}
