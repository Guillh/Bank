package com.bankapp.bankapp.services;

import com.bankapp.bankapp.models.Bank;
import com.bankapp.bankapp.respositories.BankRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Object createBank(Bank bank) {
        if (this.bankRepository.countBankByName(bank.getBankName())){
            throw new RuntimeException("Banco já cadastrado!");
        }
        return this.bankRepository.save(bank);
    }


    public List<Bank> getAllBanks() {
        return this.bankRepository.findAll();
    }

    public void deleteById(Integer id) {
        this.bankRepository.deleteById(id);
    }
// verificar se o banco poussui agencias antes de deletar
    public Bank getById(Integer id) {
        return this.bankRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Banco não encontrado!");
        });
    }

    public ResponseEntity<Object> replaceBank(Integer id, Bank bank) {
        if (this.bankRepository.countBankByName(bank.getBankName())){
            throw new RuntimeException("Banco já cadastrado!");
        }
        Bank b = this.bankRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Banco não encntrado!");
        });
        b.setBankName(bank.getBankName());
        b.setFullBalanceTransaction(bank.getFullBalanceTransaction());
        return ResponseEntity.ok(this.bankRepository.save(b));

    }
}
