package com.cross.chain.payment.repository;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRequestRepository extends MongoRepository<PaymentRequestDetails, String> {

    Optional<PaymentRequestDetails> findByHash(String hash);

    List<PaymentRequestDetails> findByUserAddress(String address);

}
