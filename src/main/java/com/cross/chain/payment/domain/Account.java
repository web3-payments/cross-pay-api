package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "account")
public class Account {

    private String name;

    private String address;

}