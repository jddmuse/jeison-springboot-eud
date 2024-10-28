package com.datacenter.eud.course.course_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegionDTO {
	@NotNull(message = "ID es nulo")
	private Long id;

	@NotBlank(message = "name es nulo o vacio")
	@Size(max = 25, message = "name permite máximo 25 caracteres")
	private String name;
}
