package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.Dto.BankAgencyDto;
import com.bankapp.bankapp.models.Bank;
import com.bankapp.bankapp.models.BankAgency;
import com.bankapp.bankapp.services.BankAgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agency")
public class BankAgencyController {
    final BankAgencyService bankAgencyService;

    public BankAgencyController(BankAgencyService bankAgencyService) {this.bankAgencyService = bankAgencyService; }

    @PostMapping("/create")
    public ResponseEntity<Object> createAgency(@RequestBody BankAgencyDto bankAgencyDto) {
        return ResponseEntity.ok(this.bankAgencyService.createAgency(bankAgencyDto));
    }
}
