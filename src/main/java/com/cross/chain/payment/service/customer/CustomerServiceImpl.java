package com.cross.chain.payment.service.customer;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.CustomerMapper;
import com.cross.chain.payment.model.Customer;
import com.cross.chain.payment.model.CustomerDTO;
import com.cross.chain.payment.model.UserDTO;
import com.cross.chain.payment.persistence.CustomerDbService;
import com.cross.chain.payment.persistence.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserDbService userDbService;
    private final CustomerDbService customerDbService;
    private final CustomerMapper customerMapper;

    @Override
    public Customer create(String address, Customer customer) throws UserNotFoundException {
        CustomerDTO customerDTO = customerDbService.save(customerMapper.map(customer));
        UserDTO user = userDbService.findBySignerAddress(address);
        user.getCustomers().add(customerDTO);
        userDbService.save(user);
        return customerMapper.map(customerDTO);
    }

    @Override
    public List<Customer> getAllByUserAddress(String address) throws UserNotFoundException {
        return userDbService.findBySignerAddress(address)
                .getCustomers()
                .stream()
                .map(customerMapper::map)
                .collect(Collectors.toList());
    }
}
