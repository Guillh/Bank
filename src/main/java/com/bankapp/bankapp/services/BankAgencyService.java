package com.bankapp.bankapp.services;

import com.bankapp.bankapp.Dto.BankAgencyDto;
import com.bankapp.bankapp.models.BankAgency;
import com.bankapp.bankapp.respositories.BankAgencyRepository;
import com.bankapp.bankapp.respositories.BankRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
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
    
    public ResponseEntity<BankAgency> createAgency(BankAgencyDto bankAgencyDto) {
        if(Objects.isNull(bankAgencyDto.getBankNumber())) {
            throw new RuntimeException("É necessário informar um Banco");
        }
        if (!this.bankRepository.countBankByNumber(bankAgencyDto.getBankNumber())) {
            throw new RuntimeException("É necessario um banco existente para cadastrar uma agencia!");
        }
        if (this.bankAgencyRepository.countAgencyAndBankByNumber(bankAgencyDto.getAgencyNumber(),bankAgencyDto.getBankNumber())) {
            throw new RuntimeException("Essa agencia já existe");
        }

        BankAgency bankAgency = new BankAgency();
        bankAgency.setAgencyNumber(bankAgencyDto.getAgencyNumber());
        bankAgency.setBankNumber(this.bankService.getById(bankAgencyDto.getBankNumber()));
        return ResponseEntity.ok(this.bankAgencyRepository.save(bankAgency));


    }

    public List<BankAgency> getAllAgencys() {
        return this.bankAgencyRepository.findAll();
    }

    public void deleteAgency(Integer id) {
        this.bankAgencyRepository.findAll();
    }

    public BankAgency getAgencyById(Integer id) {
        return this.bankAgencyRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Agencia não encontrada!");
        });
    }

    public ResponseEntity<Object> replaceAgency(Integer id, BankAgencyDto bankAgencyDto) {
        if(bankAgencyRepository.countAgencyByNumber(bankAgencyDto.getAgencyNumber())) {
            throw new RuntimeException("Agencia já cadastrada!");
        }
        BankAgency ba = this.bankAgencyRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Agencia não encontrada!");
        });
        ba.setAgencyNumber(bankAgencyDto.getAgencyNumber());
        return ResponseEntity.ok(this.bankAgencyRepository.save(ba));
    }
}
