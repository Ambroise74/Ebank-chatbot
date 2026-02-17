package com.example.ebankservice.controllers;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.service.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class EbankRestController {

    private EbankService ebankService;
    public EbankRestController(EbankService ebankService) {
        this.ebankService = ebankService;
    }


    @GetMapping("accounts")
    public List<BankAccount> getAllBankAccounts() {
        return ebankService.getAllBankAccounts();
    }
    @GetMapping("account/{id}")
    public BankAccount getBankAccountById( @PathVariable String id) {
        return ebankService.getBankAccountById(id);

    }

    @PostMapping("/account")
    public  BankAccount saveBankAccount( @RequestBody BankAccount bankAccount) {
        return ebankService.saveBankAccount(bankAccount);
    }

}
