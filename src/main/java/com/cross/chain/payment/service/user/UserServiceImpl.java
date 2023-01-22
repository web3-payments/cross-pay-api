package com.cross.chain.payment.service.user;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.UserMapper;
import com.cross.chain.payment.model.UserDTO;
import com.cross.chain.payment.model.UserRequest;
import com.cross.chain.payment.model.WalletRequest;
import com.cross.chain.payment.persistence.UserDbService;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDbService userDbService;

    private final UserMapper mapper;

    @Override
    public UserRequest save(UserRequest userRequest) {
        return mapper.map(userDbService.save(mapper.map(userRequest)));
    }

    @Override
    public UserRequest retrieveUser(String address) throws UserNotFoundException {
        return mapper.map(userDbService.findBySignerAddress(address));
    }

    @Override
    public List<WalletRequest> retrieveUserWallets(String address) throws UserNotFoundException {
        return new ArrayList<>(retrieveUser(address).getWallets());
    }

    @Override
    public UserRequest update(UserRequest userRequest) throws UserNotFoundException {
        UserDTO userFound = userDbService.findBySignerAddress(userRequest.getSignerAddress());
        userFound.setCompanyName(userRequest.getCompanyName());
        userFound.setFirstName(userRequest.getFirstName());
        userFound.setLastName(userRequest.getLastName());
        userFound.setEmail(userRequest.getEmail());
        userFound.setPhone(userRequest.getPhone());
        return mapper.map(userDbService.save(userFound));
    }

    @Override
    public UserRequest uploadImage(String address, MultipartFile file) throws IOException, UserNotFoundException {
        Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        UserDTO user = userDbService.findBySignerAddress(address);
        user.setImage(image);
        return mapper.map(userDbService.save(user));
    }

}
