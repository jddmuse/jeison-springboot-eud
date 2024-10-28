package com.datacenter.eud.course.course_spring.persistence.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
	List<EmployeeEntity> findByDepartmentId(Long departmentId);
	List<EmployeeEntity> getByDepartmentId(Long departmentId);
	
	List<EmployeeEntity> findByJobId(String jobId);
	List<EmployeeEntity> findByJobIdNot(String jobId);
	
	List<EmployeeEntity> findBySalaryGreaterThan(Double salary);
	List<EmployeeEntity> findBySalaryGreaterThanEqual(Double salary);
	List<EmployeeEntity> findBySalaryLessThan(Double salary);
	List<EmployeeEntity> findBySalaryLessThanEqual(Double salary);
	List<EmployeeEntity> findBySalaryBetween(Double salaryInitial, Double salaryFinal);
	
	List<EmployeeEntity> findByHireDateLessThanEqual(Date hireDate);
	List<EmployeeEntity> findByHireDateGreaterThan(Date hireDate);
	List<EmployeeEntity> findByHireDateAfter(Date hireDate);
	List<EmployeeEntity> findByHireDateBefore(Date hireDate);
	List<EmployeeEntity> findByHireDateBetween(Date hireDateStart, Date hireDateEnd);
	
	List<EmployeeEntity> findByFirstNameContaining(String word); //%ee%
	List<EmployeeEntity> findByFirstNameNotContaining(String word);
	List<EmployeeEntity> findByFirstNameStartingWith(String word); //E%
	List<EmployeeEntity> findByFirstNameEndingWith(String word); //%s
	
	List<EmployeeEntity> findByFirstNameLike(String like);
	List<EmployeeEntity> findByFirstNameNotLike(String like);
	
	List<EmployeeEntity> findByDepartmentIdIn(List<Long> departmentIds);
	List<EmployeeEntity> findByDepartmentIdNotIn(List<Long> departmentIds);
	
	List<EmployeeEntity> findByCommissionPctIsNull();
	List<EmployeeEntity> findByCommissionPctIsNotNull();
	
	List<EmployeeEntity> findByDepartmentIdAndJobId(Long departmentId, String jobId);
	List<EmployeeEntity> findByDepartmentIdOrJobId(Long departmentId, String jobId);
	
	List<EmployeeEntity> findByDepartmentIdAndJobIdAndSalaryGreaterThan(Long departmentId, String jobId, Double salary);
	List<EmployeeEntity> findByHireDateAfterOrSalaryLessThanAndCommissionPctIsNotNull(Date hireDate, Double salary);
	List<EmployeeEntity> findByFirstNameStartingWithAndLastNameEndingWithAndHireDateBetween(String fn, String ln, Date initialDate, Date finalDate);
	
	boolean existsByDepartmentId(Long departmentId);
	boolean existsByFirstNameStartingWithAndSalaryLessThan(String fn, Double salary);
	boolean existsByHireDateBetweenOrJobIdIn(Date startDate, Date endDate, List<String> jobsId);
	
	long countBySalaryGreaterThan(Double salary);
	long countByCommissionPctIsNullAndLastNameContaining(String word);
	long countByDepartmentIdNotOrJobIdAndSalaryIsNotNull(Long departmentId, String jobId);
	
	List<EmployeeEntity> findByOrderByFirstName();
	List<EmployeeEntity> findByOrderByFirstNameAsc();
	List<EmployeeEntity> findByOrderByFirstNameDesc();
	List<EmployeeEntity> findByOrderByFirstNameAscLastNameDesc();
	
	List<EmployeeEntity> findByDepartmentIdOrderByFirstNameAsc(Long departmentId);
	List<EmployeeEntity> findByDepartmentIdAndJobIdNotOrderByFirstNameAscLastNameAscSalaryDesc(Long departmentId, String jobId);
	
	void deleteByDepartmentId(Long departmentId);
	void removeByDepartmentIdAndJobId(Long departmentId, String jobId);
}
