package com.java.myrotiuk.testpractice.data.repos;

import com.java.myrotiuk.testpractice.domain.CustomerContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

    Optional<CustomerContact> findByEmail(String email);

}
