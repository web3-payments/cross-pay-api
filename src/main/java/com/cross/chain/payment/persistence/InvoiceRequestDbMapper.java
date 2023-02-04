package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.InvoiceRequestDTO;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceRequestDbMapper {

    InvoiceRequestEntity map(InvoiceRequestDTO invoiceRequest);

    InvoiceRequestDTO map(InvoiceRequestEntity invoiceRequestEntity);

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
