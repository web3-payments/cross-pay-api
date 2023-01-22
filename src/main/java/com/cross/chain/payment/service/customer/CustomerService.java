package com.cross.chain.payment.service.customer;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer create(String address, Customer customer) throws UserNotFoundException;
    List<Customer> getAllByUserAddress(String address) throws UserNotFoundException;

}
