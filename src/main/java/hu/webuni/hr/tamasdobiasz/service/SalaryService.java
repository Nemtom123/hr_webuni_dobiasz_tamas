package hu.webuni.hr.tamasdobiasz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.tamasdobiasz.model.Employee;

@Service
public class SalaryService {

	private EmployeeService employeeService;

	@Autowired
	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

    public int getNewSalary(Employee employee) {
		return employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
	}

}
