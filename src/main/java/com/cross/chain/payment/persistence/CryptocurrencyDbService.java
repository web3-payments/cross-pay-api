package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.CryptocurrencyNotFoundException;
import com.cross.chain.payment.model.CryptocurrencyDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CryptocurrencyDbService {
    private final CryptocurrencyRepository repository;
    private final CryptocurrencyDbMapper mapper;
    public List<CryptocurrencyDTO> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public CryptocurrencyDTO findById(String cryptocurrencyId) throws CryptocurrencyNotFoundException {
        return mapper.map(repository.findById(cryptocurrencyId).orElseThrow(CryptocurrencyNotFoundException::new));
    }
}
