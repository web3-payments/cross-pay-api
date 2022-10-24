package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String name;

    private String signerAddress;

    private String signature;

    private String email;

    private String phone;

    private List<Account> accounts;

}