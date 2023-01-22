package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.PaymentRequestDTO;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentRequestDbMapper {

    PaymentRequestEntity map(PaymentRequestDTO paymentRequest);

    PaymentRequestDTO map(PaymentRequestEntity paymentRequestEntity);

    default Binary map(byte[] value){
        if(value == null){
            return null;
        }
        return new Binary(BsonBinarySubType.BINARY, value);
    }
    default byte[] map(Binary value){
        if(value == null){
            return null;
        }
        return value.getData();
    }

}
