package com.cross.chain.payment.mapper;

import com.cross.chain.payment.model.CustomerDTO;
import com.cross.chain.payment.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO map(Customer customer);
    Customer map(CustomerDTO customerDTO);
}