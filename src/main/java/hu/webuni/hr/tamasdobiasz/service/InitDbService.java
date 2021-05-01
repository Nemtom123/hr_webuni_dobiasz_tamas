package hu.webuni.hr.tamasdobiasz.service;


import hu.webuni.hr.tamasdobiasz.model.Company;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import hu.webuni.hr.tamasdobiasz.model.Position;
import hu.webuni.hr.tamasdobiasz.model.PositionDetailsByCompany;
import hu.webuni.hr.tamasdobiasz.model.Qualification;
import hu.webuni.hr.tamasdobiasz.repository.CompanyRepository;
import hu.webuni.hr.tamasdobiasz.repository.EmployeeRepository;
import hu.webuni.hr.tamasdobiasz.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.tamasdobiasz.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public class InitDbService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;

    @Transactional
    public void initDb() {

        Position developer = positionRepository.save(new Position("fejlesztő", Qualification.UNIVERSITY));
        Position tester = positionRepository.save(new Position("tesztelő", Qualification.HIGH_SCHOOL));

        Employee newEmployee1 = employeeRepository.save(new Employee(null, "John Malkovics ","Director", 200000, LocalDateTime.now()));
        newEmployee1.setPosition(developer);

        Employee newEmployee2 = employeeRepository.save(new Employee(null, "Terminator T3000","Achtor", 200000, LocalDateTime.now()));
        newEmployee2.setPosition(tester);
        Company newCompany = companyRepository.save(new Company(null, 10L, "Fiktív Univerzum", "Budapest Király 12", null));
        newCompany.addEmployee(newEmployee2);
        newCompany.addEmployee(newEmployee1);

        PositionDetailsByCompany pd = new PositionDetailsByCompany();
        pd.setCompany(newCompany);
        pd.setMinSalary(250000);
        pd.setPosition(developer);
        //positionDetailsByCompanyRepository.save(pd);

        PositionDetailsByCompany pd2 = new PositionDetailsByCompany();
        pd2.setCompany(newCompany);
        pd2.setMinSalary(200000);
        pd2.setPosition(tester);
        //positionDetailsByCompanyRepository.save(pd2);
    }
}
