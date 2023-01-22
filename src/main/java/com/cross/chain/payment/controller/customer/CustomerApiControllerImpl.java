package com.cross.chain.payment.controller.customer;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.model.Customer;
import com.cross.chain.payment.service.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class CustomerApiControllerImpl implements CustomerApiController {
    @Autowired
    private CustomerService service;

    @Override
    @PostMapping(value = CUSTOMER, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity createCustomer(String address, Customer customer) throws UserNotFoundException {
        service.create(address, customer);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = CUSTOMER, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Customer>> getAllCustomers(String address) throws UserNotFoundException {
        return ResponseEntity.ok(service.getAllByUserAddress(address));
    }
}
