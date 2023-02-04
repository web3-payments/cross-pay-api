package com.cross.chain.payment.service.invoice;

import com.cross.chain.payment.exception.InvoiceLinkCreationException;
import com.cross.chain.payment.exception.ProductNotFoundException;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.InvoiceRequestMapper;
import com.cross.chain.payment.mapper.ProductMapper;
import com.cross.chain.payment.mapper.TransactionMapper;
import com.cross.chain.payment.model.*;
import com.cross.chain.payment.model.enums.InvoiceStatus;
import com.cross.chain.payment.model.enums.InvoiceType;
import com.cross.chain.payment.persistence.InvoiceRequestDbService;
import com.cross.chain.payment.persistence.ProductDbService;
import com.cross.chain.payment.persistence.TransactionDbService;
import com.cross.chain.payment.persistence.UserDbService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceLinkServiceImpl implements InvoiceService {

    @Value("${invoice.hash-length}")
    private int hashLength;

    @Value("${invoice.url}")
    private String url;

    private final InvoiceRequestMapper invoiceRequestMapper;

    private final ProductMapper productMapper;

    private final TransactionMapper transactionMapper;

    private final InvoiceRequestDbService invoiceRequestDbService;

    private final UserDbService userDbService;

    private final TransactionDbService transactionDbService;
    private final ProductDbService productDbService;

    @Override
    public InvoiceResponse create(InvoiceRequest invoiceRequest) {
        validateInvoice(invoiceRequest); //TODO: move this to a bean validator?
        InvoiceRequestDTO invoiceRequestDTO = invoiceRequestMapper.map(invoiceRequest);
        try {
            invoiceRequestDTO = createLink(invoiceRequestDTO);
        } catch (UserNotFoundException e) {
            throw new InvoiceLinkCreationException(e);
        }
        return InvoiceResponse.builder()
                .paymentLink(invoiceRequestDTO.getInvoiceLink())
                .build();
    }

    @Override
    public InvoiceRequestDTO confirm(InvoiceRequestDTO invoiceRequest, InvoiceConfirmationDTO invoiceConfirmationDTO) {
        TransactionDTO transaction = transactionMapper.map(invoiceConfirmationDTO);
        transaction.setCryptocurrency(invoiceRequest.getCryptocurrency());
        updateProductDetails(transaction.getProducts());
        invoiceRequest.setTransaction(transactionDbService.save(transaction));
        invoiceRequest.setInvoiceStatus(InvoiceStatus.PAID);;
        return invoiceRequestDbService.save(invoiceRequest);
    }

    @Override
    public InvoiceRequestDTO cancel(InvoiceRequestDTO invoiceRequest) {
        //TODO: create a transaction history using the invoiceConfirmation
        if(invoiceRequest.getInvoiceStatus().isFinalStatus()){
            throw new RuntimeException(); //TODO: change exception
        }
        //TODO: increase the total supply since the invoice was cancelled.
        invoiceRequest.setInvoiceStatus(InvoiceStatus.DEACTIVATED);
        return invoiceRequestDbService.save(invoiceRequest);
    }

    @Override
    public void validateInvoice(InvoiceRequest invoiceRequest) {
        //TODO: validate if contains all the required information to create the link
        Assert.notNull(invoiceRequest.getAmount(), String.format("Amount is required for an Invoice"));
    }

    @Override
    public InvoiceType typeInvoice() {
        return InvoiceType.INVOICE;
    }


    private InvoiceRequestDTO createLink(InvoiceRequestDTO invoiceRequestDTO) throws UserNotFoundException {
        UserDTO user = userDbService.findBySignerAddress(invoiceRequestDTO.getUserAddress());
        invoiceRequestDTO.setUser(user);
        invoiceRequestDTO.setHash(RandomStringUtils.randomAlphabetic(hashLength));
        invoiceRequestDTO.setInvoiceLink(url.concat("/").concat(invoiceRequestDTO.getHash()));
        invoiceRequestDTO.setInvoiceStatus(InvoiceStatus.AWAITING_PAYMENT);
        invoiceRequestDTO.setUuid(UUID.randomUUID());
        return invoiceRequestDbService.save(invoiceRequestDTO);
    }

    private void updateProductDetails(List<ProductPaymentDTO> products) {
        products.forEach(item-> {
            ProductDTO product;
            try {
                product = productDbService.findById(item.getProduct().getId());
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e); //TODO: throw an payment Execution error - Product not found
            }
            if(item.getQuantity() > product.getTotalSupply()){
                throw new RuntimeException(); //TODO: throw an payment Execution error - Amount selected not valid
            }
            product.setTotalSupply(product.getTotalSupply() - item.getQuantity());
            productDbService.save(product);
        });
    }

}
