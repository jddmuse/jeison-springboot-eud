package com.datacenter.eud.course.course_spring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> {
	int totalPages;
	int currentPage;
	long totalRows;
	int currentRows;
	List<T> data;
}
