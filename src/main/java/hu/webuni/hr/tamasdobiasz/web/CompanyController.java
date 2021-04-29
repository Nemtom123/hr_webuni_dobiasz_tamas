package hu.webuni.hr.tamasdobiasz.web;

import com.fasterxml.jackson.annotation.JsonView;
import hu.webuni.hr.tamasdobiasz.dto.CompanyDto;
import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.dto.Views;
import hu.webuni.hr.tamasdobiasz.mapper.CompanyMapper;
import hu.webuni.hr.tamasdobiasz.mapper.EmployeeMapper;
import hu.webuni.hr.tamasdobiasz.model.Company;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import hu.webuni.hr.tamasdobiasz.service.CompanyService;
import hu.webuni.hr.tamasdobiasz.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<CompanyDto> getCompanys(@RequestParam(required = false) Boolean full) {
        List<Company> companies = companyService.findAll();
        return full == null || full == false ?
                companyMapper.companySummariesToDtos(companies)
                : companyMapper.companiesToDtos(companies);
    }


    @GetMapping("/{id}")
    public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
        Company company = companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return full == null || full == false ?
                companyMapper.companySummaryToDto(company)
                : companyMapper.companyToDto(company);
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
        companyDto.setId(id);
        Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
        if (updatedCompany == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        companyService.delete(id);
    }

    @PostMapping("/{id}/employees")
    public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody HrDto employeeDto) {
        try {
            return companyMapper.companyToDto(
                    companyService.addEmployee(id, employeeMapper.dtoToEmployee(employeeDto))
            );
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/employees/{employeeId}")
    public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId){
        try {
            return companyMapper.companyToDto(
                    companyService.deleteEmployee(id, employeeId)
            );
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/employees")
    public CompanyDto replaceEmployees(@PathVariable long id, @RequestBody List<HrDto> employees) {
        try {
            return companyMapper.companyToDto(
                    companyService.replaceEmployees(id, employeeMapper.dtosToEmployees(employees))
            );
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    /*

    private Map<Long, CompanyDto> companies = new HashMap<>();


//      @GetMapping
//      public List<CompanyDto> getAll() {
//      return companyMapper.companiesToDtos(companyService.findAll());
//      }

    @GetMapping(params = "full=true")
    public List<CompanyDto> getCompanys(){
        return new ArrayList<>(companies.values());
    }
    @GetMapping
    @JsonView(Views.BaseData.class)
    public List<CompanyDto> getCompanysWhitBaseData(@RequestParam(required = false) Boolean full) {
        return new ArrayList<>(companies.values());
    }

//   @GetMapping
//	public List<CompanyDto> getCompanys(@RequestParam(required = false) Boolean full) {
//		if(full != null && full) {
// 		    return new ArrayList<>(companies.values());
//        } else {
//           return companies.values().stream()
//                   .map(mapToCompanyDtoWithEmployees())
//                   .collect(Collectors.toList());}
//	}

	private Function<? super CompanyDto, ? extends CompanyDto> mapToCompanyDtoWithEmployees(){
        return c -> new CompanyDto(c.getId(), c.getCompanyRegistrationNumber(),c.getCompanyName(),c.getCompanyAdress(),null);
    }

    @GetMapping ("/{id}")
    public CompanyDto getById(@PathVariable Long id, @RequestParam(required = false) Boolean full){
        CompanyDto companyDto = finByIdOrThrow(id);
        if(full != null || full) {
            return companyDto;
        } else {
            return mapToCompanyDtoWithEmployees().apply(companyDto);
        }
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        companies.put(companyDto.getId(), companyDto);
        return companyDto;
    }


    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> modyfyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto){
        if (!companies.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        companyDto.setId(id);
        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id){
        companies.remove(id);
    }

    @PostMapping("/{id}/employees")
    public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody HrDto hrDto){
        CompanyDto companyDto = finByIdOrThrow(id);
        companyDto.getEmployee().add(hrDto);
        return companyDto;
    }

    @DeleteMapping("/{id}/employees/{employeeId}")
    public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long emloyeeId){
        CompanyDto companyDto = finByIdOrThrow(id);
        for(Iterator<HrDto> iterator = companyDto.getEmployee().iterator(); iterator.hasNext();){
            HrDto hrDto = iterator.next();
            if (hrDto.getEmployeeId() == emloyeeId) {
                iterator.remove();
                break;
            }
        }
        return companyDto;
    }

    // Egyszerűsítés
    public CompanyDto finByIdOrThrow(long id){
        CompanyDto companyDto = companies.get(id);
        if (companyDto == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return companyDto;
    }


    @PutMapping("/{id}/employees")
    public CompanyDto replaceEmployee(@PathVariable long id, @RequestBody List<HrDto> hrDto){
        CompanyDto companyDto = finByIdOrThrow(id);
        companyDto.setEmployee(hrDto);
        return companyDto;
    }


    @DeleteMapping("/{companyRegistrationNumber}")
    public void deleteCompany(@PathVariable long companyRegistrationNumber) {
        companyService.delete(companyRegistrationNumber);
    }

    @PutMapping("/{companyRegistrationNumber}")
    public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long registrationNumber,
                                                    @RequestBody CompanyDto companyDto) {
        if (companyService.findById(registrationNumber) == null)
            return ResponseEntity.notFound().build();

        companyDto.setCompanyRegistrationNumber(registrationNumber);
        companyService.save(companyMapper.dtoToCompany(companyDto));
        return ResponseEntity.ok(companyDto);
    }

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/payRaise")
    public int getpayRaise(@RequestBody Employee employee){
        return employeeService.getPayRaisePercent(employee);
    }
*/

}