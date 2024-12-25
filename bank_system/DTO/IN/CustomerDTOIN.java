package com.example.bank_system.DTO.IN;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTOIN {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;



}
