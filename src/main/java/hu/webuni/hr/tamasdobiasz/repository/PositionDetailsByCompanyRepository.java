package hu.webuni.hr.tamasdobiasz.repository;

import java.util.List;

import hu.webuni.hr.tamasdobiasz.model.PositionDetailsByCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompanyRepository, Long> {
    List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long companyId);
}
