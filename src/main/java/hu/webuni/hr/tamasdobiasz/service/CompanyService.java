package hu.webuni.hr.tamasdobiasz.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.model.Company;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import hu.webuni.hr.tamasdobiasz.repository.CompanyRepository;
import hu.webuni.hr.tamasdobiasz.repository.EmployeeRepository;

@NamedEntityGraph(
        name = "Company.full",
        attributeNodes = @NamedAttributeNode("employees"))
@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Company update(Company company) {
        if (!companyRepository.existsById(company.getId()))
            return null;
        return companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(long id) {
        return companyRepository.findById(id);
    }

    public void delete(long id) {
        companyRepository.deleteById(id);
    }

    public Company addEmployee(long id, Employee employee) {
        Company company = companyRepository.findById(id).get();
        company.addEmployee(employee);
        employeeRepository.save(employee);
        return company;
    }

    public Company deleteEmployee(long id, long employeeId) {
        Company company = companyRepository.findById(id).get();
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setCompany(null);
        company.getEmployees().remove(employee);
        employeeRepository.save(employee);
        return company;
    }

    public Company replaceEmployees(long id, List<Employee> employees) {
        Company company = companyRepository.findById(id).get();
        company.getEmployees().stream().forEach(e -> {
            e.setCompany(null);
        });
        company.getEmployees().clear();

        for (Employee emp : employees) {
            company.addEmployee(emp);
            employeeRepository.save(emp);
        }
        return company;
    }


    public Employee createEmployee() {
        Employee employee = new Employee();
        employee.setId(employeeRepository.findById(1L).get().getId());

        return null;
    }


    /*Dinamikus-keresés-Spring-Data-ban */
    public List<Employee> findHrdtoExamle(HrDto example) {
        long id = example.getEmployeeId();
        String name = example.getWorkName();
        String jobTitle = example.getJobTitle();
        int salary = example.getSalary();
        LocalDateTime entryDate = example.getDateOfStartWork();
        String companyName = example.getCompanyName();

        Specification<Employee> spec = Specification.where(null);

        if (id > 0) {
            spec = spec.and(EmployeeSpecifications.hasId(id));
        }

        if (StringUtils.hasText(name))
            spec = spec.and(EmployeeSpecifications.hasName(name));

        if (StringUtils.hasText(jobTitle))
            spec = spec.and(EmployeeSpecifications.hasJobTitle(jobTitle));

        if (salary != 0)
            spec = spec.and(EmployeeSpecifications.hasSalary(salary));

        if (entryDate != null)
            spec = spec.and(EmployeeSpecifications.hasEntryDate(entryDate));


        if (StringUtils.hasText(companyName))
            spec = spec.and(EmployeeSpecifications.hasCompanyName(companyName));

        return employeeRepository.findAll(spec, Sort.by("id"));

    }



}

