package com.insignia.customerservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FindCustomerOrderRequest {

    private Integer customerId;
    private String customerName;
    private String customerAddress;
}
