package com.bankapp.bankapp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bankapp.bankapp.models.Transactions;

import java.math.BigDecimal;
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT balance  " +
                    "FROM bank_account ba " +
                    "JOIN transactions t ON t.source_account_id = ba.id " +
                    "OR t.target_account_id  = ba.id " +
                    "WHERE ba.id = :sourceAccountId " +
                    "LIMIT 1 ")
    BigDecimal checkBalance(@Param("sourceAccountId") Integer sourceAccountId);
    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM transactions t " +
                    "JOIN bank_account ba ON ba.id = t.source_account_id " +
                    "WHERE transaction_date > current_date " +
                    "AND t.transaction_type = :transactionType " +
                    "AND t.source_account_id  = :sourceAccountId ")
    boolean validateTransferAmount(@Param("transactionType") String transactionType, @Param("sourceAccountId") Integer sourceAccountId);

    @Query(nativeQuery = true,
            value = "SELECT SUM(transaction_value) " +
                    "FROM transactions t " +
                    "JOIN bank_account ba ON t.target_account_id = ba.id " +
                    "WHERE t.transaction_date > CURRENT_DATE " +
                    "AND ba.id  = :targetAccountId ")
     BigDecimal sumDepositsByAccountId(@Param("targetAccountId") Integer targetAccountId);


    @Query(nativeQuery = true,
            value = "SELECT (COALESCE(SUM(transaction_value),0)) " +
                    "FROM transactions t " +
                    "JOIN bank_account ba ON t.source_account_id = ba.id " +
                    "WHERE t.transaction_date > CURRENT_DATE " +
                    "AND ba.id = :sourceAccountId ")
    BigDecimal sumTransfersByAccountId(@Param("sourceAccountId") Integer sourceAccountId);

    @Query(nativeQuery = true,
            value = "SELECT full_balance_transaction " +
                    "FROM bank b " +
                    "JOIN bank_agency ba ON b.bank_number = ba.bank_number " +
                    "JOIN bank_account ba2 ON ba.id = ba2.agency_number " +
                    "WHERE ba2.id = :sourceAccountId ")
    BigDecimal getFullBalanceTransaction (@Param("sourceAccountId") Integer sourceAccountId);

}