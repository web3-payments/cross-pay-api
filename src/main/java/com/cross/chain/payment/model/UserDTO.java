package com.cross.chain.payment.model;

import lombok.Data;
import org.bson.types.Binary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public
class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String signerAddress;
    private String signature;
    private String email;
    private String phone;
    private Binary image;
    private List<WalletDTO> wallets = new ArrayList<>();
    private List<ProductDTO> products = new ArrayList<>();
    private List<CustomerDTO> customers = new ArrayList<>();
    private LocalDateTime updatedAt;
}