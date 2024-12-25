package com.example.bank_system_full.DTO.OUT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTOOUT {
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
}
