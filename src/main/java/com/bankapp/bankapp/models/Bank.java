package com.bankapp.bankapp.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bank")
@Data
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_number")
    private Integer bankNumber;

    @NotNull
    @Column(name = "bank_name")
    private String bankName;

}
