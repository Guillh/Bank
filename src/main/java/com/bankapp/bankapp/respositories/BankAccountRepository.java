package com.bankapp.bankapp.respositories;

import com.bankapp.bankapp.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM bank_account b  " +
                    "JOIN bank_agency a ON a.id = b.agency_number " +
                    "WHERE b.account_number = :accountNumber "  +
                    "AND b.agency_number = :agencyId ")
    boolean countAgencyAndAccountById (@Param("accountNumber") Integer accountNumber, @Param("agencyId") Integer agencyId);

    @Query(nativeQuery = true,
            value = " SELECT COUNT(*) > 0 " +
                    " FROM bank_agency ba " +
                    " JOIN bank_account ba2 ON ba2.agency_number = ba.id " +
                    " WHERE ba2.associate_id = :associateId AND ba.bank_number = ( " +
                    "   SELECT ba.bank_number " +
                    "   FROM bank_agency ba " +
                    "   JOIN bank_account ba2 ON ba.id = ba2.agency_number " +
                    "   WHERE ba.id = :agencyId AND ba2.associate_id = :associateId )")
boolean countAccountOnBankByAgencyId (@Param("associateId") Integer associateId, @Param("agencyId") Integer agencyId);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM bank_account ba " +
                    "JOIN bank_agency ba2 ON ba.agency_number = ba2.id " +
                    "WHERE ba2.id = :id")
    boolean getAllAccountsByAgency (@Param("id") Integer id);


}
