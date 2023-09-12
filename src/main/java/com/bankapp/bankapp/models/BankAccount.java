package com.bankapp.bankapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bank_account")
@Data
@NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "account_number")
    private Integer accountNumber;

//    @ManyToOne
//    @JoinColumn(name = "bank_number")
//    private Bank bankNumber;

    @ManyToOne
    @JoinColumn(name = "agency_number")
    private BankAgency agencyId;

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associates associateId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "transaction_limit")
    private BigDecimal transactionLimit;



}
