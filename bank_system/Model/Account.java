package com.example.bank_system.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    //@NotEmpty(message = "account number should not be null unique")
   //@Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "Account number must be in the format XXXX-XXXX-XXXX-XXXX")
    //  @Column(columnDefinition ="varchar not null unique")
    private String accountNumber;

    //@NotNull
    // @DecimalMin("0.0")
   //  @Column(columnDefinition ="decimal not null")
    private Double balance;

   // @Column(columnDefinition = "boolean default false")
    //@AssertFalse
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
