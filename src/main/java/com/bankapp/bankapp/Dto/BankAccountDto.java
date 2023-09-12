package com.bankapp.bankapp.Dto;

import com.bankapp.bankapp.models.Associates;
import com.bankapp.bankapp.models.BankAgency;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BankAccountDto {
    @NotNull
    private Integer accountNumber;
//    @NotNull
//    private Integer bankNumber;
    @NotNull
    private Integer agencyId;
    @NotNull
    private Integer associateId;

    

}
