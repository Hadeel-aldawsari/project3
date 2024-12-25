package com.example.bank_system_full.DTO.IN;

import lombok.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTOIN {

    //@NotEmpty(message = "account number should not be null unique")
    //@Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "Account number must be in the format XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;

    //@NotNull
    // @DecimalMin("0.0")
    private Double balance;
}
