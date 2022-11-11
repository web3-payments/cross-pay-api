package com.cross.chain.payment.service.product;

import com.cross.chain.payment.domain.Product;
import com.cross.chain.payment.domain.User;
import com.cross.chain.payment.dto.ProductRequest;
import com.cross.chain.payment.dto.ProductResponse;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.ProductMapper;
import com.cross.chain.payment.repository.ProductRepository;
import com.cross.chain.payment.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public ProductResponse create(String userAddress, ProductRequest productRequest, MultipartFile file) throws IOException, UserNotFoundException {
        Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        Product product = mapper.map(productRequest);
        product.setImage(image);
        return mapper.map(persistProduct(userAddress, product));
    }

    @Override
    public ProductResponse create(String userAddress, ProductRequest productRequest) throws UserNotFoundException {
        Product product = mapper.map(productRequest);
        return mapper.map(persistProduct(userAddress, product));
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
}