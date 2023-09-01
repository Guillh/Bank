package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.models.Bank;
import com.bankapp.bankapp.services.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBank(@RequestBody Bank bank) {
        return ResponseEntity.ok(this.bankService.createBank(bank));
    }

    @GetMapping("/get-all")
    public List<Bank> getAllBanks() {
        return this.bankService.getAllBanks();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBank(@PathVariable Integer id) {
        this.bankService.deleteById(id);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Object> getBank(@PathVariable Integer id) {
        return ResponseEntity.ok(this.bankService.getById(id));
    }

    @PutMapping("/replace/{id}")
    public ResponseEntity<Object> replaceBank(@PathVariable Integer id, @RequestBody Bank bank) {
        return this.bankService.replaceBank(id,bank);
    }

}
