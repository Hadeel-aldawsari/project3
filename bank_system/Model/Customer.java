package com.example.bank_system.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

   @Id
    private Integer id;

    @NotNull
    @Pattern(regexp = "^05\\d{8}$")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private MyUser user;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

 public Integer getId() {
  return id;
 }
}
