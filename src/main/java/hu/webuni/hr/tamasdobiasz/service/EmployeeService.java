package hu.webuni.hr.tamasdobiasz.service;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.model.Employee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class EmployeeService {
    public abstract int getPayRaisePercent(Employee employee);
        private Map<Long, Employee> employees = new HashMap<>();


        {
            employees.put(1L, new Employee(1L, "John Doe", "Director", 2250000,   LocalDateTime.parse("2010-02-12T10:10:10")));
            employees.put(2L, new Employee(2L, "John Boe", "Fisherman", 350000,   LocalDateTime.parse("2012-03-12T10:10:10")));
            employees.put(3L, new Employee(3L, "John Dean", "Testing", 250000,   LocalDateTime.parse("2013-04-12T10:10:10")));
            employees.put(4L, new Employee(4L, "Johny English", "Developer", 450000,   LocalDateTime.parse("2014-07-12T10:10:10")));
            employees.put(5L, new Employee(5L, "John Malkovic", "Achtor", 6250000, LocalDateTime.parse("2015-02-12T10:10:10")));
            employees.put(6L, new Employee(6L, "John Wick", "Starring", 4250000, LocalDateTime.parse("2017-02-12T10:10:10")));
            employees.put(7L, new Employee(7L, "John Wick1", "Statistics", 1250000, LocalDateTime.parse("2018-02-12T10:10:10")));
            employees.put(8L, new Employee(8L, "John Wick2", "Statistics", 350000, LocalDateTime.parse("2019-02-12T10:10:10")));
            employees.put(9L, new Employee(9L, "John Wick3", "Statistics", 550000, LocalDateTime.parse("2020-02-12T10:10:10")));
            employees.put(10L,new Employee(10L, "John Wick4", "Statistics", 350000, LocalDateTime.parse("2021-02-12T10:10:10")));
        }

    public List<Employee> findAll() {
        return new ArrayList<Employee>(employees.values());
    }

    public Employee findById(long id) {
        return employees.get(id);
    }

    public List<Employee> findAboveASalary(int aboveSalary) {
        return employees.values().stream().filter(e -> e.getSalary() > aboveSalary).collect(Collectors.toList());
    }

    public Employee save(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
        return employee;
    }


    public void delete(long id) {
        employees.remove(id);
    }




}


