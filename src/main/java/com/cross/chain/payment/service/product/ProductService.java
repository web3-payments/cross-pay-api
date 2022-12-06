package com.cross.chain.payment.service.product;

import com.cross.chain.payment.domain.Product;
import com.cross.chain.payment.domain.ProductsPayment;
import com.cross.chain.payment.dto.ProductRequest;
import com.cross.chain.payment.dto.ProductResponse;
import com.cross.chain.payment.exception.CryptocurrencyNotFoundException;
import com.cross.chain.payment.exception.ProductNotFoundException;
import com.cross.chain.payment.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductResponse create(String userAddress, ProductRequest productRequest, MultipartFile file) throws IOException, UserNotFoundException, CryptocurrencyNotFoundException;

    ProductResponse create(String userAddress, ProductRequest productRequest) throws UserNotFoundException;

    ProductResponse retrieveById(String id) throws ProductNotFoundException;

    List<ProductResponse> getAllByUserAddress(String userAddress) throws UserNotFoundException;

    void update(Product product);
}