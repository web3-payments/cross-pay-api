package com.cross.chain.payment.mapper;

import com.cross.chain.payment.model.Cryptocurrency;
import com.cross.chain.payment.model.CryptocurrencyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CryptocurrencyMapper {
    CryptocurrencyDTO map(Cryptocurrency cryptocurrency);
    Cryptocurrency map(CryptocurrencyDTO cryptocurrency);

}