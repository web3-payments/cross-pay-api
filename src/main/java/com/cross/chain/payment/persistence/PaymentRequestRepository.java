package com.cross.chain.payment.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface PaymentRequestRepository extends MongoRepository<PaymentRequestEntity, String> {

    Optional<PaymentRequestEntity> findByHash(String hash);
    List<PaymentRequestEntity> findByUserAddress(String address);

}