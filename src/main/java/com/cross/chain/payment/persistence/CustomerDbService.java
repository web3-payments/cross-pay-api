package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.CustomerNotFoundException;
import com.cross.chain.payment.model.CustomerDTO;

public interface CustomerDbService {

    CustomerDTO findById(String id) throws CustomerNotFoundException;

    CustomerDTO save(CustomerDTO customerDTO);

}
