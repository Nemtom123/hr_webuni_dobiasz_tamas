package hu.webuni.hr.tamasdobiasz.web;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.mapper.EmployeeMapper;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import hu.webuni.hr.tamasdobiasz.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hr")
public class HrController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;


    @GetMapping
    public List<HrDto> getAll() {
        return employeeMapper.employeesToDtos(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HrDto> getById(@PathVariable long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null)
            return ResponseEntity.ok(employeeMapper.employeeToDto(employee));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public HrDto addEmployee(@RequestBody @Valid HrDto employeeDto) {
        Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
        return employeeMapper.employeeToDto(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HrDto> setEmployee(@PathVariable long id, @RequestBody @Valid HrDto employeeDto) {
        if (employeeService.findById(id) == null)
            return ResponseEntity.notFound().build();
        employeeDto.setEmployeeId(id);
        Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
        return ResponseEntity.ok(employeeMapper.employeeToDto(employee));
    }

    @GetMapping(params = "salary")
    public List<HrDto> salary(@RequestParam int aboveSalary) {
        return employeeMapper.employeesToDtos(employeeService.findAboveASalary(aboveSalary));
    }

    @PostMapping("/risesalary")
    public int riseSalary(@RequestBody HrDto employeeDto) {
        return employeeService.getPayRaisePercent(employeeMapper.dtoToEmployee(employeeDto));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
    }
}

