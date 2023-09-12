package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.Dto.TransactionsDto;
import com.bankapp.bankapp.Enums.TransactionsType;
import com.bankapp.bankapp.services.TransactionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionsController {
    //private final TransactionsDto transactionsDto;
    private final TransactionsService transactionsService;
    public TransactionsController(TransactionsService transactionsService) {
      //  this.transactionsDto = transactionsDto;
        this.transactionsService = transactionsService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Object> createDeposit(@RequestBody TransactionsDto transactionsDto) {
        return ResponseEntity.ok(this.transactionsService.createTransactions(transactionsDto, TransactionsType.DEPOSIT));
    }
    @PostMapping("/transfer")
    public ResponseEntity<Object> createTransfer(@RequestBody TransactionsDto transactionsDto) {
        return ResponseEntity.ok(this.transactionsService.createTransactions(transactionsDto, TransactionsType.TRANSFER));
    }
    @PostMapping("/withdraw")
    public ResponseEntity<Object> createWithdraw(@RequestBody TransactionsDto transactionsDto) {
        return ResponseEntity.ok(this.transactionsService.createTransactions(transactionsDto, TransactionsType.WITHDRAW));
    }


}
