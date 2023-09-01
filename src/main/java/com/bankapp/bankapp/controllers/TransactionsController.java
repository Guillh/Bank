package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.models.Transactions;
import com.bankapp.bankapp.services.TransactionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {
    final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {this.transactionsService = transactionsService;}

    @PostMapping("/create-transactions")
    public ResponseEntity<Object> createTransactions(@RequestBody Transactions transactions) {
        return ResponseEntity.ok(this.transactionsService.createTransactions(transactions));
    }
}
