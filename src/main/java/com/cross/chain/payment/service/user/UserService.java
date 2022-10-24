package com.cross.chain.payment.service.user;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.dto.UserRequest;

public interface UserService {

    UserRequest registerUser(UserRequest userRequest);

    UserRequest retrieveUser(String address) throws UserNotFoundException;
}
