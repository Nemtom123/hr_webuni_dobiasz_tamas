package hu.webuni.hr.tamasdobiasz.service;


import hu.webuni.hr.tamasdobiasz.dto.HrDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

    private static final String BASE_URI = "/api";

    @Autowired
    WebTestClient webTestClient;


    @Test
    void testThatNewValidEmployeeCanBeSaved() throws Exception {

        List<HrDto> employeesBefore = getAllEmployees();

        HrDto newEmployee = new HrDto(0L, "Harry Potter", "Wizzard", 10, LocalDateTime.of(2019, 01, 01, 8, 0, 0));
        saveEmployee(newEmployee).expectStatus().isOk();

        List<HrDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter.size()).isEqualTo(employeesBefore.size() + 1);

        assertThat(employeesAfter.get(employeesAfter.size()-1))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newEmployee);
    }

    @Test
    void testThatNewInvalidEmployeeCannotBeSaved() throws Exception {
        List<HrDto> employeesBefore = getAllEmployees();

        HrDto newEmployee = newInvalidEmployee();
        saveEmployee(newEmployee).expectStatus().isBadRequest();

        List<HrDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
    }

    private HrDto newInvalidEmployee() {
        return new HrDto(0L, "", "Wizzard", 20, LocalDateTime.of(2019, 01, 01, 8, 0, 0));
    }

    @Test
    void testThatEmployeeCanBeUpdatedWithValidFields() throws Exception {

        HrDto newEmployee = new HrDto(0L, "Harry Potter", "wizzard", 200000, LocalDateTime.of(2019, 01, 01, 8, 0, 0));
        HrDto savedEmployee = saveEmployee(newEmployee)
                .expectStatus().isOk()
                .expectBody(HrDto.class).returnResult().getResponseBody();

        List<HrDto> employeesBefore = getAllEmployees();
        savedEmployee.setWorkName("modified");
        modifyEmployee(savedEmployee).expectStatus().isOk();

        List<HrDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
        assertThat(employeesAfter.get(employeesAfter.size()-1))
                .usingRecursiveComparison()
                .isEqualTo(savedEmployee);
    }

    @Test
    void testThatEmployeeCannotBeUpdatedWithInvalidFields() throws Exception {
        HrDto newEmployee = new HrDto(0L, "Harry Potter", "Wizzard", 200000, LocalDateTime.of(2019, 01, 01, 8, 0, 0));
        HrDto savedEmployee = saveEmployee(newEmployee)
                .expectStatus().isOk()
                .expectBody(HrDto.class).returnResult().getResponseBody();

        List<HrDto> employeesBefore = getAllEmployees();
        HrDto invalidEmployee = newInvalidEmployee();
        invalidEmployee.setEmployeeId(savedEmployee.getEmployeeId());
        modifyEmployee(invalidEmployee).expectStatus().isBadRequest();

        List<HrDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
        assertThat(employeesAfter.get(employeesAfter.size()-1))
                .usingRecursiveComparison()
                .isEqualTo(savedEmployee);
    }

    private ResponseSpec modifyEmployee(HrDto employee) {
        String path = BASE_URI + "/" + employee.getEmployeeId();
        return
                webTestClient
                        .put()
                        .uri(path)
                        .bodyValue(employee)
                        .exchange();
    }

    private ResponseSpec saveEmployee(HrDto newEmployee) {
        return
                webTestClient
                        .post()
                        .uri(BASE_URI)
                        .bodyValue(newEmployee)
                        .exchange();
    }

    private List<HrDto> getAllEmployees() {
        List<HrDto> responseList =
                webTestClient
                        .get()
                        .uri(BASE_URI)
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBodyList(HrDto.class)
                        .returnResult()
                        .getResponseBody();
        Collections.sort(responseList, (a1, a2) -> Long.compare(a1.getEmployeeId(), a2.getEmployeeId()));
        return responseList;
    }

}

