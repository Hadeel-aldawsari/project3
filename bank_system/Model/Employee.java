package com.example.bank_system.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private Integer id;

// @NotNull
    private String position;

// @NotNull
// @DecimalMin("0.0")
    private Double salary;

    @OneToOne
    @JoinColumn(name = "user_id")
    private MyUser user;
}
