package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.ProductDTO;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDbMapper {

    ProductEntity map(ProductDTO productRequest);

    ProductDTO map(ProductEntity productEntity);

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