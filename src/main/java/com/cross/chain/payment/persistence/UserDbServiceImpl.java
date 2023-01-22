package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDbServiceImpl implements UserDbService {
    private final UserRepository repository;
    private final UserDbMapper mapper;
    @Override
    public UserDTO findBySignerAddress(String address) throws UserNotFoundException {
        return mapper.map(repository.findBySignerAddress(address).orElseThrow(UserNotFoundException::new));
    }
    @Override
    public UserDTO save(UserDTO user) {
        UserEntity userEntity = mapper.map(user);
        return mapper.map(repository.save(userEntity));
    }
}
