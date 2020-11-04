package com.java.myrotiuk.testpractice.service;

import com.java.myrotiuk.testpractice.data.repos.CustomerContactRepository;
import com.java.myrotiuk.testpractice.domain.CustomerContact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContactsManagementService {

    private final CustomerContactRepository customerContactRepository;

    public CustomerContact add(CustomerContact aContact) {

        if (Objects.isNull(aContact.getLastName())) {
            throw new IllegalArgumentException("There is no last name");
        }

        return customerContactRepository.save(aContact);
    }
	
	/*
	public CustomerContact addContactOccasion(CustomerContact aContact, ContactImportantOccasion anOccasion) {
		CustomerContact newContact = null;
		
		return newContact;	
	}
	*/
}
