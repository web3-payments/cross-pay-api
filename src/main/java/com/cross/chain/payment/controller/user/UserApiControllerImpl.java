package com.cross.chain.payment.controller.user;

import com.cross.chain.payment.model.WalletRequest;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.model.UserRequest;
import com.cross.chain.payment.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class UserApiControllerImpl implements UserApiController {

    @Autowired
    private UserService userService;

    @Override
    @PostMapping(value = USER, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE} )
    public ResponseEntity<UserRequest> createUser(@Valid @RequestBody UserRequest body) {
        return ResponseEntity.ok(userService.save(body));
    }

    @Override
    @PutMapping(value = USER_ADDRESS, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE} )
    public ResponseEntity<UserRequest> updateUser(@PathVariable(value = "address") String address, @Valid @RequestBody UserRequest body) throws UserNotFoundException {
        return ResponseEntity.ok(userService.update(body));
    }

    @Override
    @PatchMapping(value = USER_ADDRESS, produces = {APPLICATION_JSON_VALUE}, consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadImage(String address, MultipartFile file) throws UserNotFoundException, IOException {
        userService.uploadImage(address, file);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = USER_ADDRESS, produces = {APPLICATION_JSON_VALUE} )
    public ResponseEntity<UserRequest> retrieveUser(@PathVariable(value = "address") String address) throws UserNotFoundException {
        return ResponseEntity.ok(userService.retrieveUser(address));
    }

    @Override
    @GetMapping(value = USER_WALLETS, produces = {APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<WalletRequest>> retrieveUserWallets(String address) throws UserNotFoundException {
        return ResponseEntity.ok(userService.retrieveUserWallets(address));
    }

}
