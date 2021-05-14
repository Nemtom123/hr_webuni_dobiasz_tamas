package hu.webuni.hr.tamasdobiasz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest
public class CompanyControllerIT {

    private static final Object BASE_URI = "/test";


    @Autowired
    WebTestClient webTestClient;

    @Autowired
    CompanyService companyService;


}