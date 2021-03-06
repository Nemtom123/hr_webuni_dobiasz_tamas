package hu.webuni.hr.tamasdobiasz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.tamasdobiasz.model.Position;
import hu.webuni.hr.tamasdobiasz.model.PositionDetailsByCompany;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long> {

	List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long companyId);

}
