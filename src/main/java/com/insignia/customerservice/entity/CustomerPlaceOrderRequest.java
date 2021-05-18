package com.insignia.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPlaceOrderRequest {

    private int customerId;

    @NotNull(message = "email cannot be empty")
    @Size(min = 1,message = "Please select atleast one product to be able to place order")
    private List<Product> products;
}
