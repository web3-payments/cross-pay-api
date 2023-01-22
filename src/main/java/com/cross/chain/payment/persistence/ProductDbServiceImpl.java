package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.ProductNotFoundException;
import com.cross.chain.payment.model.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDbServiceImpl implements ProductDbService {

    private final ProductRepository repository;

    private final ProductDbMapper mapper;

    @Override
    public ProductDTO findById(String id) throws ProductNotFoundException {
        return mapper.map(repository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity = mapper.map(productDTO);
        return mapper.map(repository.save(productEntity));
    }
}