package com.cross.chain.payment.service.cryptocurrency;

import com.cross.chain.payment.dto.CryptocurrencyDTO;

import java.util.List;

public interface CryptocurrencyService {

    List<CryptocurrencyDTO> listOfSupportedCryptocurrencies();

}
