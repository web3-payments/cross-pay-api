package com.cross.chain.payment.service.user;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.UserMapper;
import com.cross.chain.payment.dto.UserRequest;
import com.cross.chain.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Override
    public UserRequest registerUser(UserRequest userRequest) {
        return mapper.map(repository.save(mapper.map(userRequest)));
    }

    @Override
    public UserRequest retrieveUser(String address) throws UserNotFoundException {
        return mapper.map(repository.findBySignerAddress(address).orElseThrow(UserNotFoundException::new));
    }

}
