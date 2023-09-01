package com.bankapp.bankapp.services;

import com.bankapp.bankapp.Dto.BankAgencyDto;
import com.bankapp.bankapp.models.BankAgency;
import com.bankapp.bankapp.respositories.BankAgencyRepository;
import com.bankapp.bankapp.respositories.BankRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BankAgencyService {
    private final BankAgencyRepository bankAgencyRepository;
    private final BankRepository bankRepository;
    private final BankService bankService;

    public BankAgencyService(BankAgencyRepository bankAgencyRepository, BankRepository bankRepository, BankService bankService) {
        this.bankAgencyRepository = bankAgencyRepository;
        this.bankRepository = bankRepository;
        this.bankService = bankService;
    }
    
    public Object createAgency(BankAgencyDto bankAgencyDto) {
        if(Objects.isNull(bankAgencyDto.getBankNumber())) {
            new RuntimeException("É necessário informar um Banco");
        } else if (!this.bankRepository.countBankByNumber(bankAgencyDto.getBankNumber())) {
            new RuntimeException("É necessario um banco existente para cadastrar uma agencia!");
        } else if (this.bankAgencyRepository.countAgencyByNumber(bankAgencyDto.getAgencyNumber())) {
            new RuntimeException("Essa agencia já existe");
            
        }
        BankAgency bankAgency = new BankAgency();
        bankAgency.setAgencyNumber(bankAgencyDto.getAgencyNumber());
        bankAgency.setBankNumber(this.bankService.getById(bankAgencyDto.getBankNumber()));
        return ResponseEntity.ok(this.bankAgencyRepository.save(bankAgency));


    }
}
