package com.adria.accountservice.entities;

import com.adria.accountservice.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") // Expose the id field in JSON
    private Long id;
    private String accountNumber;
    private double balance;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Long  customerId;




}

