package hu.webuni.hr.tamasdobiasz.repository;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.model.AverageSalaryByPosition;
import hu.webuni.hr.tamasdobiasz.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("SELECT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary")
	public List<Company> findByEmployeeWithSalaryHigherThan(int minSalary);

	@Query("SELECT DISTINCT c FROM Company c INNER JOIN c.employees e WHERE e.name = name")
	Optional<Company> findWorkByName(String workName);

	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :minEmployeeCount")
	public List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);

	@Query("SELECT e.position.name AS position, avg(e.salary) AS averageSalary "
			+ "FROM Company c "
			+ "JOIN c.employees e "
			+ "WHERE c.id=:companyId "
			+ "GROUP BY e.position.name "
			+ "ORDER BY avg(e.salary) DESC")
	public List<AverageSalaryByPosition> findAverageSalaryByPosition(long companyId);

	@EntityGraph("companyWithEmployeesAndEmployeePositions")
	@Query("SELECT DISTINCT c FROM Company c INNER JOIN c.employees e WHERE e.salary > :salary")
	List<Company> findWhereEmployeeSalaryIsGreaterThanCompaniesCompanies(int salary);

	@EntityGraph("companyWithEmployeesAndEmployeePositions")
	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :employeeNumber")
	List<Company> findWhereEmployeeNumberIsAboveCompanies(int employeeNumber);

	Optional<Company> findByCompanyName(String name);


	@EntityGraph("companyWithEmployeesAndEmployeePositions")
	@Query("SELECT c FROM Company c")
	List<Company> findAllWithCompanies();

	@EntityGraph("companyWithEmployeesAndEmployeePositions")
	@Query("SELECT c FROM Company c WHERE c.companyRegistrationNumber = :id")
	Optional<Company> findByWithCompany(long id);

	@EntityGraph("companyWithEmployeesAndEmployeePositions")
	@Query("SELECT DISTINCT c FROM Company c INNER JOIN c.employees e WHERE e.salary > :salary")
	List<Company> findWhereEmployeeSalaryIsGreaterThan(int salary);

	@EntityGraph("companyWithEmployeesAndEmployeePositions")
	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :employeeNumber")
	List<Company> findWhereEmployeeNumberIsAbove(int employeeNumber);

	@Query("SELECT new hu.webuni.hr.tamasdobiasz.dto.HrDto(e.position.name, avg(e.salary)) FROM Company c INNER JOIN c.employees e WHERE c.companyRegistrationNumber = :id GROUP BY e.position.name ORDER BY avg(e.salary) DESC")
	List<HrDto> listAverageSalaryiesGroupedByTitlesAtACompany(Long id);

	@EntityGraph("companyWithEmployeesAndEmployeePositions")
	@Query("SELECT c FROM Company c")
	List<Company> findAllWithEmployees();

	@EntityGraph("companyWithEmployeesAndEmployeePositions")
	@Query("SELECT c FROM Company c WHERE c.companyRegistrationNumber = :id")
	Optional<Company> findByWithEmployees(long id);

}
