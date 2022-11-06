package com.cross.chain.payment.service.user;

import com.cross.chain.payment.domain.User;
import com.cross.chain.payment.dto.UserRequest;
import com.cross.chain.payment.dto.WalletRequest;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.UserMapper;
import com.cross.chain.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Override
    public UserRequest save(UserRequest userRequest) {
        return mapper.map(repository.save(mapper.map(userRequest)));
    }

    @Override
    public UserRequest retrieveUser(String address) throws UserNotFoundException {
        return mapper.map(repository.findBySignerAddress(address).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<WalletRequest> retrieveUserWallets(String address) throws UserNotFoundException {
        return new ArrayList<>(retrieveUser(address).getWallets());
    }

    @Override
    public UserRequest update(UserRequest userRequest) throws UserNotFoundException {
        User user = mapper.map(userRequest);
        user.setId(repository.findBySignerAddress(userRequest.getSignerAddress())
                .map(User::getId)
                .orElseThrow(UserNotFoundException::new));
        return mapper.map(repository.save(user));
    }

}
