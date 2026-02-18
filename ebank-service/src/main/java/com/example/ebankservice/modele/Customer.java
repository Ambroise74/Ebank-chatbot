package com.example.ebankservice.modele;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {

    private String id;
    private String name;
    private String email ;
}
