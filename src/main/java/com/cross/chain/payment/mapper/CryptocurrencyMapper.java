package com.cross.chain.payment.mapper;

import com.cross.chain.payment.domain.Cryptocurrency;
import com.cross.chain.payment.dto.CryptocurrencyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CryptocurrencyMapper {

    CryptocurrencyDto map(Cryptocurrency cryptocurrency);

    Cryptocurrency map(CryptocurrencyDto cryptocurrency);

}
