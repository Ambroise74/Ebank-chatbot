package com.example.serviceoperation.controllers;

import com.example.serviceoperation.entities.Operation;
import com.example.serviceoperation.modele.BankAccount;
import com.example.serviceoperation.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationRestController {

    private final OperationService operationService;  // 👈 ajoute final
    @PostMapping("/depot/{accountId}/{amount}")
    public Operation depot(@PathVariable String accountId, @PathVariable double amount) {
        return operationService.depot(accountId, amount);
    }

    @PostMapping("/retrait/{accountId}/{amount}")
    public Operation retrait(@PathVariable String accountId, @PathVariable double amount) {
        return operationService.retrait(accountId, amount);
    }

    @GetMapping("/consultation/{accountId}")
    public BankAccount consultation(@PathVariable String accountId) {
        return operationService.consultation(accountId);
    }


}
