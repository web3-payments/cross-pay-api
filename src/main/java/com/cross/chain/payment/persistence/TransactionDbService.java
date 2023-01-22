package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.TransactionDTO;

public interface TransactionDbService {
    TransactionDTO save(TransactionDTO transaction);
}
