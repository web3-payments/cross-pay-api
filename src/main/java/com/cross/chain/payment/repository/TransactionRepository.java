package com.cross.chain.payment.repository;

import com.cross.chain.payment.domain.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
