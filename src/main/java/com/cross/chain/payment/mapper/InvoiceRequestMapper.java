package com.cross.chain.payment.mapper;

import com.cross.chain.payment.model.InvoiceConfirmationDTO;
import com.cross.chain.payment.model.InvoiceConfirmationRequest;
import com.cross.chain.payment.model.InvoiceRequest;
import com.cross.chain.payment.model.InvoiceRequestDTO;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceRequestMapper {

    InvoiceRequestDTO map(InvoiceRequest invoiceRequest);
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    InvoiceRequest map(InvoiceRequestDTO invoiceRequest);

    InvoiceConfirmationDTO map(InvoiceConfirmationRequest invoiceConfirmationDTO);

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
