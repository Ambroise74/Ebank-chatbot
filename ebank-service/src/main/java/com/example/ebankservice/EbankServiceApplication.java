package com.example.ebankservice;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.service.EbankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(EbankService ebankService) {
        return args -> {
            // Liste de types de comptes
            List<String> types = List.of("CURRENT", "SAVING", "CURRENT");

            // Liste de customerIds
            List<Long> customerIds = List.of(1L, 2L, 3L);

            // Liste de balances
            List<Double> balances = List.of(10000.0, 25000.0, 5000.0);

            for (int i = 0; i < 3; i++) {
                BankAccount bankAccount = BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .creationDate(new Date())
                        .balance(balances.get(i))
                        .type(types.get(i))
                        .customerId(customerIds.get(i))
                        .build();

                ebankService.saveBankAccount(bankAccount);
            }

            // Vérification : afficher tous les comptes sauvegardés
            ebankService.getAllBankAccounts().forEach(account -> {
                System.out.println("----------------------------");
                System.out.println("ID       : " + account.getId());
                System.out.println("Type     : " + account.getType());
                System.out.println("Balance  : " + account.getBalance());
                System.out.println("Customer : " + account.getCustomerId());
                System.out.println("Date     : " + account.getCreationDate());
            });
        };
    }
}
