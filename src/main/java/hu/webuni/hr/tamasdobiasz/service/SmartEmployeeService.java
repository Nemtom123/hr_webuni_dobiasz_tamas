package hu.webuni.hr.tamasdobiasz.service;

import hu.webuni.hr.tamasdobiasz.config.HrConfigProperties;
import hu.webuni.hr.tamasdobiasz.config.HrConfigProperties.Smart;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {

		double yearsWorked = ChronoUnit.DAYS.between(employee.getEntryDate(), LocalDateTime.now()) / 365.0;
		Smart smartConfig = config.getSalary().getSmart();
		TreeMap<Double, Integer> limits = smartConfig.getLimits();
		Map.Entry<Double, Integer> floorEntry = limits.floorEntry(yearsWorked);
		return floorEntry == null ? 0 : floorEntry.getValue();
	}

	@Override
	public void createHrDto(long id, String name, String jobTittle, int salary, LocalDateTime date) {

	}

}
