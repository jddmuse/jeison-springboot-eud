package com.datacenter.eud.course.course_spring.api;

import java.util.List;

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

import com.datacenter.eud.course.course_spring.dto.RegionDTO;
import com.datacenter.eud.course.course_spring.services.RegionService;
import com.datacenter.eud.course.course_spring.util.exception.DatoNoExisteException;
import com.datacenter.eud.course.course_spring.util.exception.DatoYaExisteException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/region")
public class RegionApi {

	private final RegionService service;

	// @Autowired
	public RegionApi(RegionService service) {
		this.service = service;
	}

//    @GetMapping
//    public void getAll1() {
//        service.execute();
//    }

	@GetMapping
	public List<RegionDTO> getAll() {
		return this.service.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<RegionDTO> getById(@PathVariable Long id) {
		RegionDTO dto = this.service.getById(id);

		if (dto != null) {
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody @Valid RegionDTO dto) {
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
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid RegionDTO dto) {
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
		}catch (DatoNoExisteException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}		
	}

}
