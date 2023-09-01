package com.bankapp.bankapp.Dto;

import com.bankapp.bankapp.models.Bank;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
public class BankAgencyDto {

    @NotNull
    private Integer agencyNumber;

    @NotNull
    private Integer bankNumber;
}
