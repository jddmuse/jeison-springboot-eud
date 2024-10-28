package com.datacenter.eud.course.course_spring.persistence.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;

@Repository
public interface EmployeeRepositoryPagination extends PagingAndSortingRepository<EmployeeEntity, Long> {

}
