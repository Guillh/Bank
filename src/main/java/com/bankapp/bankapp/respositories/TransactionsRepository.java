package com.bankapp.bankapp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bankapp.bankapp.models.Transactions;
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

}
