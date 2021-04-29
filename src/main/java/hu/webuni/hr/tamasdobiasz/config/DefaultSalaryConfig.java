package hu.webuni.hr.tamasdobiasz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.tamasdobiasz.service.DefaultEmployeeService;
import hu.webuni.hr.tamasdobiasz.service.EmployeeService;


@Configuration
@Profile("!smart")

public class DefaultSalaryConfig{
	@Bean
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}
}
