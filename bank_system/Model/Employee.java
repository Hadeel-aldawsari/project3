package com.example.bank_system_full.Model;

import jakarta.persistence.*;
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

    //  @Column(columnDefinition ="varchar not null ")
    private String position;

    //@Column(columnDefinition = "DECIMAL(10, 2)")
    private Double salary;

    @OneToOne
    @JoinColumn(name = "user_id")
    private MyUser user;
}
