package com.cross.chain.payment.service.cryptocurrency;

import com.cross.chain.payment.model.Cryptocurrency;

import java.util.List;

public interface CryptocurrencyService {

    List<Cryptocurrency> listOfSupportedCryptocurrencies();

}
