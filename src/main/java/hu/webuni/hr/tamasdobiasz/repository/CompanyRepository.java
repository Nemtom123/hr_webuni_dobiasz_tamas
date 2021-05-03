package hu.webuni.hr.tamasdobiasz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.tamasdobiasz.model.AverageSalaryByPosition;
import hu.webuni.hr.tamasdobiasz.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("SELECT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary")
	public List<Company> findByEmployeeWithSalaryHigherThan(int minSalary);


	//@Query("SELECT DISTINC c FROM Company c LEFT JOIN FETCH c.employees")
	@EntityGraph("Company.full")
	@Query("SELECT c FROM Company c")
	public List<Company> findAllWhithEmployees();

	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :minEmployeeCount")
	public List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);

	@Query("SELECT e.position.name AS position, avg(e.salary) AS averageSalary "
			+ "FROM Company c "
			+ "JOIN c.employees e "
			+ "WHERE c.id=:companyId "
			+ "GROUP BY e.position.name "
			+ "ORDER BY avg(e.salary) DESC")
	public List<AverageSalaryByPosition> findAverageSalaryByPosition(long companyId);

}
