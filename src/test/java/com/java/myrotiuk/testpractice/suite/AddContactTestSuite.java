package com.java.myrotiuk.testpractice.suite;

import com.java.myrotiuk.testpractice.data.repos.CustomerContactRepositoryUnitTest;
import com.java.myrotiuk.testpractice.service.integration.ContactsManagementIntegrationTest;
import com.java.myrotiuk.testpractice.service.integration.ContactsManagementServiceIntegrationTest;
import org.junit.runners.Suite;

/**
 * Created by Ivan on 04.11.2020. All rights reserved.
 * Create Suites for our tests
 */
@Suite.SuiteClasses({ContactsManagementIntegrationTest.class, ContactsManagementServiceIntegrationTest.class,
        CustomerContactRepositoryUnitTest.class})
public class AddContactTestSuite {

}
