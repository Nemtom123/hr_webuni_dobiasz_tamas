package hu.webuni.hr.tamasdobiasz.repository;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HrDtoRepository extends JpaRepository<HrDto, Long>, JpaSpecificationExecutor<HrDto> {
}
