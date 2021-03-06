package hu.webuni.hr.tamasdobiasz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.tamasdobiasz.config.HrConfigProperties;
import hu.webuni.hr.tamasdobiasz.model.Employee;

import java.time.LocalDateTime;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService {
	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getSalary().getDef().getPercent();
	}

	@Override
	public void createHrDto(long id, String name, String jobTittle, int salary, LocalDateTime date) {

	}

}
