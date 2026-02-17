package com.example.ebankservice.modele;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {

    private Long id;
    private String name;
    private String email ;
}
