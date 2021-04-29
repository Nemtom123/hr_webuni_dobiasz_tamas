package hu.webuni.hr.tamasdobiasz.repository;

import hu.webuni.hr.tamasdobiasz.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}