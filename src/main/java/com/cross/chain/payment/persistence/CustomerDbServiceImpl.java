package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.CustomerNotFoundException;
import com.cross.chain.payment.model.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDbServiceImpl implements CustomerDbService {

    private final CustomerRepository repository;

    private final CustomerDbMapper mapper;

    @Override
    public CustomerDTO findById(String id) throws CustomerNotFoundException {
        return mapper.map(repository.findById(id).orElseThrow(CustomerNotFoundException::new));
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = mapper.map(customerDTO);
        return mapper.map(repository.save(customerEntity));
    }
}
