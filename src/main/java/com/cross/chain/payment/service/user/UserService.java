package com.cross.chain.payment.service.user;

import com.cross.chain.payment.model.WalletRequest;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.model.UserRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserRequest save(UserRequest userRequest);

    UserRequest retrieveUser(String address) throws UserNotFoundException;

    List<WalletRequest> retrieveUserWallets(String address) throws UserNotFoundException;

    UserRequest update(UserRequest userRequest) throws UserNotFoundException;

    UserRequest uploadImage(String address, MultipartFile file) throws IOException, UserNotFoundException;
}
