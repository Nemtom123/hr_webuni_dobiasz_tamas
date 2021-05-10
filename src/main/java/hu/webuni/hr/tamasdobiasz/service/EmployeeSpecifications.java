package hu.webuni.hr.tamasdobiasz.service;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EmployeeSpecifications {


    /*Keresés-id-alapján*/
    public static Specification<HrDto> hasId(long id){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"),id));
    }

    /*Keresés-név-és-like-alapján*/
    public static Specification<HrDto> hasName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),name + "%"));

    }

    /*Keresés-munkakör-alapján-teljesegyezés*/
    public static Specification<HrDto> hasJobTitle(String jobTitle) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("jobTitle"),jobTitle));
    }

    /*Keresés-fizetés-alapján-+5%*/
    public static Specification<HrDto> hasSalary(double salary) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("salary"),(salary+salary*100/20),salary-salary*100/20));
    }

    /*Keresés-belépés-alapján-teljes egyezés*/
    public static Specification<HrDto> hasEntryDate(LocalDateTime entryDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("entryDate"),entryDate));
    }

    /*Keresés-Cég-alapján*/
    public static Specification<HrDto> hasCompanyName(String companyName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("companyName"),companyName + "%"));

    }
}
