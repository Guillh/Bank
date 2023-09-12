package com.bankapp.bankapp.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Entity
@Table(name = "associates")
@Data
@NoArgsConstructor
public class Associates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "phone_number")
    private Integer phoneNumber;

    @NotNull
    @Column(name = "document_number")
    private Integer documentNumber;

    @NotNull
    @Column(name = "salary")
    private BigDecimal salary;


}
