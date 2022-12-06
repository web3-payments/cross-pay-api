package com.cross.chain.payment.service.product;

import com.cross.chain.payment.domain.Cryptocurrency;
import com.cross.chain.payment.domain.Product;
import com.cross.chain.payment.domain.User;
import com.cross.chain.payment.dto.ProductRequest;
import com.cross.chain.payment.dto.ProductResponse;
import com.cross.chain.payment.exception.CryptocurrencyNotFoundException;
import com.cross.chain.payment.exception.ProductNotFoundException;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.ProductMapper;
import com.cross.chain.payment.repository.CryptocurrencyRepository;
import com.cross.chain.payment.repository.ProductRepository;
import com.cross.chain.payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;

    private final  ProductRepository productRepository;

    private final  CryptocurrencyRepository cryptocurrencyRepository;

    private final  ProductMapper mapper;

    @Override
    public ProductResponse create(String userAddress, ProductRequest productRequest, MultipartFile file) throws IOException, UserNotFoundException, CryptocurrencyNotFoundException {
        Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        Cryptocurrency cryptocurrency = cryptocurrencyRepository.findById(productRequest.getCryptocurrencyId()).
                orElseThrow(CryptocurrencyNotFoundException::new);
        Product product = mapper.map(productRequest);
        product.setCryptocurrency(cryptocurrency);
        product.setImage(image);
        return mapper.map(persistProduct(userAddress, product));
    }

    @Override
    public ProductResponse create(String userAddress, ProductRequest productRequest) throws UserNotFoundException {
        Product product = mapper.map(productRequest);
        return mapper.map(persistProduct(userAddress, product));
    }

    @Override
    public ProductResponse retrieveById(String id) throws ProductNotFoundException {
        return mapper.map(productRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    private Product persistProduct(String userAddress, Product product) throws UserNotFoundException {
        Product productCreated = productRepository.save(product);
        User user = userRepository.findBySignerAddress(userAddress).orElseThrow(UserNotFoundException::new);
        user.getProducts().add(productCreated);
        userRepository.save(user);
        return productCreated;
    }

    @Override
    public List<ProductResponse> getAllByUserAddress(String userAddress) throws UserNotFoundException {
        return userRepository.findBySignerAddress(userAddress)
                .orElseThrow(UserNotFoundException::new)
                .getProducts()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }
}