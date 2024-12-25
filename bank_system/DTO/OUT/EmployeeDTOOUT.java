package com.example.bank_system_full.DTO.OUT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDTOOUT {

    private String username;
    private String name;
    private String email;
    private String position;
    private Double salary;
}
