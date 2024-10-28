package com.datacenter.eud.course.course_spring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.eud.course.course_spring.dto.LocationDTO;
import com.datacenter.eud.course.course_spring.services.LocationService;
import com.datacenter.eud.course.course_spring.util.exception.DatoNoExisteException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/location")
@Slf4j
public class LocationApi {
	
	@Autowired
	private LocationService service;
	
	/*
	@PostMapping
	public ResponseEntity<Long> create(@RequestBody @Valid LocationDTO dto){
		
		Long id = this.service.create(dto);
		return ResponseEntity.ok(id);
	}*/
	
	@PostMapping
	public ResponseEntity<Long> create(@RequestBody @Valid LocationDTO dto){
		try {
			Long id = this.service.create(dto);
			
			return ResponseEntity.ok(id);
		}catch (DatoNoExisteException e) {
			log.error(e.getMessage());
			
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}
}
