package com.example.serviceoperation.modele;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BankAccount {
    private String id;
    private double balance;
    private Long customerId;
    private String type;
}