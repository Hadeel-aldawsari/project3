package com.example.bank_system.DTO.IN;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTOIN {
    private String username;
    private String password;
    private String name;
    private String email;
    private String position;
    private Double salary;
}
