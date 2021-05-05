package hu.webuni.hr.tamasdobiasz.service;

import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

    private static final String BASE_URI = "/api/employees";

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    HrDto hrDto;

    @Test
    void testThatCreatedEmployeeIsListed() throws Exception {
        List<HrDto> employeesBefore = getAllEmployees();
        HrDto newEmployee = new HrDto(1L, "John Doe", "wizzard", 500_000, LocalDateTime.of(2021,10,10,10,10));
        createEmployee(newEmployee);
        List<HrDto> employeesAfter = getAllEmployees();
        assertThat(employeesAfter.subList(0, employeesBefore.size())).usingFieldByFieldElementComparator().containsExactlyElementsOf(employeesBefore);
        assertThat(employeesAfter.get(employeesAfter.size() - 1)).usingRecursiveComparison().isEqualTo(newEmployee);
    }

    @Test
    void testThatInvalidSalaryCreatedEmployeeIsNotListed() throws Exception {
        List<HrDto> employeesBefore = getAllEmployees();
        HrDto newEmployee = new HrDto(1L, "John Doe", "wizzard", 500_000, LocalDateTime.of(2021,10,10,10,10));
        createEmployee(newEmployee, HttpStatus.BAD_REQUEST);

        List<HrDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).usingFieldByFieldElementComparator().containsExactlyElementsOf(employeesBefore);
    }

    @Test
    void testThatModifiedEmployeeIsListed() throws Exception {
        HrDto newEmployee = new HrDto(1L, "John Doe", "wizzard", 500_000, LocalDateTime.of(2021,10,10,10,10));
        createEmployee(hrDto);
        HrDto modifiedEmployee = new HrDto(1L, "John Doe", "wizzard", 500_000, LocalDateTime.of(2021,10,10,10,10));
        modifyEmployee(modifiedEmployee);
        HrDto employeeAfterModification = getEmployee(modifiedEmployee.getEmployeeId());
        assertThat(employeeAfterModification).usingRecursiveComparison().isEqualTo(modifiedEmployee);
    }

    @Test
    void testThatInvalidSalaryModifiedEmployeeIsNotListed() throws Exception {
        HrDto originalEmployee = new HrDto(1L, "John Doe", "wizzard", 500_000, LocalDateTime.of(2021,10,10,10,10));
        createEmployee(originalEmployee);
        HrDto modifiedEmployee = new HrDto(1L, "John Doe", "wizzard", 500_000, LocalDateTime.of(2021,10,10,10,10));
        modifyEmployee(modifiedEmployee, HttpStatus.BAD_REQUEST);
        HrDto employeeAfterModification = getEmployee(modifiedEmployee.getEmployeeId());
        assertThat(employeeAfterModification).usingRecursiveComparison().isEqualTo(originalEmployee);
    }

    private void createEmployee(HrDto newEmployee) {
        createEmployee(newEmployee, HttpStatus.OK);
    }

    private void createEmployee(HrDto newEmployee, HttpStatus status) {
        webTestClient
                .post()
                .uri(BASE_URI)
                .bodyValue(newEmployee)
                .exchange()
                .expectStatus()
                .isEqualTo(status);
    }

    private void modifyEmployee(HrDto modifiedEmployee) {
        modifyEmployee(modifiedEmployee, HttpStatus.OK);
    }

    private void modifyEmployee(HrDto modifiedEmployee, HttpStatus status) {
        webTestClient
                .put()
                .uri(BASE_URI + "/" + modifiedEmployee.getEmployeeId())
                .bodyValue(modifiedEmployee)
                .exchange()
                .expectStatus()
                .isEqualTo(status);
    }

    private List<HrDto> getAllEmployees() {
        List<HrDto> responseList = webTestClient.get().uri(BASE_URI).exchange().expectStatus().isOk()
                .expectBodyList(HrDto.class).returnResult().getResponseBody();
        Collections.sort(responseList, (e1, e2) -> Long.compare(e1.getEmployeeId(), e2.getEmployeeId()));
        return responseList;
    }

    private HrDto getEmployee(long id) {
        HrDto employee = webTestClient.get().uri(BASE_URI + "/" + id).exchange().expectStatus().isOk()
                .expectBody(HrDto.class).returnResult().getResponseBody();
        return employee;
    }

}
