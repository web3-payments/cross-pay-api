package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.model.UserDTO;

public interface UserDbService {
    UserDTO findBySignerAddress(String address) throws UserNotFoundException;

    UserDTO save(UserDTO user);
}