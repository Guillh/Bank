package com.bankapp.bankapp.models;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Entity
@Table(name = "assosciates")
@Data
public class Associates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "document_umber")
    private Integer documentNumber;

    @Column(name = "salary")
    private BigDecimal salary;


}
