package hu.webuni.hr.tamasdobiasz.mapper;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<HrDto> employeesToDtos(List<Employee> employees);

    @Mapping(target = "companyName", source = "company.companyName")
    @Mapping(target = "jobTitle", source = "position.name")
    HrDto employeeToDto(Employee employee);

    Employee dtoToEmployee(HrDto employeeDto);

    List<Employee> dtosToEmployees(List<HrDto> employees);
}

