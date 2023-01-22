package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionDbServiceImpl implements TransactionDbService {

    private final TransactionRepository repository;

    private final TransactionDbMapper mapper;

    @Override
    public TransactionDTO save(TransactionDTO transaction) {
        TransactionEntity transactionEntity = mapper.map(transaction);
        return mapper.map(repository.save(transactionEntity));
    }
}
