package hu.webuni.hr.tamasdobiasz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.tamasdobiasz.config.HrConfigProperties;
import hu.webuni.hr.tamasdobiasz.model.Employee;

@Service
public class DefaultEmployeeService extends EmployeeService {
	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getSalary().getDef().getPercent();
	}

}
