package com.datacenter.eud.course.course_spring.api;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.eud.course.course_spring.dto.DepartmentDTO;
import com.datacenter.eud.course.course_spring.services.DepartmentService;
import com.datacenter.eud.course.course_spring.util.exception.DatoNoExisteException;
import com.datacenter.eud.course.course_spring.util.exception.DatoYaExisteException;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentApi {

	@NonNull
	private DepartmentService service;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {

		return Optional.ofNullable(this.service.getById(id)).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody @Valid DepartmentDTO dto) {
		try {
			Assert.notNull(dto.getId(), "ID es nulo");
			this.service.create(dto);
			return ResponseEntity.ok().build();
		} catch (DatoYaExisteException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DepartmentDTO dto) {
		try {
			dto.setId(id);
			this.service.update(dto);

			return ResponseEntity.ok().build();
		} catch (DatoNoExisteException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			this.service.delete(id);

			return ResponseEntity.ok().build();
		} catch (DatoNoExisteException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
