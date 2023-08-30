package com.bankapp.bankapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bank_angency")
@Data
public class BankAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "agency_number")
    private Integer agencyNumber;

    @ManyToOne
    @JoinColumn(name = "bank_number")
    private Bank bankNumber;

}
