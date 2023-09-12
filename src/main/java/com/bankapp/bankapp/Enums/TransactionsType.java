package com.bankapp.bankapp.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TransactionsType {
    DEPOSIT("deposito", 1),
    TRANSFER("transferencia", 2),
    WITHDRAW("saque", 3);

    private String transactionType;
    private Integer value;
}
