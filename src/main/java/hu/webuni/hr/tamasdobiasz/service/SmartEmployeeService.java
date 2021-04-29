package hu.webuni.hr.tamasdobiasz.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.tamasdobiasz.config.HrConfigProperties;
import hu.webuni.hr.tamasdobiasz.model.Employee;

@Service
public class SmartEmployeeService extends EmployeeService {

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		long months = java.time.temporal.ChronoUnit.MONTHS.between(employee.getDateOfStartWork(), LocalDateTime.now());
		return config.getSalary().getSmart().getRaisingIntervals().get(config.getSalary().getSmart()
				.getRaisingIntervals().keySet().stream().filter(k -> months >= k).max(Integer::compare).get());

	}

}
