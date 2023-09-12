package com.bankapp.bankapp.services;

import com.bankapp.bankapp.Dto.BankAccountDto;
import com.bankapp.bankapp.models.Associates;
import com.bankapp.bankapp.models.BankAccount;
import com.bankapp.bankapp.respositories.AssociatesRepository;
import com.bankapp.bankapp.respositories.BankAccountRepository;
import com.bankapp.bankapp.respositories.BankAgencyRepository;
import com.bankapp.bankapp.respositories.BankRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankAccountService {
    private final AssociatesRepository associatesRepository;
    private final BankAgencyService bankAgencyService;
    private final AssociatesService associatesService;
    private final BankAccountRepository bankAccountRepository;
    private final BigDecimal WITHDRAWPERCENTAGE = new BigDecimal(0.3);


    public BankAccountService(BankAccountRepository bankAccountRepository, BankAgencyRepository bankAgencyRepository, AssociatesRepository associatesRepository, BankAgencyService bankAgencyService, AssociatesService associatesService, BankRepository bankRepository, BankService bankService, BankAccountRepository bankAccountRepository1) {
        this.associatesRepository = associatesRepository;
        this.bankAgencyService = bankAgencyService;
        this.associatesService = associatesService;
        this.bankAccountRepository = bankAccountRepository;

    }

    public Object createAccount(BankAccountDto bankAccountDto) {
        if(this.bankAccountRepository.countAgencyAndAccountById(bankAccountDto.getAccountNumber(),bankAccountDto.getAgencyId())) {
            throw new RuntimeException("Já existe uma conta com esses dados!");
        }

        if (!this.bankAccountRepository.countAccountOnBankByAgencyId(bankAccountDto.getAssociateId(), bankAccountDto.getAgencyId())) {
            throw new RuntimeException("O associado já possui uma conta neste banco!");
        }


//        this.bankAgencyService.getAgencyById(bankAccountDto.getAgencyId());
//        this.associatesService.getAssociateById(bankAccountDto.getAssociateId());

        BankAccount bankAccount = new BankAccount();
        Associates associate = this.associatesService.getAssociateById(bankAccountDto.getAssociateId());
        bankAccount.setAccountNumber(bankAccountDto.getAccountNumber());
        bankAccount.setAgencyId(this.bankAgencyService.getAgencyById(bankAccountDto.getAgencyId()));
        bankAccount.setAssociateId(associate);
        bankAccount.setBalance(new BigDecimal(0));
        bankAccount.setTransactionLimit(bankAccount.getBalance().multiply(this.WITHDRAWPERCENTAGE));
        return ResponseEntity.ok(this.bankAccountRepository.save(bankAccount));

    }

    public void deleteAccount(Integer id) {
        this.bankAccountRepository.deleteById(id);
    }

    public List<BankAccount> getAllAccounts() {
        return this.bankAccountRepository.findAll();
    }

    public boolean getAllAccountsByAgency(Integer id){
        return this.bankAccountRepository.getAllAccountsByAgency(id);
    }

    public BankAccount getAccountById(Integer id) {
        return this.bankAccountRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Associado não encontrado!");
        });
    }


    public boolean validateAccount(Integer id) {
        return this.bankAccountRepository.existsById(id);
    }

    public ResponseEntity<Object> replaceAccount(Integer id, BankAccountDto bankAccountDto){

    BankAccount bkAcc = this.bankAccountRepository.findById(id).orElseThrow(() -> {
        return new RuntimeException("Conta não encontrada!");
    });

    bkAcc.setAccountNumber(bankAccountDto.getAccountNumber());
    bkAcc.setAgencyId(this.bankAgencyService.getAgencyById(bankAccountDto.getAgencyId()));
    bkAcc.setAssociateId(this.associatesService.getAssociateById(bankAccountDto.getAssociateId()));
    return ResponseEntity.ok(this.bankAccountRepository.save((bkAcc)));
    }



}
