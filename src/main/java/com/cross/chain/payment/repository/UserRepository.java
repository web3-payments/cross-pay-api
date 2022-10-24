package com.cross.chain.payment.repository;

import com.cross.chain.payment.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {

    Optional<User> findBySignerAddress(String address);

}
