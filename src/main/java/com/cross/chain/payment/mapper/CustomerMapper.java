package com.cross.chain.payment.mapper;

import com.cross.chain.payment.model.CustomerDTO;
import com.cross.chain.payment.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO map(Customer customer);
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    Customer map(CustomerDTO customerDTO);
}