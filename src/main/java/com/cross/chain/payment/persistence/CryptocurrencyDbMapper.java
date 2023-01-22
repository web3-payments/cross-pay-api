package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.CryptocurrencyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CryptocurrencyDbMapper {

    CryptocurrencyDTO map(CryptocurrencyEntity cryptocurrencyEntity);

    CryptocurrencyEntity map(CryptocurrencyDTO cryptocurrency);

}
