package com.bankapp.bankapp.services;

import com.bankapp.bankapp.models.Transactions;
import com.bankapp.bankapp.respositories.TransactionsRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {
    private final TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public Object createTransactions(Transactions transactions) {
        return this.transactionsRepository.save(transactions);
    }
}
