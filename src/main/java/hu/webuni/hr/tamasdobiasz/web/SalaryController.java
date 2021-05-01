package hu.webuni.hr.tamasdobiasz.web;

import hu.webuni.hr.tamasdobiasz.service.SalaryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public class SalaryController {

    SalaryService salaryService;

    @PutMapping("/{positionName}/raiseMin/{minSalary}/{companyId}")
    public void raiseMinSalary(@PathVariable String positionName, @PathVariable int minSalary, @PathVariable long companyId) {
        salaryService.raiseMinimalSalary(positionName, minSalary, companyId);
    }
}
