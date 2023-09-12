package com.bankapp.bankapp.Dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionsDto {
    private Integer sourceAccountId;
    private Integer targetAccountId;
    private Date transactionDate;
    @NotNull
    private BigDecimal transactionValue;
}
