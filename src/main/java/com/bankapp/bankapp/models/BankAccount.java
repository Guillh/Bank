package com.bankapp.bankapp.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bank_account")
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "account_number")
    private Integer accountNumber;

    @ManyToOne
    @JoinColumn(name = "bank_number")
    private BankAgency bankNumber;

    @ManyToOne
    @JoinColumn(name = "agency_number")
    private BankAgency agencyId;

    @ManyToOne
    @JoinColumn(name = "associated_id")
    private Associates associatedId;

    @Column(name = "balance")
    private BigDecimal balance;


    @Column(name = "transaction_limit")
    private BigDecimal transactionLimit;

}
