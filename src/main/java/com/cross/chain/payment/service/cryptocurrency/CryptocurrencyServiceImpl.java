package com.cross.chain.payment.service.cryptocurrency;

import com.cross.chain.payment.domain.Cryptocurrency;
import com.cross.chain.payment.dto.CryptocurrencyDto;
import com.cross.chain.payment.mapper.CryptocurrencyMapper;
import com.cross.chain.payment.repository.CryptocurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    @Autowired
    private CryptocurrencyRepository repository;

    @Autowired
    private CryptocurrencyMapper mapper;

    @Override
    public List<CryptocurrencyDto> listOfSupportedCryptocurrencies() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }
}
