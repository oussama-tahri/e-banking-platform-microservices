package com.adria.userservice.entities;


import com.adria.userservice.models.Account;
import com.adria.userservice.models.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@DiscriminatorValue("CUSTOMER")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Customer  extends User{

    @Transient
    private List<Account> bankAccounts;
}
