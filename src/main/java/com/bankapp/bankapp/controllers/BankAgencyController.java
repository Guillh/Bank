package com.bankapp.bankapp.controllers;

import com.bankapp.bankapp.Dto.BankAgencyDto;
import com.bankapp.bankapp.models.Bank;
import com.bankapp.bankapp.models.BankAgency;
import com.bankapp.bankapp.services.BankAgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class BankAgencyController {
    private final BankAgencyService bankAgencyService;

    public BankAgencyController(BankAgencyService bankAgencyService) {
        this.bankAgencyService = bankAgencyService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAgency(@RequestBody BankAgencyDto bankAgencyDto) {
        return ResponseEntity.ok(this.bankAgencyService.createAgency(bankAgencyDto));
    }

    @GetMapping("/get-all")
    public List<BankAgency> getAllAgencys() {
        return this.bankAgencyService.getAllAgencys();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAgency(@PathVariable Integer id) {this.bankAgencyService.deleteAgency(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAgency(@PathVariable Integer id) {
        return ResponseEntity.ok(this.bankAgencyService.getAgencyById(id));
    }

    @PutMapping("/replace/{id}")
    public ResponseEntity<Object> replaceAgency(@PathVariable Integer id, @RequestBody BankAgencyDto bankAgencyDto) {
        return this.bankAgencyService.replaceAgency(id,bankAgencyDto);
    }
}
