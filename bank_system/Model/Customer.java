package com.example.bank_system_full.Model;

import jakarta.persistence.*;
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

 //  @Column(columnDefinition ="varchar(10) not null unique")
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
