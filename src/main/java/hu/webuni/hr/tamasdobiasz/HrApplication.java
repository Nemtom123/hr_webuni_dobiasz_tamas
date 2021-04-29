package hu.webuni.hr.tamasdobiasz;

import hu.webuni.hr.tamasdobiasz.model.Employee;
import hu.webuni.hr.tamasdobiasz.service.SalaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class HrApplication implements CommandLineRunner {
	@Autowired
	SalaryService salaryService;

	Employee employee = new Employee();

	public static void main(String[] args) {

		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1L, "John Doe", "Director",2250000, LocalDateTime.of(2010, 9, 20, 8, 10, 10)));
		employees.add(new Employee(2L, "John Boe", "Fisherman",350000, LocalDateTime.of(2014, 9, 20, 8, 10, 10)));
		employees.add(new Employee(3L, "John Dean","Testing",250000, LocalDateTime.of(2016, 9, 20, 8, 10, 10)));
		employees.add(new Employee(4L, "Mariska Margittai","Application developer",450000, LocalDateTime.of(2017, 9, 20, 8, 10, 10)));
		employees.add(new Employee(5L, "John Malkovic","Achtor",6250000, LocalDateTime.of(2018, 9, 20, 8, 10, 10)));
		employees.add(new Employee(6L, "John Wick","Starring",4250000, LocalDateTime.of(2020, 9, 20, 8, 10, 10)));
		employees.add(new Employee(7L, "John Wick1","Statistics",1250000, LocalDateTime.of(2009, 9, 20, 8, 10, 10)));
		employees.add(new Employee(8L, "John Wick2","Statistics",350000, LocalDateTime.of(2011, 9, 20, 8, 10, 10)));
		employees.add(new Employee(9L, "John Wick3","Statistics",550000, LocalDateTime.of(2013, 9, 20, 8, 10, 10)));
		employees.add(new Employee(10L,"John Wick4","Statistics",350000, LocalDateTime.of(2019, 9, 20, 8, 10, 10)));
		for (Employee names : employees) {
			System.out.println(names.getEmployeeId() + "  " + "New Salery : " + salaryService.getNewSalary(names));
		}
	}

}
