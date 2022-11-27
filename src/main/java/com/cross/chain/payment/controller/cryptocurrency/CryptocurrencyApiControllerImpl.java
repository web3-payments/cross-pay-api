package com.cross.chain.payment.controller.cryptocurrency;

import com.cross.chain.payment.dto.CryptocurrencyDto;
import com.cross.chain.payment.service.cryptocurrency.CryptocurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class CryptocurrencyApiControllerImpl implements CryptocurrencyApiController {

    @Autowired
    private CryptocurrencyService service;

    @Override
    @GetMapping(value = CRYPTOCURRENCY, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CryptocurrencyDto>> getAllSupportedCryptocurrencies() {
        return ResponseEntity.ok(service.listOfSupportedCryptocurrencies());
    }
}
