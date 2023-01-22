package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.ProductNotFoundException;
import com.cross.chain.payment.model.ProductDTO;

public interface ProductDbService {
    ProductDTO findById(String id) throws ProductNotFoundException;

    ProductDTO save(ProductDTO productDTO);
}