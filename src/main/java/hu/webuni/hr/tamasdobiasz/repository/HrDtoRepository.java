package hu.webuni.hr.tamasdobiasz.repository;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface HrDtoRepository extends JpaRepository<HrDto, Long>, JpaSpecificationExecutor<HrDto> {

    List<HrDto> findByHrName(String workName);
    List<HrDto> findByHrJobTitle(String jobTitle);

}
