package com.insignia.customerservice.service;

import com.insignia.customerservice.entity.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Optional<GetCustomerOrdersResponse> getMyOrders(GetCustomerOrderRequest request) throws MalformedURLException, URISyntaxException;

    Optional<Customer> updateCustomer(Customer customer);

    String placeOrder(CustomerPlaceOrderRequest request) throws MalformedURLException, URISyntaxException;
}
