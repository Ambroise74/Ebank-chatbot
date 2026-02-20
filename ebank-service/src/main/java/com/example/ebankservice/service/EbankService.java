package com.example.ebankservice.service;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.feign.CustomersRestClient;
import com.example.ebankservice.modele.Customer;
import com.example.ebankservice.repository.BankAccountRepository;
import feign.FeignException;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbankService {


    private final CustomersRestClient customersRestClient;
    private final BankAccountRepository bankAccountRepository;

    public EbankService(CustomersRestClient customersRestClient, BankAccountRepository bankAccountRepository) {
        this.customersRestClient = customersRestClient;
        this.bankAccountRepository = bankAccountRepository;
    }

    @McpTool(description = "get all bank account")
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
     }
    @McpTool(description = "get bank account by id")

    public BankAccount getBankAccountById( @McpToolParam(description = "account id") String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BankAccount not found with id: " + id));

        // Pour un type primitif long, vérifier qu'il est valide (> 0)
        if (bankAccount.getCustomerId() <= 0) {
            throw new RuntimeException("CustomerId invalide pour le bankAccount: " + id);
        }

        try {
            Customer customer = customersRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setCustomer(customer);
        } catch (FeignException e) {
            throw new RuntimeException("Impossible de récupérer le customer avec l'id: "
                    + bankAccount.getCustomerId() + " — " + e.getMessage(), e);
        }

        return bankAccount;
    }
    @McpTool(description = "save a new bank account (balance ,type, customer")
    public  BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
}
