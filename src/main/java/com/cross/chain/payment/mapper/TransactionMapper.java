package com.cross.chain.payment.mapper;

import com.cross.chain.payment.domain.Transaction;
import com.cross.chain.payment.dto.PaymentConfirmationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mappings({
        @Mapping(source = "paymentConfirmationDTO.transactionDetailsDTO.transactionHash", target = "transactionHash"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetailsDTO.blockHash", target = "blockHash"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetailsDTO.blockNumber", target = "blockNumber"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetailsDTO.gasUsed", target = "gasUsed"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetailsDTO.toAddress", target = "toAddress"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetailsDTO.fromAddress", target = "fromAddress"),
        @Mapping(source = "paymentConfirmationDTO.transactionDetailsDTO.confirmations", target = "confirmations"),
        @Mapping(source = "paymentConfirmationDTO.customerInfoDTO.name", target = "customerInfo.name"),
        @Mapping(source = "paymentConfirmationDTO.customerInfoDTO.email", target = "customerInfo.email"),
        @Mapping(source = "paymentConfirmationDTO.customerInfoDTO.phoneNumber", target = "customerInfo.phoneNumber"),
        @Mapping(source = "paymentConfirmationDTO.customerInfoDTO.shippingAddress", target = "customerInfo.shippingAddress")
    })
    Transaction map(PaymentConfirmationDTO paymentConfirmationDTO);

}
