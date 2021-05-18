package com.insignia.customerservice.service;

import com.insignia.customerservice.entity.*;
import com.insignia.customerservice.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    private RestTemplate client;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Autowired
    public void setClient(RestTemplate client) {
        this.client = client;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Optional<Customer> updateCustomer(Customer customer) {
        Optional<Customer> cust = customerRepo.findById(customer.getCustomerId());
        if (cust.isPresent()) {
            Customer c = cust.get();
            c.setCustomerName(customer.getCustomerName());
            c.setCustomerAddress(customer.getCustomerAddress());
        }

        return cust;
    }

    @Override
    @Transactional
    public String placeOrder(CustomerPlaceOrderRequest request) throws MalformedURLException, URISyntaxException {
        String response = "";
        Optional<Customer> cust = customerRepo.findById(request.getCustomerId());

        if (cust.isPresent()) {
            PlaceOrderRequest orderRequest = new PlaceOrderRequest();
            Customer c = cust.get();
            orderRequest.setCustomerId(c.getCustomerId());
            orderRequest.setCustomerName(c.getCustomerName());
            orderRequest.setCustomerAddress(c.getCustomerAddress());
            orderRequest.setProducts(request.getProducts());

            RequestEntity<PlaceOrderRequest> requestEntity = RequestEntity.post(new URL("http://ORDER-SERVICE/orders").toURI()).contentType(MediaType.APPLICATION_JSON).body(orderRequest);
            ResponseEntity<String> stats = client.exchange(requestEntity, String.class);
            return stats.getBody();
        } else
            return response = "Invalid customer";

    }

    public Optional<GetCustomerOrdersResponse> getMyOrders(GetCustomerOrderRequest request) throws MalformedURLException, URISyntaxException {
        RequestEntity<Optional<Customer>> requestEntity = RequestEntity.post(new URL("http://ORDER-SERVICE/orders/myOrders").toURI()).contentType(MediaType.APPLICATION_JSON).body(customerRepo.findById(request.getCustomerId()));
        ResponseEntity<GetCustomerOrdersResponse> response = client.exchange(requestEntity, GetCustomerOrdersResponse.class);
        return Optional.ofNullable(response.getBody());
    }

}
