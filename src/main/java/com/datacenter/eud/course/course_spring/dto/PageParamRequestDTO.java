package com.datacenter.eud.course.course_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageParamRequestDTO {
	int size;
	int pageSize;
}
