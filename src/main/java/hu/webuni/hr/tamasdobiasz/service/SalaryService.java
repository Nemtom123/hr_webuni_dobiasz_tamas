package hu.webuni.hr.tamasdobiasz.service;

import hu.webuni.hr.tamasdobiasz.model.Employee;
import hu.webuni.hr.tamasdobiasz.repository.EmployeeRepository;
import hu.webuni.hr.tamasdobiasz.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.tamasdobiasz.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaryService {

    private EmployeeService employeeService;
    private PositionRepository positionRepository;
    PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;

    EmployeeRepository employeeRepository;


    public SalaryService(EmployeeService employeeService, PositionRepository positionRepository,
                         PositionDetailsByCompanyRepository positionDetailsByCompanyRepository,
                         EmployeeRepository employeeRepository) {
        super();
        this.employeeService = employeeService;
        this.positionRepository = positionRepository;
        this.positionDetailsByCompanyRepository = positionDetailsByCompanyRepository;
        this.employeeRepository = employeeRepository;
    }

    public void setNewSalary(Employee employee) {
        int newSalary = employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
        employee.setSalary(newSalary);
    }

//	@Transactional
//	public void raiseMinimalSalary(String positionName, int minSalary) {
//		positionRepository.findByName(positionName)
//		.forEach(p ->{
//			p.setMinSalary(minSalary);
//			p.getEmployees().forEach(e ->{
//				if(e.getSalary() < minSalary)
//					e.setSalary(minSalary);
//			});
//		});
//	}

    @Transactional
    public void raiseMinimalSalary(String positionName, int minSalary, long companyId) {
        positionDetailsByCompanyRepository.findByPositionNameAndCompanyId(positionName, companyId)
                .forEach(pd -> {
                    pd.setMinSalary(minSalary);
//			pd.getCompany().getEmployees().forEach(e ->{
//				if(e.getPosition().getName().equals(positionName)
//						&& e.getSalary() < minSalary)
//					e.setSalary(minSalary);
//			});
                });

        employeeRepository.updateSalaries(positionName, minSalary, companyId);
    }

}
