package hu.webuni.hr.tamasdobiasz.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.tamasdobiasz.config.HrConfigProperties;
import hu.webuni.hr.tamasdobiasz.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {

		double yearsWorked = ChronoUnit.DAYS.between(employee.getDateOfStartWork(), LocalDateTime.now()) / 365.0;

		HrConfigProperties.Smart smartConfig = config.getSalary().getSmart();
//		if(yearsWorked > smartConfig.getLimit3())
//			return smartConfig.getPercent3();
//
//		if(yearsWorked > smartConfig.getLimit2())
//			return smartConfig.getPercent2();
//
//		if(yearsWorked > smartConfig.getLimit1())
//			return smartConfig.getPercent1();

//		Integer maxLimit = null;

//		for(Entry<Double, Integer> limitEntry : smartConfig.getLimits().entrySet()) {
//			if(yearsWorked > limitEntry.getKey())
//				maxLimit = limitEntry.getValue();
//			else
//				break;
//		}
//		return maxLimit == null ? 0 : maxLimit;

		TreeMap<Double, Integer> limits = smartConfig.getLimits();

//		Optional<Double> optionalMax = limits.keySet()
//		.stream()
//		.filter(k -> yearsWorked >= k )
//		.max(Double::compare);
//
//		return optionalMax.isEmpty() ? 0 : limits.get(optionalMax.get());

		Map.Entry<Double, Integer> floorEntry = limits.floorEntry(yearsWorked);
		return floorEntry == null ? 0 : floorEntry.getValue();
	}

}
