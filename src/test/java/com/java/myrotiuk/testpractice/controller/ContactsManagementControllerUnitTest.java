package com.java.myrotiuk.testpractice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.myrotiuk.testpractice.domain.CustomerContact;
import com.java.myrotiuk.testpractice.dto.ContactCustomerDto;
import com.java.myrotiuk.testpractice.service.ContactsManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ivan on 03.11.2020. All rights reserved.
 */
@WebMvcTest(ContactsManagementController.class)
class ContactsManagementControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsManagementService contactsManagementService;

    @Autowired
    private ContactsManagementController contactsManagementController;

    @Test
    public void shouldReturnSuccess() throws Exception {

        CustomerContact customerContact = CustomerContact.builder()
                .firstName("Lilu")
                .lastName("Simpson")
                .build();

        when(contactsManagementService.add(any(CustomerContact.class)))
                .thenReturn(customerContact);

        ContactCustomerDto customerContactToAdd = ContactCustomerDto.builder()
                .contactCustomerFirstName("Lilu")
                .contactCustomerLastName("Simpson")
                .build();


        mockMvc.perform(post("/api/contacts")
                .content(new ObjectMapper().writeValueAsString(customerContactToAdd))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.contactCustomerId").exists())
                .andExpect(jsonPath("$.contactCustomerFirstName").value("Lilu"))
                .andExpect(jsonPath("$.contactCustomerLastName").value("Simpson"));
    }


}