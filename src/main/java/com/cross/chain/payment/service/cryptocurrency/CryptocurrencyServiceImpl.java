package com.cross.chain.payment.service.cryptocurrency;

import com.cross.chain.payment.mapper.CryptocurrencyMapper;
import com.cross.chain.payment.model.Cryptocurrency;
import com.cross.chain.payment.persistence.CryptocurrencyDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptocurrencyServiceImpl implements CryptocurrencyService {
    private final CryptocurrencyDbService cryptocurrencyDbService;
    private final CryptocurrencyMapper cryptocurrencyMapper;
    @Override
    public List<Cryptocurrency> listOfSupportedCryptocurrencies() {
        return cryptocurrencyDbService.findAll().stream().map(cryptocurrencyMapper::map).collect(Collectors.toList());
    }
}