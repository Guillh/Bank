package com.bankapp.bankapp.services;

import com.bankapp.bankapp.models.Bank;
import com.bankapp.bankapp.respositories.BankRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Object createBank(Bank bank) {
        return this.bankRepository.save(bank);
    }
}
