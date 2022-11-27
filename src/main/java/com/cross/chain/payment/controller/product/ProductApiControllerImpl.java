package com.cross.chain.payment.controller.product;

import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.dto.ProductRequest;
import com.cross.chain.payment.dto.ProductResponse;
import com.cross.chain.payment.exception.CryptocurrencyNotFoundException;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class ProductApiControllerImpl implements ProductApiController {

    @Autowired
    private ProductService service;

    @Override
    @PostMapping(value = PRODUCT, produces = {APPLICATION_JSON_VALUE}, consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity createProductWithImage(@PathVariable(value = "address") String address, @RequestPart("image") MultipartFile file, ProductRequest productRequest) throws UserNotFoundException, IOException, CryptocurrencyNotFoundException {
        service.create(address, productRequest, file);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(value = PRODUCT, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity createProduct(@PathVariable(value = "address") String address, @RequestBody ProductRequest productRequest) throws UserNotFoundException {
        service.create(address, productRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = PRODUCT, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductResponse>> getAllProducts(@PathVariable(value = "address") String address) throws UserNotFoundException {
        return ResponseEntity.ok(service.getAllByUserAddress(address));
    }
}