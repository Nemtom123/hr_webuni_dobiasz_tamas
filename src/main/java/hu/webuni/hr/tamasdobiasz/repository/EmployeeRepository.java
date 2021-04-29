package hu.webuni.hr.tamasdobiasz.repository;

import hu.webuni.hr.tamasdobiasz.model.Company;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySalaryGreaterThan(Integer minSalary);
    List<Employee> findByTitle(String title);
    List<Employee> findByNameStartingWithIgnoreCase(String name);
    List<Employee> findByEntryDateBetween(LocalDateTime start, LocalDateTime end);

}