package com.java.myrotiuk.testpractice.service.integration;

import com.java.myrotiuk.testpractice.dto.ContactCustomerDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Ivan on 02.11.2020. All rights reserved.
 * Test from controller to DB
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/scripts/customer-contact-data-delete2.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
        config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class ContactsManagementIntegrationTest {

    @LocalServerPort
    private String port;

    @Test
    public void shouldReturnSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("email", "first.last@example.com");

//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ContactCustomerDto customerDto = ContactCustomerDto.builder()
                .contactCustomerFirstName("Juann")
                .contactCustomerLastName("Jamess-Test")
                .build();

        HttpEntity<ContactCustomerDto> request = new HttpEntity<>(customerDto, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ContactCustomerDto> response = restTemplate.postForEntity("http://localhost:" + port + "/api/contacts", request, ContactCustomerDto.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        ContactCustomerDto actual = response.getBody();
        assertNotNull(actual.getContactCustomerId());
        assertEquals("Jamess-Test", actual.getContactCustomerLastName());
    }

    @Test
    @Disabled
    //fix it for internal server error
    public void shouldFailed() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ContactCustomerDto customerDto = ContactCustomerDto.builder()
                .contactCustomerFirstName("Juann")
                .build();

        HttpEntity<ContactCustomerDto> request = new HttpEntity<>(customerDto, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.postForEntity("http://localhost:" + port + "/api/contacts", request, String.class);
        assertTrue(response.getStatusCode().isError());
    }


}
