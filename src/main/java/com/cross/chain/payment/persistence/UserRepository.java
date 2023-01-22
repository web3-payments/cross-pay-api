package com.cross.chain.payment.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository  extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findBySignerAddress(String address);

}
