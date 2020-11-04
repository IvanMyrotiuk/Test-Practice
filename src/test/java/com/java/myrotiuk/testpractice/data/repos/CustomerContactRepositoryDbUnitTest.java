package com.java.myrotiuk.testpractice.data.repos;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.java.myrotiuk.testpractice.domain.CustomerContact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Ivan on 04.11.2020. All rights reserved.
 *
 * @Transactional if we do not use transactional
 * and use just SpringBootTest our dataset will be stored in db
 * @SpringBootTest
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
class CustomerContactRepositoryDbUnitTest {

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Test
    @DatabaseSetup(value = "/customer-contact-dataset.xml")
    public void shouldFindByEmail() {
        String email = "dian@gmail.com";

        Optional<CustomerContact> emailActualOptional = customerContactRepository.findByEmail(email);

        CustomerContact emailActual = emailActualOptional.orElse(null);

        assertNotNull(emailActual);
        assertEquals(emailActual.getEmail(), email);

    }

}