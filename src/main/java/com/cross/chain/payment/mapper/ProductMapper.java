package com.cross.chain.payment.mapper;

import com.cross.chain.payment.model.ProductDTO;
import com.cross.chain.payment.model.ProductRequest;
import com.cross.chain.payment.model.ProductResponse;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO map(ProductRequest productRequest);

    ProductDTO map(ProductResponse productResponse);

    ProductResponse map(ProductDTO productEntity);

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