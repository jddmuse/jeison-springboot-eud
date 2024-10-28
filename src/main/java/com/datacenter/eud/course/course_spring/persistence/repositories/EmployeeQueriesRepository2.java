package com.datacenter.eud.course.course_spring.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datacenter.eud.course.course_spring.persistence.entities.EmployeeEntity;
import com.datacenter.eud.course.course_spring.persistence.entities.JobEntity;
import com.datacenter.eud.course.course_spring.utils.SQLConstants;

@Repository
public interface EmployeeQueriesRepository2 extends CrudRepository<EmployeeEntity, Long> {

	/**
	 * 1. Los empleados cuya primera y última letra
	 * del nombre o del apellido son iguales
	 *
	 * Tipo 2.
	 * */
	@Query(
			value = "SELECT E.FIRST_NAME, E.LAST_NAME, E.HIRE_DATE, D.DEPARTMENT_NAME, J.JOB_TITLE, E.EMAIL "
					+ "FROM EMPLOYEES E " + "INNER JOIN JOBS J " + "ON (J.JOB_ID = E.JOB_ID) " + "LEFT JOIN departments D "
					+ "ON(D.DEPARTMENT_ID = E.DEPARTMENT_ID) "
					+ "WHERE (LOWER(E.FIRST_NAME) LIKE :letter% AND LOWER(E.FIRST_NAME) LIKE %:letter) "
					+ "OR (LOWER(E.LAST_NAME) LIKE :letter% AND LOWER(E.LAST_NAME) LIKE %:letter)" + "ORDER BY E.HIRE_DATE ASC",
			nativeQuery = true
	)
	public List<Object[]> findByFirstNameLetterEqualsLastNameLetter(@Param("letter") String letter);

