package com.java.myrotiuk.testpractice.service.integration;

import com.java.myrotiuk.testpractice.domain.CustomerContact;
import com.java.myrotiuk.testpractice.service.ContactsManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Ivan on 01.11.2020. All rights reserved.
 * Integration test from Service layer up to DB
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)//no one controller components will be loaded
@Sql(scripts = "/scripts/customer-contact-data-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
        config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class ContactsManagementServiceIntegrationTest {

    @Autowired
    private ContactsManagementService contactsManagementService;

    @Test
    public void testAddContact() {

        CustomerContact customerContact = CustomerContact.builder()
                .firstName("Jenny")
                .lastName("Johnson-Test")
                .build();

        CustomerContact actual = contactsManagementService.add(customerContact);

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals("Jenny", actual.getFirstName());
    }

}