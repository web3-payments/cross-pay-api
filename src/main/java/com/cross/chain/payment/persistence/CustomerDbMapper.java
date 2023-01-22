package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.CustomerDTO;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface CustomerDbMapper {

    CustomerDTO map(CustomerEntity customer);
    CustomerEntity map(CustomerDTO customer);

}
