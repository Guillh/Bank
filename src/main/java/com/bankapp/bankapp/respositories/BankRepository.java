package com.bankapp.bankapp.respositories;

import com.bankapp.bankapp.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM bank b " +
                    "WHERE b.bank_name = :bankName")
    boolean countBankByName(@Param("bankName") String bankName);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM bank b " +
                    "WHERE b.bank_number = :bankNumber")
    boolean countBankByNumber(@Param("bankNumber") Integer bankNumber);

}
