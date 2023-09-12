package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.Dto.BankAccountDto;
import com.bankapp.bankapp.Dto.BankAgencyDto;
import com.bankapp.bankapp.models.Associates;
import com.bankapp.bankapp.models.BankAccount;
import com.bankapp.bankapp.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    @Autowired
    private final BankAccountService bankAccount;

    public BankAccountController(BankAccountService bankAccount) {
        this.bankAccount = bankAccount;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAccount(@RequestBody BankAccountDto bankAccountDto) {
        return ResponseEntity.ok(this.bankAccount.createAccount(bankAccountDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Integer id) {
        this.bankAccount.deleteAccount(id);
    }

    @GetMapping("/get-all")
    public List<BankAccount> getAllAccounts() {
        return this.bankAccount.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccountById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.bankAccount.getAccountById(id));
    }

    @PutMapping("/replace/{id}")
    public ResponseEntity<Object> replaceAccount(@PathVariable Integer id, @RequestBody BankAccountDto bankAccountDto) {
        return this.bankAccount.replaceAccount(id, bankAccountDto);
    }
}
