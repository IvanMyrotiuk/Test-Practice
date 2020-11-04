package com.java.myrotiuk.testpractice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@Table(name = "delivery_address")
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zipCode;

}
