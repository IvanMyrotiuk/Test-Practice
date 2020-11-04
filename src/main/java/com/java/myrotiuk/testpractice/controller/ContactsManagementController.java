package com.java.myrotiuk.testpractice.controller;

import com.java.myrotiuk.testpractice.domain.CustomerContact;
import com.java.myrotiuk.testpractice.dto.ContactCustomerDto;
import com.java.myrotiuk.testpractice.service.ContactsManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactsManagementController {

    private final ContactsManagementService contactsManagementService;

    @PostMapping
    public ResponseEntity<ContactCustomerDto> createContact(@RequestBody ContactCustomerDto contactCustomerDto) {
        CustomerContact customerContact = CustomerContact.builder()
                .firstName(contactCustomerDto.getContactCustomerFirstName())
                .lastName(contactCustomerDto.getContactCustomerLastName())
                .build();
        CustomerContact savedCustomerContact = contactsManagementService.add(customerContact);

        return ResponseEntity.status(HttpStatus.CREATED).body(ContactCustomerDto.builder()
                .contactCustomerId(savedCustomerContact.getId())
                .contactCustomerFirstName(savedCustomerContact.getFirstName())
                .contactCustomerLastName(savedCustomerContact.getLastName())
                .build());
    }

}
