package com.java.myrotiuk.testpractice.data.repos;

import com.java.myrotiuk.testpractice.domain.CustomerContact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Ivan on 04.11.2020. All rights reserved.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerContactRepositoryUnitTest {

    @Autowired
    private CustomerContactRepository customerContactRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldFindByEmail() {
        String email = "john@gmail.com";

        CustomerContact customerContact = CustomerContact.builder()
                .email(email)
                .build();

        testEntityManager.persist(customerContact);

        Optional<CustomerContact> emailActualOptional = customerContactRepository.findByEmail(email);

        CustomerContact emailActual = emailActualOptional.orElse(null);

        assertNotNull(emailActual);
        assertEquals(emailActual.getEmail(), email);

    }

}