package com.cross.chain.payment.service.product;

import com.cross.chain.payment.exception.CryptocurrencyNotFoundException;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.model.ProductRequest;
import com.cross.chain.payment.model.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductResponse create(String userAddress, ProductRequest productRequest, MultipartFile file) throws IOException, UserNotFoundException, CryptocurrencyNotFoundException;

    ProductResponse create(String userAddress, ProductRequest productRequest) throws UserNotFoundException;

    List<ProductResponse> getAllByUserAddress(String userAddress) throws UserNotFoundException;
}