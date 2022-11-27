package com.cross.chain.payment.service.user;

import com.cross.chain.payment.domain.User;
import com.cross.chain.payment.dto.UserRequest;
import com.cross.chain.payment.dto.WalletRequest;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.UserMapper;
import com.cross.chain.payment.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        User userFound = repository.findBySignerAddress(userRequest.getSignerAddress()).orElseThrow(UserNotFoundException::new);
        userFound.setCompanyName(userRequest.getCompanyName());
        userFound.setFirstName(userRequest.getFirstName());
        userFound.setLastName(userRequest.getLastName());
        userFound.setEmail(userRequest.getEmail());
        userFound.setPhone(userRequest.getPhone());
        return mapper.map(repository.save(userFound));
    }

    @Override
    public UserRequest uploadImage(String address, MultipartFile file) throws IOException, UserNotFoundException {
        Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        User user = repository.findBySignerAddress(address).orElseThrow(UserNotFoundException::new);
        user.setImage(image);
        return mapper.map(repository.save(user));
    }

}
