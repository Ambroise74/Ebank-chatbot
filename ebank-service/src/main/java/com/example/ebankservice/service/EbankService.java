package com.example.ebankservice.service;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbankService {

    private BankAccountRepository bankAccountRepository;
     public  EbankService(BankAccountRepository bankAccountRepository) {
         this.bankAccountRepository = bankAccountRepository;
     }

     public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
     }
    public BankAccount getBankAccountById(String id) {
         return bankAccountRepository.findById(id)
                 .orElseThrow(()->new RuntimeException("not found") );
    }
    public  BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
}
