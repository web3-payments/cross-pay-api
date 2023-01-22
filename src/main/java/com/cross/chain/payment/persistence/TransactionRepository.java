package com.cross.chain.payment.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

}