	/**
	 * 2. Los empleados que ganan un rango salarial
	 * ordenados por nombre y apellido de forma ascendente.
	 *
	 * Tipo 1.
	 * */
	@Query(
			value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName, e.salary, e.commissionPct) "
					+ "FROM EmployeeEntity e " + "WHERE e.salary BETWEEN :salary_1 AND :salary_2 "
					+ "ORDER BY e.firstName ASC, e.lastName ASC",
			nativeQuery = false
	)
	List<EmployeeEntity> findBySalaryRange(@Param("salary_1") Double salaryStart,
			@Param("salary_2") Double salaryEnd);

	/**
	 * 3. Los empleados cuyo jefe es 'Harrison Bloom'
	 * (o cualquier otro nombre apellido) ordenados
	 * por nombre y apellido.
	 *
	 * Tipo 4.
	 * */
	@Query(
			value = "SELECT E.FIRST_NAME, E.LAST_NAME, E.HIRE_DATE, D.DEPARTMENT_NAME, J.JOB_TITLE, E.EMAIL "
					+ "FROM EMPLOYEES E "
					+ "INNER JOIN JOBS J ON (J.JOB_ID = E.JOB_ID) "
					+ "LEFT JOIN departments D ON(D.DEPARTMENT_ID = E.DEPARTMENT_ID) "
					+ "WHERE E.MANAGER_ID IN (SELECT EMPLOYEE_ID FROM employees EM "
					+ "  WHERE UPPER(EM.FIRST_NAME) = UPPER(:firstName) "
					+ "  AND UPPER(EM.LAST_NAME) = UPPER(:lastName)) "
					+ "ORDER BY E.FIRST_NAME ASC, E.LAST_NAME ASC",
			nativeQuery = true
	)
	public List<Object[]> findByNameManager(@Param("firstName") String firstName, @Param("lastName") String lastName);

	/**
	 * 4. Los empleados que ingresaron en el primer Q
	 * (Por parámetro) del año, ordenados por fecha de
	 * ingreso de forma ascendente.
	 *
	 * Tipo 3.
	 * */
	@Query(
			value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName, e.email, e.hireDate, e.phoneNumber) "
					+ "FROM EmployeeEntity e WHERE MONTH(e.hireDate) BETWEEN 1 AND 3 "
					+ "AND YEAR(e.hireDate) = :year",
			nativeQuery = false
		)
	List<EmployeeEntity> findEmployeesByHireDateInFirstQuartile(@Param("year") int year);

	/**
	 * 5. Los empleados que pertenecen al departamento 'Sales',
	 * ordenados por nombre y apellido.
	 *
	 * Tipo 3.
	 * */
	@Query(
			value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName, e.email, e.hireDate, e.phoneNumber) "
					+ "FROM EmployeeEntity e "
					+ "WHERE e.departmentId IN(SELECT d.id FROM DepartmentEntity d WHERE UPPER(d.name) = UPPER(:department)) "
					+ "ORDER BY e.firstName ASC, e.lastName ASC",
			nativeQuery = false
	)
    List<EmployeeEntity> findByDepartmentName(@Param("department") String department);


	/**
	 * 6. Los empleados que tienn el cargo 'Sales Representative'
	 * y tienen una comisión superior a X %, ordenados por
	 * nombre y apellido de forma descendente.
	 *
	 * Tipo 1.
	 * */
	@Query(
			value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName, e.salary, e.commissionPct) "
					+ "FROM EmployeeEntity e "
					+ "WHERE e.jobId in(SELECT j.jobId FROM JobEntity j WHERE LOWER(j.jobTitle) = LOWER(:jobName)) "
					+ "AND e.commissionPct > :percent "
					+ "ORDER BY e.firstName DESC, e.lastName DESC ",
			nativeQuery = false
	)
    List<EmployeeEntity> findByJobAndPercent(@Param("jobName") String jobName, @Param("percent") Double percent);

	/**
	 * 7. Los empleados que son jefe de algún departamento,
	 * ordenado por nombre de departamento de forma
	 * ascendente.
	 *
	 * Tipo 2.
	 * */
	@Query(
			value = "SELECT e.first_name, E.last_name, e.hire_date, d.department_name, j.job_title, e.email "
					+ "FROM employees e "
					+ "INNER JOIN jobs j ON (j.job_id = e.job_id) "
					+ "INNER JOIN departments d ON(d.manager_id = e.employee_id) "
					+ "ORDER BY d.department_name ASC",
			nativeQuery = true
	)
	public List<Object[]> findByEmployeeIsBoss();

	/**
	 * 8. Los empleados que son más antiguos (o nuevos,
	 * pasado por parámetro) que sus jefes ordenados por
	 * nombre y apellido.
	 *
	 * Tipo 3.
	 * */
	@Query(
			value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName, e.email, e.hireDate, e.phoneNumber) "
					+ "FROM EmployeeEntity e "
					+ "WHERE e.managerId = :employeeId "
					+ "AND (e.hireDate <> (SELECT m.hireDate FROM EmployeeEntity m WHERE m.id = e.managerId)) "
					+ "ORDER BY e.firstName ASC, e.lastName ASC",
			nativeQuery = false
	)
    List<EmployeeEntity> findEmployeesOlder(@Param("employeeId") int employeeId);

	/**
	 * 9. Los empleados que han tenido más de un cargo en la compañia
	 * (Ver tabla JOB_HISTORY) ordenados por fecha de ingreso de
	 * forma ascendente.
	 *
	 * Tipo 2.
	 * */
	@Query(
			value = "SELECT e.first_name, E.last_name, e.hire_date, d.department_id, d.department_name, j.job_title, e.email "
					+ "FROM employees e "
					+ "INNER JOIN jobs j ON(j.job_id = e.job_id) "
					+ "LEFT JOIN departments d ON(d.department_id = e.department_id) "
					+ "WHERE e.employee_id IN ( "
					+ "    SELECT employee_id "
					+ "    FROM job_history "
					+ "    GROUP BY employee_id "
					+ "    HAVING COUNT(DISTINCT job_id) > 1 n"
					+ ") "
					+ "ORDER BY e.hire_date ASC",
			nativeQuery = true
	)
	public List<Object[]> findByEmployeesMoreOneJob();

	/**
	 * 10. Los empleados que nunca han tenido un cambio
	 * de cargo en la empresa, ordenados por nombre y apellido.
	 *
	 * Tipo 1.
	 * */
	@Query(
			value = "SELECT new EmployeeEntity(e.id, e.firstName, e.lastName, e.email, e.hireDate, e.phoneNumber) "
					+ "FROM EmployeeEntity e "
					+ "WHERE ( "
					+ "    SELECT COUNT(DISTINCT j.jobId) "
					+ "    FROM JobHistoryEntity j "
					+ "    WHERE j.employeeId = e.id "
					+ ") <= 1 "
					+ "ORDER BY e.firstName asc, e.lastName asc",
			nativeQuery = false
	)
    List<EmployeeEntity> findEmployeesNoChangeJobs();

	/**
	 * 11. El historial de cargos de un empleado en la
	 * compañia (por ID) ordeados por fecha de ingreso de
	 * forma ascendente.
	 *
	 * Tipo 2.
	 * */
	@Query(
			value = "SELECT E.FIRST_NAME, E.LAST_NAME, E.HIRE_DATE, D.DEPARTMENT_NAME, J.JOB_TITLE, E.EMAIL "
					+ "FROM EMPLOYEES E "
					+ "INNER JOIN JOBS J ON (J.JOB_ID = E.JOB_ID) "
					+ "LEFT JOIN departments D ON(D.DEPARTMENT_ID = E.DEPARTMENT_ID) "
					+ "WHERE E.JOB_ID IN((SELECT JH.JOB_ID FROM JOB_HISTORY JH WHERE EMPLOYEE_ID = :employeeId)) "
					+ "ORDER BY E.HIRE_DATE ASC",
			nativeQuery = true
	)
	public List<Object[]> findByHistoryEmployeeJobs(@Param("employeeId") int employeeId);

	/**
	 * 12. Los cargos (ID, nombre, rango salarial) que coinciden
	 * con una aspiración salarial ingresada, ordenados
	 * por nombrede forma ascendente.
	 * */
	@Query(
			name = "JobEntity.findRangeSalary",
			nativeQuery = true
	)
	public List<JobEntity> findJobsBySalaryRange(@Param("salary") Double salary);

	/**
	 * 13. Los departamentos, con su ubicación, país y región.
	 * Cada uno debe incluir el código y el nombre. Ordenados
	 * de forma ascendente por nombre de ubicación, país y región.
	 * */
	@Query(
			value = "SELECT d.department_id, "
					+ "       d.department_name, "
					+ "       m.first_name || ' ' || m.last_name AS manager_name, "
					+ "       COUNT(e.employee_id) AS employee_count "
					+ "FROM departments d "
					+ "JOIN employees m ON d.manager_id = m.employee_id "
					+ "LEFT JOIN employees e ON d.department_id = e.department_id "
					+ "GROUP BY d.department_id, d.department_name, m.first_name, m.last_name "
					+ "HAVING COUNT(e.employee_id) > :qty "
					+ "ORDER BY employee_count ASC ",
			nativeQuery = true
	)
	public List<Object[]> findDepartmentsQtyEmployees(@Param("qty") int qty);


}
