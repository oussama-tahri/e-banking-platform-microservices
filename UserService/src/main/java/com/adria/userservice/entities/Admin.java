package com.adria.userservice.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("ADMIN")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Admin extends User{
    private String phoneNumber;
}
