package hu.webuni.hr.tamasdobiasz.service;

import static org.assertj.core.api.Assertions.assertThat;


import hu.webuni.hr.tamasdobiasz.dto.CompanyDto;
import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.model.Company;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import hu.webuni.hr.tamasdobiasz.repository.CompanyRepository;
import hu.webuni.hr.tamasdobiasz.repository.EmployeeRepository;
import hu.webuni.hr.tamasdobiasz.web.CompanyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;


@SpringBootTest
public class CompanyControllerIT {

    @Autowired
    CompanyService companyService;


    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeService employeeService;


    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void testCreateEmployee() throws Exception {
//        Long id = employeeRepository.save(new Employee(1L,"Mort", 1000, LocalDateTime.of(2021,10,10,10,10,10))).getCompanyId();
//        LocalDateTime of = LocalDateTime.now();
//        Company company = companyService.createEmployee(id, of);
//        Optional<Company> savedEmployeeOptional = companyRepository.findById(company.getId());
//        assertThat(savedEmployeeOptional).isNotEmpty();
//        Company savedCompany = savedEmployeeOptional.get();
//        assertThat(savedCompany.getEmployees()).isEqualTo(id);
    }

}
