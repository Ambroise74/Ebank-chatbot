package com.example.serviceoperation.feign;

import com.example.serviceoperation.modele.BankAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ebank-service")
public interface BankAccountRestClient {

    @GetMapping("/account/{id}")
    BankAccount getBankAccountById(@PathVariable String id);

    @PutMapping("/account/{id}")
    BankAccount updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount);
}