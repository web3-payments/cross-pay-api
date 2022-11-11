package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String signerAddress;
    private String signature;
    private String email;
    private String phone;
    private List<Wallet> wallets = new ArrayList<>();
    @DBRef
    private List<Product> products = new ArrayList<>();
    @LastModifiedDate
    private Instant updatedAt;

}