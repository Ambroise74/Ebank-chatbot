package com.example.ebankservice.entities;

import com.example.ebankservice.modele.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Date;
@Entity @Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public class BankAccount {
        @Id
        private String id;
    private Date creationDate;
    private double balance;
    private String type;
    private long customerId;
    @Transient
 private Customer customer;


}
