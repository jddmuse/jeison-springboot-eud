package com.datacenter.eud.course.course_spring.persistence.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;
import com.datacenter.eud.course.course_spring.utils.SQLConstants;

@Repository
public interface EmployeeQueriesReository
		extends CrudRepository<EmployeeEntity, Long>, PagingAndSortingRepository<EmployeeEntity, Long> {

	// Empleados que pertenecen al departamento 50 o 60
	List<EmployeeEntity> findByDepartmentIdIn(List<Long> departmentIds);

	// Empleados cuyo nombre tiene 3 letras
	Page<EmployeeEntity> findByFirstNameLikeOrderByFirstNameAscLastNameAsc(String like, Pageable pageable);

	// Empleados por año específico de ingreso
	List<EmployeeEntity> findByHireDateBetweenOrderByHireDateDesc(Date startDate, Date endDate);

	// Empleados que pertenecen al departamento 50 y su cargo es SH_CLERK
	Page<EmployeeEntity> findByDepartmentIdAndJobIdOrderBySalaryAsc(Long departmentId, String jobId, Pageable pageable);

	List<EmployeeEntity> findByFirstNameStartingWithAndLastNameEndingWithOrderByHireDateDesc(String sbFirstName,
			String sbLastName);

	List<EmployeeEntity> findBySalaryGreaterThanAndCommissionPctGreaterThanOrCommissionPctIsNull(double salary,
			double commissionPct);

	List<EmployeeEntity> findByFirstNameAndLastName(String firstName, String lastName);

	Page<EmployeeEntity> findByManagerIdOrderByFirstNameAscLastNameAsc(Long managerId, Pageable pageable);

	List<EmployeeEntity> findByFirstNameLikeOrderByDepartmentIdAsc(String letter);

	Page<EmployeeEntity> findByPhoneNumberStartingWithOrderByFirstNameAscLastNameAscDepartmentIdDesc(String prefix,
			Pageable pageable);

	// Consultas usando antoción @Query
	/*
	 * JPQL Sigue el estandar de SQL 1. Nombres de las tablas --> Nombre de las
	 * entidades 2. Nombres de las columnas --> Nombre de los campos en la entidad
	 * 3. Consultar todos los campos o un conjuntos de campos --> Constructor de la
	 * entidad 4. Los filtros se manejan como variables de reemplazo 4.1 Posicional
	 * 4.2 Nombrada
	 * 
	 * SELECT FROM WHERE ORDER BY
	 * 
	 * Consulta todos los campos Filtros son posicionales
	 */

//	@Query(value = "SELECT e " + "FROM EmployeeEntity e " + "WHERE e.departmentId = ?1 "
//			+ "ORDER BY e.firstName ASC, e.lastName DESC", nativeQuery = false)
//	List<EmployeeEntity> consultarPorDepartamento(Long departmentId);
//
//	@Query(value = "SELECT e " + "FROM EmployeeEntity e " + "WHERE e.departmentId = ?1 " + "AND e.jobId = ?2 "
//			+ "ORDER BY e.firstName ASC, e.lastName DESC", nativeQuery = false)
//	List<EmployeeEntity> consultarPorDepartamentoYCargo(Long departmentId, String jobId);
//
//	// Consultar algunos campos
//	// Filtros nombrados
//	@Query(value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName) " + "FROM EmployeeEntity e "
//			+ "WHERE e.salary >= :salary_in " + "OR e.commissionPct IS NULL "
//			+ "ORDER BY e.firstName ASC, e.lastName DESC", nativeQuery = false)
//	List<EmployeeEntity> consultarPorSalarioONoTieneComision(@Param("salary_in") Double salary);
//
//	@Query(value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName, e.salary, e.commissionPct) "
//			+ "FROM EmployeeEntity e " + "WHERE e.salary BETWEEN :salary_1 AND :salary_2 "
//			+ "ORDER BY e.firstName ASC, e.lastName ASC", nativeQuery = false)
//	List<EmployeeEntity> consultarPorRangoSalarial(@Param("salary_1") Double salaryStart,
//			@Param("salary_2") Double salaryEnd);
//
//	@Query(value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName, e.hireDate, e.salary, e.managerId) "
//			+ "FROM EmployeeEntity e " + "WHERE e.managerId IN (" + "  SELECT e1.id " + "  FROM EmployeeEntity e1 "
//			+ "  WHERE UPPER(e1.firstName) = UPPER(:fn) " + "  AND UPPER(e1.lastName) = UPPER(:ln)" + ") "
//			+ "ORDER BY e.firstName ASC, e.lastName ASC", nativeQuery = false)
//	List<EmployeeEntity> consultarNombreApellidoDelJefe(@Param("fn") String bossFirstName,
//			@Param("ln") String bossLastName);
//
//	@Query(value = "   SELECT COUNT(e.id) " + " FROM  EmployeeEntity e "
//			+ " WHERE e.departmentId = ?1", nativeQuery = false)
//	long contarPorDepartamento(Long departmentId);
//
//	@Query(value = "   SELECT COUNT(e.id) > 0 " + " FROM  EmployeeEntity e "
//			+ " WHERE e.departmentId = ?1", nativeQuery = false)
//	boolean existePorDepartamento(Long departmentId);
//
//	// SQL
//	@Query(value = "" + "SELECT * " + "FROM EMPLOYEES E " + "WHERE LENGTH(E.FIRST_NAME) = ?1 ", nativeQuery = true)
//	List<EmployeeEntity> consultarPorNumerodeLetrasEnNonmbre(int size);
//
//	@Query(value = "" + " SELECT E.EMPLOYEE_ID, E.FIRST_NAME, E.LAST_NAME, E.SALARY, E.COMMISSION_PCT "
//			+ "	FROM EMPLOYEES E " + "	WHERE E.SALARY BETWEEN :salary_1 AND :salary_2 "
//			+ " ORDER BY E.SALARY DESC", nativeQuery = true)
//	List<Object[]> consultarPorRangoSalarialSql(@Param("salary_1") Double salary1, @Param("salary_2") Double salary2);
//
//	@Query(value = "" + " SELECT E.EMPLOYEE_ID, E.FIRST_NAME, E.LAST_NAME, E.SALARY, E.COMMISSION_PCT "
//			+ "	FROM EMPLOYEES E " + "	WHERE E.SALARY BETWEEN :salary_1 AND :salary_2 "
//			+ " ORDER BY E.SALARY DESC", nativeQuery = true)
//	List<EmployeeEntity> consultarPorRangoSalarialSql2(@Param("salary_1") Double salary1,
//			@Param("salary_2") Double salary2);
//
//	@Query(value = "   SELECT COUNT(E.EMPLOYEE_ID) " + " FROM  EMPLOYEES E "
//			+ " WHERE E.DEPARTMENT_ID = ?1", nativeQuery = true)
//	long contarPorDepartamentoSql(Long departmentId);
	
	// =====================================================================================================================
	@Query(value = SQLConstants.SQL_FIRST_NAME_LETTER_EQUALS_LAST_NAME, nativeQuery = true)
	public List<Object[]> findByFirstNameLetterEqualsLastNameLetter(@Param("letter") String letter);
	
	


}
