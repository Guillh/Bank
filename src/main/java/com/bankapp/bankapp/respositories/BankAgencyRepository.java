package com.bankapp.bankapp.respositories;

import com.bankapp.bankapp.models.BankAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAgencyRepository extends JpaRepository<BankAgency, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM bank_agency ba  " +
                    "WHERE ba.agency_number = :agencyNumber")
    boolean countAgencyByNumber(@Param("agencyNumber") Integer agencyNumber);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM bank_agency ba  " +
                    "JOIN bank b ON b.bank_number = ba.bank_number " +
                    "WHERE ba.agency_number = :agencyNumber "  +
                    "AND ba.bank_number = :bankNumber ")
    boolean countAgencyAndBankByNumber(@Param("agencyNumber") Integer agencyNumber,@Param("bankNumber") Integer bankNumber);

//    @Query(nativeQuery = true,
//            value = " SELECT  COUNT(*) > 0 " +
//                    " FROM bank_agency ba " +
//                    " WHERE ba.id = :id ")
//    boolean getAgencyById(@Param("id") Integer id);
}
