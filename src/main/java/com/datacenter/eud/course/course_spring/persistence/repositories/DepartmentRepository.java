package com.datacenter.eud.course.course_spring.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.eud.course.course_spring.persistence.entities.DepartmentEntity;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {

}
