package com.example.bank_system.DTO.IN;

import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTOIN {

    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "Account number must be in the format XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;
    private Double balance;
}
