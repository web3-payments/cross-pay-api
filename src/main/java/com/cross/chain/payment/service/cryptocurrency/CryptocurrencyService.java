package com.cross.chain.payment.service.cryptocurrency;

import com.cross.chain.payment.dto.CryptocurrencyDto;

import java.util.List;

public interface CryptocurrencyService {

    List<CryptocurrencyDto> listOfSupportedCryptocurrencies();

}
