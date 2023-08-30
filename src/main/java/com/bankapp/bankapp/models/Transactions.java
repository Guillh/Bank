package com.bankapp.bankapp.models;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_value")
    private BigDecimal transactionValue;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private BankAccount sourceAccountId;

    @ManyToOne
    @JoinColumn(name = "target_account_id")
    private BankAccount targetAccountId;

}
