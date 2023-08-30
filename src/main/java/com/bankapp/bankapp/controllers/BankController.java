package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.models.Bank;
import com.bankapp.bankapp.services.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/create-bank")
    public ResponseEntity<Object> createBank(@RequestBody Bank bank) {
        return ResponseEntity.ok(this.bankService.createBank(bank));
    }
}
