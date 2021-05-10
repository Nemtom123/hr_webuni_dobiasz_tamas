package hu.webuni.hr.tamasdobiasz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.tamasdobiasz.service.EmployeeService;
import hu.webuni.hr.tamasdobiasz.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartSalaryConfig {
	@Bean
	public EmployeeService employeeService() {
		return (EmployeeService) new SmartEmployeeService();
	}
}
