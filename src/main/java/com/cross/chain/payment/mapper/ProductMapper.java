package com.cross.chain.payment.mapper;

import com.cross.chain.payment.domain.Product;
import com.cross.chain.payment.dto.ProductRequest;
import com.cross.chain.payment.dto.ProductResponse;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product map(ProductRequest productRequest);

    Product map(ProductResponse productResponse);

    ProductResponse map(Product product);

    default byte[] mapImage(Binary image) {
        if(image == null){
            return null;
        }
        return image.getData();
    }

    default Binary map(byte[] value) {
        if(value == null){
            return null;
        }
        return new Binary(BsonBinarySubType.BINARY, value);
    }

}