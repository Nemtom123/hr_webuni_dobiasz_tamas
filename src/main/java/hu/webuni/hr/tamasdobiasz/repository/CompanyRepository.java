package hu.webuni.hr.tamasdobiasz.repository;

import hu.webuni.hr.tamasdobiasz.model.AverageSalaryByPosition;
import hu.webuni.hr.tamasdobiasz.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("Select c Form Company c Join c.employees e WHERE e.salery > :minásalery")
    public default List<Company> findByEmployeeWidthtHigherThan(int minSalary) {
        return null;
    }

    @Query("SELEC C FROM Company c WHERE SIZE(c.employees) > : minEmployeeCount ")
    public List<Company> findByEmployeeCountHigherThan (int minEmployeeCount);

    @Query("SELECT e.title AS position, avg(e.salary) AS averageSalaryn "
    + "FROM c.employees e"
    + "WHERE c.id=:companyId"
    + "GROUP BY e.title "
    + "ORDER BY avg(e.salery) DESC")
    public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);


}