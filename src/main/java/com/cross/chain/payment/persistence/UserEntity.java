package com.cross.chain.payment.persistence;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "user")
class UserEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String signerAddress;
    private String signature;
    private String email;
    private String phone;
    private Binary image;
    private List<WalletEntity> wallets = new ArrayList<>();
    @DBRef
    private List<ProductEntity> products = new ArrayList<>();
    @DBRef
    private List<CustomerEntity> customers = new ArrayList<>();
    @LastModifiedDate
    private Instant updatedAt;

}