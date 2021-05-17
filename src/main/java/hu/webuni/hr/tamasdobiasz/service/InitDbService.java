package hu.webuni.hr.tamasdobiasz.service;


import hu.webuni.hr.tamasdobiasz.model.*;
import hu.webuni.hr.tamasdobiasz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;


@Service
public class InitDbService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;


    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public InitDbService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUserIfNeeded() {
        if (!userRepository.existsById("admin")) {
            userRepository.save(new EmployeeUser("admin", passwordEncoder.encode("pass"), Set.of("admin", "user")));
        }
        if (!userRepository.existsById("admin")) {
            userRepository.save(new EmployeeUser("user", passwordEncoder.encode("pass"), Set.of("user")));
        }


//        Position developer = positionRepository.save(new Position("Head Chef", Qualification.UNIVERSITY));
//        Position tester = positionRepository.save(new Position("Chef", Qualification.HIGH_SCHOOL));
//
//        Employee newEmployee1 = employeeRepository.save(new Employee(null, "John Malkovics ", 200000, LocalDateTime.now()));
//        newEmployee1.setPosition(developer);
//
//        Employee newEmployee2 = employeeRepository.save(new Employee(null, "Terminator T3000", 200000, LocalDateTime.now()));
//        newEmployee2.setPosition(tester);
//        Company newCompany = companyRepository.save(new Company(null, 10L, "Fiktív Univerzum", "Budapest Király 12", null));
//        newCompany.addEmployee(newEmployee2);
//        newCompany.addEmployee(newEmployee1);
//
//        PositionDetailsByCompany pd = new PositionDetailsByCompany();
//        pd.setCompany(newCompany);
//        pd.setMinSalary(250000);
//        positionDetailsByCompanyRepository.save(pd);
//
//        PositionDetailsByCompany pd2 = new PositionDetailsByCompany();
//        pd2.setCompany(newCompany);
//        pd2.setMinSalary(200000);
//        pd2.setPosition(tester);
//        positionDetailsByCompanyRepository.save(pd2);

    }

}