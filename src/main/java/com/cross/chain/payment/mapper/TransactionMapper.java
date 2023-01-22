package com.cross.chain.payment.mapper;

import com.cross.chain.payment.model.PaymentConfirmationDTO;
import com.cross.chain.payment.model.Transaction;
import com.cross.chain.payment.model.TransactionDTO;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mappings({
        @Mapping(source = "paymentConfirmationDTO.transactionDetails.transactionHash", target = "transactionHash"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetails.blockHash", target = "blockHash"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetails.blockNumber", target = "blockNumber"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetails.gasUsed", target = "gasUsed"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetails.toAddress", target = "toAddress"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetails.fromAddress", target = "fromAddress"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetails.confirmations", target = "confirmations"),
        @Mapping(source = "paymentConfirmationDTO.customerInfoDTO.name", target = "customerInfo.name"),
        @Mapping(source = "paymentConfirmationDTO.customerInfoDTO.email", target = "customerInfo.email"),
        @Mapping(source = "paymentConfirmationDTO.customerInfoDTO.phoneNumber", target = "customerInfo.phoneNumber"),
        @Mapping(source = "paymentConfirmationDTO.customerInfoDTO.shippingAddress", target = "customerInfo.shippingAddress")
    })
    TransactionDTO map(PaymentConfirmationDTO paymentConfirmationDTO);

    @Mappings({
            @Mapping(source = "transaction.customerInfo.name", target = "customerInfo.name"),
            @Mapping(source = "transaction.customerInfo.email", target = "customerInfo.email"),
            @Mapping(source = "transaction.customerInfo.phoneNumber", target = "customerInfo.phoneNumber"),
            @Mapping(source = "transaction.customerInfo.shippingAddress", target = "customerInfo.shippingAddress")
    })
    TransactionDTO map(Transaction transaction);

    Transaction map(TransactionDTO transaction);

    default Binary map(byte[]value){
        if(value == null){
            return null;
        }
        return new Binary(BsonBinarySubType.BINARY, value);
    }

    default byte[] map(Binary value) {
        if(value == null){
            return null;
        }
        return value.getData();
    }
}
