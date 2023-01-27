package com.cross.chain.payment.mapper;

import com.cross.chain.payment.model.PaymentConfirmationDTO;
import com.cross.chain.payment.model.PaymentConfirmationRequest;
import com.cross.chain.payment.model.PaymentRequest;
import com.cross.chain.payment.model.PaymentRequestDTO;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentRequestMapper {

    PaymentRequestDTO map(PaymentRequest paymentRequest);
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    PaymentRequest map(PaymentRequestDTO paymentRequest);

    PaymentConfirmationDTO map(PaymentConfirmationRequest paymentConfirmationDTO);

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
