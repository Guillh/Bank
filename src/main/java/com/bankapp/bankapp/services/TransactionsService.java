package com.bankapp.bankapp.services;

import com.bankapp.bankapp.Dto.TransactionsDto;
import com.bankapp.bankapp.Enums.TransactionsType;
import com.bankapp.bankapp.models.Bank;
import com.bankapp.bankapp.models.BankAccount;
import com.bankapp.bankapp.models.Transactions;
import com.bankapp.bankapp.respositories.BankAccountRepository;
import com.bankapp.bankapp.respositories.TransactionsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class TransactionsService {
    private final BankAccountService bankAccountService;
    private final BankAccountRepository bankAccountRepository;
    private BankService bankService;
    private final TransactionsRepository transactionsRepository;
    private Bank bank;
    public TransactionsService(BankAccountService bankAccountService, BankAccountRepository bankAccountRepository, TransactionsRepository transactionsRepository) {
        this.bankAccountService = bankAccountService;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionsRepository = transactionsRepository;

    }
    public ResponseEntity<Object> createTransactions(TransactionsDto transactionsDto, TransactionsType transactionsType) {
        this.generalTransactionsValidation(transactionsDto);
        Transactions transactions = new Transactions();
        Date date = new Date();
        Date dateValidation = new Date(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));

        switch(transactionsType) {
            case DEPOSIT:
                this.depositTransactionValidation(transactionsDto);
                break;
            case TRANSFER:
                //this.transferTransactionValidation(transactionsDto);
                this.depositTransactionValidation(transactionsDto);
                this.withdrawTransactionValidation(transactionsDto);
                this.transferAndWithdrawTransactionsValidation(transactionsDto, TransactionsType.TRANSFER);
                break;
            case WITHDRAW:
                this.withdrawTransactionValidation(transactionsDto);
                this.transferAndWithdrawTransactionsValidation(transactionsDto,TransactionsType.WITHDRAW);
                break;
        }
        if(Objects.nonNull(transactionsDto.getSourceAccountId()) &&
                (transactionsType.equals(TransactionsType.WITHDRAW) ||
                transactionsType.equals(TransactionsType.TRANSFER))) {
            transactions.setSourceAccountId(this.bankAccountService.getAccountById(transactionsDto.getSourceAccountId()));
            this.updateBalance(transactionsDto.getSourceAccountId(), transactionsDto.getTransactionValue().negate());
        }
        if (Objects.nonNull(transactionsDto.getTargetAccountId()) &&
                (transactionsType.equals(TransactionsType.DEPOSIT) ||
                transactionsType.equals(TransactionsType.TRANSFER))) {
            transactions.setTargetAccountId(this.bankAccountService.getAccountById(transactionsDto.getTargetAccountId()));
            this.updateBalance(transactionsDto.getTargetAccountId(), transactionsDto.getTransactionValue());
        }
        transactions.setTransactionType(transactionsType.getTransactionType());
        transactions.setTransactionValue(transactionsDto.getTransactionValue());
        transactions.setTransactionDate(date);
        return ResponseEntity.ok(this.transactionsRepository.save(transactions));
    }

    public void generalTransactionsValidation(TransactionsDto transactionsDto) {
        if(transactionsDto.getTransactionValue().compareTo(new BigDecimal("0")) <= 0){
            throw new RuntimeException("Não é possivel realizar uma transação com esse valor!");
        }

    }

    public void transferAndWithdrawTransactionsValidation(TransactionsDto transactionsDto, TransactionsType transactionsType) {
        if(transactionsDto.getSourceAccountId() == transactionsDto.getTargetAccountId()) {
            throw new RuntimeException("A conta origem e a conta alvo devem ser diferentes!");
        }
        if(!bankAccountService.validateAccount(transactionsDto.getSourceAccountId())) {
            throw new RuntimeException("A conta origem desta transação não existe!");
        }
        if (this.validateTransferAmount(transactionsType.getTransactionType(),transactionsDto.getSourceAccountId())){
            throw new RuntimeException("Voce já realizou um " + transactionsType.getTransactionType() + " hoje!");
        }
        if(transactionsRepository.getFullBalanceTransaction(transactionsDto.getSourceAccountId()).compareTo(transactionsRepository.checkBalance(transactionsDto.getSourceAccountId())) < 0) {
            if (calculateTransferLimit(transactionsDto.getSourceAccountId()).compareTo(transactionsDto.getTransactionValue()) < 0) {
                throw new RuntimeException("Limite diario de transferencia utrapassado!");
            }
        }
        if(transactionsDto.getTransactionValue().compareTo(transactionsRepository.checkBalance(transactionsDto.getSourceAccountId())) > 0){
            throw new RuntimeException("Saldo insuficiente para transação");
        }
    }

    public void depositTransactionValidation(TransactionsDto transactionsDto) {
        if(!this.bankAccountService.validateAccount(transactionsDto.getTargetAccountId())) {
            throw new RuntimeException("A conta alvo desta transação não existe!");
        }
    }
    public void withdrawTransactionValidation(TransactionsDto transactionsDto) {
        if(!this.bankAccountService.validateAccount(transactionsDto.getSourceAccountId())) {
            throw new RuntimeException("A conta alvo desta transação não existe!");
        }
    }
    public void transferTransactionValidation(TransactionsDto transactionsDto) {
        if(!this.bankAccountService.validateAccount(transactionsDto.getTargetAccountId())) {
            throw new RuntimeException("A conta alvo desta transação não existe!");
        }
        if(!this.bankAccountService.validateAccount(transactionsDto.getTargetAccountId())) {
            throw new RuntimeException("A conta alvo desta transação não existe!");
        }
    }
    public BankAccount updateBalance(Integer accountId, BigDecimal transferValue) {
        BankAccount bankAccount = this.bankAccountService.getAccountById(accountId);
        bankAccount.setBalance(bankAccount.getBalance().add(transferValue));
        return this.bankAccountRepository.save(bankAccount);
    }


    public BigDecimal calculateTransferLimit(Integer accountId) {
        BigDecimal transferLimit = this.getDayBalance(accountId).multiply(new BigDecimal("0.3"));
        transferLimit = transferLimit.subtract(transactionsRepository.sumTransfersByAccountId(accountId));
        return transferLimit;
    }
    public boolean validateTransferAmount(String transactionType, Integer id) {
        return this.transactionsRepository.validateTransferAmount(transactionType,id);
    }

    public BigDecimal getDayBalance(Integer accountId){
        BigDecimal balance = transactionsRepository.sumTransfersByAccountId(accountId).subtract(transactionsRepository.sumDepositsByAccountId(accountId));
        balance = transactionsRepository.checkBalance(accountId).add(balance);
        return balance;
    }

}
