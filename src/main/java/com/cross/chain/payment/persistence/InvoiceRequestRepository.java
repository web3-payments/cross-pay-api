package com.cross.chain.payment.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface InvoiceRequestRepository extends MongoRepository<InvoiceRequestEntity, String> {

    Optional<InvoiceRequestEntity> findByHash(String hash);
    List<InvoiceRequestEntity> findByUserAddress(String address);

}