package com.cross.chain.payment.service.product;

import com.cross.chain.payment.exception.CryptocurrencyNotFoundException;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.ProductMapper;
import com.cross.chain.payment.model.*;
import com.cross.chain.payment.persistence.CryptocurrencyDbService;
import com.cross.chain.payment.persistence.ProductDbService;
import com.cross.chain.payment.persistence.UserDbService;
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

    private final UserDbService userDbService;

    private final ProductDbService productDbService;

    private final CryptocurrencyDbService cryptocurrencyDbService;

    private final  ProductMapper mapper;

    @Override
    public ProductResponse create(String userAddress, ProductRequest productRequest, MultipartFile file) throws IOException, UserNotFoundException, CryptocurrencyNotFoundException {
        Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        CryptocurrencyDTO cryptocurrencyDTO = cryptocurrencyDbService.findById(productRequest.getCryptocurrencyId());
        ProductDTO productDTO = mapper.map(productRequest);
        productDTO.setCryptocurrency(cryptocurrencyDTO);
        productDTO.setImage(image);
        return mapper.map(persistProduct(userAddress, productDTO));
    }

    @Override
    public ProductResponse create(String userAddress, ProductRequest productRequest) throws UserNotFoundException {
        ProductDTO productDTO = mapper.map(productRequest);
        return mapper.map(persistProduct(userAddress, productDTO));
    }

    private ProductDTO persistProduct(String userAddress, ProductDTO productDTO) throws UserNotFoundException {
        ProductDTO productCreated = productDbService.save(productDTO);
        UserDTO user = userDbService.findBySignerAddress(userAddress);
        user.getProducts().add(productCreated);
        userDbService.save(user);
        return productCreated;
    }

    @Override
    public List<ProductResponse> getAllByUserAddress(String userAddress) throws UserNotFoundException {
        return userDbService.findBySignerAddress(userAddress)
                .getProducts()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

}