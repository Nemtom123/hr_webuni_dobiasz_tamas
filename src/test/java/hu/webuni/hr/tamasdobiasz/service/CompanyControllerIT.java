package hu.webuni.hr.tamasdobiasz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.tamasdobiasz.dto.CompanyDto;
import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.model.Employee;
import hu.webuni.hr.tamasdobiasz.repository.CompanyRepository;
import hu.webuni.hr.tamasdobiasz.repository.EmployeeRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CompanyControllerIT {

    private static final Object BASE_URI = "/api";

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    InitDbService initDbService;

    private CompanyDto getFull(long registrationNumber) {
        return getCopmanyFull(registrationNumber);
    }

    private long createHrDto(long id, String name, String jobTittle, int salary, LocalDateTime date) {
        employeeService.createHrDto(id,name,jobTittle,salary,date);
        return id;
    }

    @Test /*duplázva-van*/
    void addEmployee() throws Exception {
        Long registrationId = companyRepository.findById(1L).get().getCompanyRegistrationNumber();
        HrDto newHrdto = new HrDto(1L, "Nyerő Jenő", "007", 1000, LocalDateTime.now());
        addNewEmployee(newHrdto, registrationId);
        CompanyDto companyAfter = getCopmanyFull(registrationId);
        assertThat(companyAfter.getEmployee().size()).isEqualTo(1);
        assertThat(companyAfter.getEmployee().get(1).getEmployeeId()).isEqualTo(1);
    }

    @Test/*duplázva-van*/
    void addNewHrEmployee() throws Exception{
        HrDto newHrdto = new HrDto(1L, "Nyerő Jenő", "007", 1000, LocalDateTime.now());
        Long addNewHrEmployee = employeeRepository.findByPositionName("wizzard").get(1).getId();
        CompanyDto companyDto = new CompanyDto(1L,10121L,"Nyerő Kft", "Vizi Dudva u. 12",null);
        assertThat(companyDto.getEmployee().size()).isEqualTo(1);
        assertThat(companyDto.getEmployee().get(1).getEmployeeId()).isEqualTo(1);
    }

    @Test
    void changeAllEmployee() throws Exception {
        long registrationNumber = companyRepository.findWorkByName("Nyerő Jenő").get().getCompanyRegistrationNumber();
        HrDto swappableEmployee = new HrDto(1L, "Nyerő Jenő", "Wizzard", 2000, LocalDateTime.now());
        addNewEmployee(swappableEmployee, registrationNumber);
        List<HrDto> hrDto = new ArrayList<>();
        hrDto.add(new HrDto(2L, "John Doe", "director", 2000, LocalDateTime.now()));
        swapHr(hrDto, registrationNumber);
        CompanyDto companyAfter = getFull(registrationNumber);
        assertThat(companyAfter.getEmployee().size()).isEqualTo(2);
        assertThat(companyAfter.getEmployee().get(0).getJobTitle()).isEqualTo("director");
        assertThat(companyAfter.getEmployee().get(1).getJobTitle()).isEqualTo("wizzard");
    }

    @Test
    void deleteAll() throws Exception {
        long registrationNumber = companyRepository.findWorkByName("wizzard").get().getCompanyRegistrationNumber();
        List<HrDto> hrDto = new ArrayList<>();
        hrDto.add(new HrDto(2L, "John Doe", "director", 2000, LocalDateTime.now()));
        hrDto.add(new HrDto(3L, "Harry Potter", "wizzard", 20000, LocalDateTime.now()));
        swapHr(hrDto, registrationNumber);
        long id = employeeRepository.findByNameStartingWithIgnoreCase("Harry Potter").get(0).getId();
        deleteEmployee(registrationNumber,id);
        CompanyDto companyAfter = getFull(registrationNumber);
        assertThat(companyAfter.getEmployee().size()).isEqualTo(1);
        assertThat(companyAfter.getEmployee().get(0).getJobTitle()).isEqualTo("wizzard");
    }

    @Test
    void testFindHrdtos(){
      long hrEmployee1 = createHrDto(0L, "John Doe", "director", 5000, LocalDateTime.now());
      long hrEmployee2 = createHrDto(1L, "John Emili", "painter", 1000, LocalDateTime.now());
      HrDto hrDto = new HrDto();
      hrDto.setEmployeeId(1L);
      hrDto.setSalary(3500);
      hrDto.setJobTitle("Nyerő Jenő");
      hrDto.setDateOfStartWork(LocalDateTime.now());
      hrDto.setCompanyName("Nyerő Kft");
      List<Employee> foundHrDto = this.companyService.findHrdtoExamle(hrDto);
      assertThat(foundHrDto.stream()
    		  .map(Employee::getId)
    		  .collect(Collectors.toList()))
      .containsExactly(hrEmployee1, hrEmployee2);
    }

    @BeforeEach
    public void prepareDB() {
        clearDB();
        initDB();
    }

    private void initDB() {
        extracted();
    }

    private void extracted() {
        createCompany(new CompanyDto(1L, 121234L, "Nyerő-Jenő KFT", "Wien Spring Strasse 13", null));
    }

    private void clearDB() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }


    private void createCompany(CompanyDto newCompany) {
        webTestClient
                .post()
                .uri(BASE_URI + "/companies")
                .bodyValue(newCompany)
                .exchange()
                .expectStatus()
                .isOk();
    }



    private void addNewEmployee(HrDto newEmployee, Long registrationId) {
        webTestClient
                .post()
                .uri(BASE_URI + "/companies/" + registrationId + "/employee")
                .bodyValue(newEmployee)
                .exchange()
                .expectStatus()
                .isOk();
    }
    

    private void swapHr(List<HrDto> hrDto, long registrationNumber) {
        webTestClient
                .put()
                .uri(BASE_URI + "/companies/" + registrationNumber + "/employee")
                .bodyValue(hrDto)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private void deleteEmployee(long registrationNumber, long id) {
        webTestClient
                .delete()
                .uri(BASE_URI + "/companies/" + registrationNumber + "/employee/" + id)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private CompanyDto getCopmanyFull(long registrationNumber) {
        return webTestClient
                .get()
                .uri(BASE_URI + "/companies/" + registrationNumber + "/full=true?")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CompanyDto.class)
                .returnResult()
                .getResponseBody();
    }

}
