package com.datacenter.eud.course.course_spring.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.datacenter.eud.course.course_spring.persistence.entities.CountryEntity;

public interface CountryRepository extends CrudRepository<CountryEntity, String> {

}
