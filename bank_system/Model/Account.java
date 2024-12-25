package com.example.bank_system_full.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    //  @Column(columnDefinition ="varchar not null unique")
    private String accountNumber;


   //  @Column(columnDefinition ="decimal not null")
    private Double balance;

   // @Column(columnDefinition = "boolean default false")
    //@AssertFalse
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
