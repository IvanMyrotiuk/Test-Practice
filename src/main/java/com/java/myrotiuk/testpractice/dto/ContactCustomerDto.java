package com.java.myrotiuk.testpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Ivan on 02.11.2020. All rights reserved.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContactCustomerDto {

    private Long contactCustomerId;
    private String contactCustomerFirstName;
    private String contactCustomerLastName;

}
