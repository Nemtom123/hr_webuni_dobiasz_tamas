package hu.webuni.hr.tamasdobiasz.web;


import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class HrTLController {
    private List<HrDto> allEmployees = new ArrayList<>();

    {
        allEmployees.add(new HrDto(1L, "Jhon Doe", "Director", 100000, LocalDateTime.of(2012, 1, 1, 8, 0, 0)));
    }

    @GetMapping("/employees")
    public String listEmployees(Map<String, Object> model) {
        model.put("employees", allEmployees);
        model.put("newEmployee", new HrDto());
        return "employees";
    }

    @GetMapping("/add")
    public String addEmployee(Map<String, Object> model) {
        model.put("add", allEmployees);
        model.put("newadd", new HrDto());
        return "add";
    }

    @PostMapping("/add")
    public String addEmployeeAdd(HrDto hrDto){
        allEmployees.add(hrDto);
        return "redirect:employees";
    }

    @PostMapping("/employees")
    public String addEmployee(HrDto employee) {
        allEmployees.add(employee);
        return "redirect:employees";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(HrDto employee) {
        for(int i=0; i < allEmployees.size(); i++) {
            if(allEmployees.get(i).getEmployeeId() == employee.getEmployeeId()) {
                allEmployees.set(i, employee);
                break;
            }
        }
        return "redirect:employees";
    }

    @GetMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable long employeeId) {
        allEmployees.removeIf(emp -> emp.getEmployeeId() == employeeId);
        return "redirect:/employees";
    }

    @GetMapping("employees/{id}")
    public String editEmployee(@PathVariable long id, Map<String, Object> model) {
        model.put("employee", allEmployees.stream().filter(e -> e.getEmployeeId() == id).findFirst().get());
        return "/editEmployee";
    }

}