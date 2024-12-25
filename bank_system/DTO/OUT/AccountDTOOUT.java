package com.example.bank_system.DTO.OUT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTOOUT {
    private String accountNumber;
    private Double balance;
    private Boolean isActive;
}
