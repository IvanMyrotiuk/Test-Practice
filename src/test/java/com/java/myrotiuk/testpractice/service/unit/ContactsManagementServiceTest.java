package com.java.myrotiuk.testpractice.service.unit;

import com.java.myrotiuk.testpractice.data.repos.CustomerContactRepository;
import com.java.myrotiuk.testpractice.domain.CustomerContact;
import com.java.myrotiuk.testpractice.service.ContactsManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Ivan on 01.11.2020. All rights reserved.
 */
@ExtendWith(value = MockitoExtension.class)
public class ContactsManagementServiceTest {

    @Mock
    private CustomerContactRepository customerContactRepository;

    @InjectMocks
    private ContactsManagementService contactsManagementService;

    @Test
    public void shouldSaveCustomerContact() {

        CustomerContact customerContact = CustomerContact.builder()
                .lastName("Julia")
                .firstName("Jullianna")
                .build();

        when(customerContactRepository.save(any(CustomerContact.class))).thenReturn(customerContact);

        CustomerContact actual = contactsManagementService.add(customerContact);

        assertEquals(customerContact.getLastName(), actual.getLastName());
        assertEquals(customerContact.getFirstName(), actual.getFirstName());
    }

}