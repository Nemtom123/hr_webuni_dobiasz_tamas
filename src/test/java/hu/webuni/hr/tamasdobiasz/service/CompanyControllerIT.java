package hu.webuni.hr.tamasdobiasz.service;

import hu.webuni.hr.tamasdobiasz.dto.CompanyDto;
import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import hu.webuni.hr.tamasdobiasz.repository.CompanyRepository;
import hu.webuni.hr.tamasdobiasz.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CompanyControllerIT {

    private static final Object BASE_URI = "/test";
    @Autowired
    CompanyService companyService;


    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    HrDto hrDto;

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    InitDbService initDbService;


    @Test
    void testThatCreatedAirportIsTested() throws Exception {
        List<HrDto> airportsBefore = getAll();

        HrDto newHrDto = new HrDto(1L, "Nyerő Jenő", "007", 1000, LocalDateTime.now());
        createHrDto(newAirport);

        List<AirportDto> airportsAfter = getAllAirports();

        assertThat(airportsAfter.subList(0,airportsBefore.size())).usingFieldByFieldElementComparator().containsExactlyElementsOf(airportsBefore);
        assertThat(airportsAfter.get(airportsAfter.size()-1)).usingRecursiveComparison().isEqualTo(newAirport);

    }



    @Test
    void addEmployee() throws Exception {
        HrDto newHrdto = new HrDto(1L, "Nyerő Jenő", "007", 1000, LocalDateTime.now());
        Long registrationId = companyRepository.findById(1L).get().getCompanyRegistrationNumber();
        addNewEmployee(newHrdto, registrationId);
        CompanyDto companyAfter = getASpecificCopmanyFull(registrationId);
        assertThat(companyAfter.getEmployee().size()).isEqualTo(1);
        assertThat(companyAfter.getEmployee().get(1).getEmployeeId()).isEqualTo(1);
    }

    private void addNewEmployee(HrDto newEmployee, Long registrationId) {
    }

    @Test
    void changeAllEmployee() throws Exception {
        long registrationNumber = companyRepository.findWorkByName("Nyerő Jenő").get().getCompanyRegistrationNumber();
        HrDto swappableEmployee = new HrDto(1L, "Nyerő Jenő", "Wizzard", 2000, LocalDateTime.now());
        addNewEmployee(swappableEmployee, registrationNumber);
        List<HrDto> hrDto = new ArrayList<>();
        hrDto.add(new HrDto(2L, "John Doe", "director", 2000, LocalDateTime.now()));
        hrDto.add(new HrDto(3L, "Harry Potter", "wizzard", 20000, LocalDateTime.now()));
        swapEmployees(hrDto, registrationNumber);
        CompanyDto companyAfter = getASpecificCopmanyFull(registrationNumber);
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
        swapEmployees(hrDto, registrationNumber);
        long id = employeeRepository.findByNameStartingWithIgnoreCase("Harry Potter").get(0).getId();
        deleteEmployee(registrationNumber,id);
        CompanyDto companyAfter = getASpecificCopmanyFull(registrationNumber);
        assertThat(companyAfter.getEmployee().size()).isEqualTo(1);
        assertThat(companyAfter.getEmployee().get(0).getJobTitle()).isEqualTo("wizzard");
    }

    @Test
    void testFindHrdtosExanple(){
      long hrEmployee1 = createHrDto(0L, "John Doe", "director", 5000, LocalDateTime.now());
      long hrEmployee2 = createHrDto(1L, "John Emili", "painter", 1000, LocalDateTime.now());
      long hrEmployee3 = createHrDto(5L, "John Wick", "developer", 2500, LocalDateTime.now());
      long hrEmployee4 = createHrDto(6L, "John Joe", "actor", 3800, LocalDateTime.now());
      createHrDto(8L, "John Malkovics", "actor", 6800, LocalDateTime.now());
      HrDto example = new HrDto();
      example.setEmployeeId(1L);
      example.setSalary(3500);
      example.setJobTitle("Nyerő Jenő");
      example.setDateOfStartWork(LocalDateTime.now());
      example.setCompanyName("Nyerő Kft");
      List<HrDto> foundHrDto = this.companyService.findHrdtoExamle(example);
      assertThat(foundHrDto.stream().map(hu.webuni.hr.tamasdobiasz.dto.HrDto::getEmployeeId).collect(Collectors.toList())).containsExactly(hrEmployee1, hrEmployee2);
    }

    private long createHrDto(long id, String name, String jobTittle, int salary, LocalDateTime date) {
        employeeService.createHrDto(id,name,jobTittle,salary,date);
        return id;
    }

    @BeforeEach
    public void prepareDB() {
        clearDB();
        initDB();
    }

    private void initDB() {
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



    private void addEmployee(HrDto newHrDto, long registrationNumber) {
        webTestClient
                .post()
                .uri(BASE_URI + "/companies/" + registrationNumber + "/employee")
                .bodyValue(newHrDto)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private void swapEmployees(List<HrDto> hrDto, long registrationNumber) {
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

    private CompanyDto getASpecificCopmanyFull(long registrationNumber) {
        return webTestClient
                .get()
                .uri(BASE_URI + "/companies/" + registrationNumber + "/?full=true")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CompanyDto.class)
                .returnResult()
                .getResponseBody();
    }

}
