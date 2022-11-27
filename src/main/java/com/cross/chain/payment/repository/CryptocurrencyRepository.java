package com.cross.chain.payment.repository;

import com.cross.chain.payment.domain.Cryptocurrency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptocurrencyRepository extends MongoRepository<Cryptocurrency, String> {

}
