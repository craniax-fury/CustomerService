package com.insignia.customerservice.controller;

import com.insignia.customerservice.entity.*;
import com.insignia.customerservice.service.CustomerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "")
    public List<Customer> getAllCustomer(){
            return customerService.getAllCustomers();
    }

    @PostMapping(value="")
    public Customer createCustomer(@RequestBody Customer customer ){
        return customerService.saveCustomer(customer);
    }

    @PutMapping(value = "")
    public Optional<Customer> updateCustomer(@RequestBody() Customer customer){
        return customerService.updateCustomer(customer);
    }
    @PostMapping(value="placeOrder")
    public String placeOrder(@Valid @RequestBody CustomerPlaceOrderRequest request){
        try {
            return customerService.placeOrder(request);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return "Failed";
    }

    @PostMapping(value="orders")
    public Optional<GetCustomerOrdersResponse> getMyOrders(@Valid @RequestBody GetCustomerOrderRequest request){
        try {
            return customerService.getMyOrders(request);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }
}
