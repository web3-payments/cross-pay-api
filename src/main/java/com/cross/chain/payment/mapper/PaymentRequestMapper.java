package com.cross.chain.payment.mapper;

import com.cross.chain.payment.domain.CustomerInfo;
import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.dto.CustomerInfoDto;
import com.cross.chain.payment.dto.PaymentRequest;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentRequestMapper {

    PaymentRequestDetails map(PaymentRequest paymentRequest);

    PaymentRequest map(PaymentRequestDetails paymentRequestDetails);

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
