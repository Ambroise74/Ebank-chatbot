package com.example.serviceoperation.service;

import com.example.serviceoperation.entities.Operation;
import com.example.serviceoperation.entities.OperationType;
import com.example.serviceoperation.feign.BankAccountRestClient;
import com.example.serviceoperation.modele.BankAccount;
import com.example.serviceoperation.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final BankAccountRestClient bankAccountRestClient;

    public OperationService(OperationRepository operationRepository, BankAccountRestClient bankAccountRestClient) {
        this.operationRepository = operationRepository;
        this.bankAccountRestClient = bankAccountRestClient;
    }

    // ✅ Dépôt
    public Operation depot(String accountId, double amount) {
        BankAccount account = bankAccountRestClient.getBankAccountById(accountId);
        account.setBalance(account.getBalance() + amount);
        bankAccountRestClient.updateBankAccount(accountId, account);

        Operation operation = Operation.builder()
                .accountId(accountId)
                .amount(amount)
                .date(LocalDateTime.now())
                .type(OperationType.DEPOT)
                .build();

        return operationRepository.save(operation);
    }

    // ✅ Retrait
    public Operation retrait(String accountId, double amount) {
        BankAccount account = bankAccountRestClient.getBankAccountById(accountId);

        if (account.getBalance() < amount) {
            throw new RuntimeException("Solde insuffisant");
        }

        account.setBalance(account.getBalance() - amount);
        bankAccountRestClient.updateBankAccount(accountId, account);

        Operation operation = Operation.builder()
                .accountId(accountId)
                .amount(amount)
                .date(LocalDateTime.now())
                .type(OperationType.RETRAIT)
                .build();

        return operationRepository.save(operation);
    }

    // ✅ Consultation du solde
    public BankAccount consultation(String accountId) {
        return bankAccountRestClient.getBankAccountById(accountId);
    }

}
