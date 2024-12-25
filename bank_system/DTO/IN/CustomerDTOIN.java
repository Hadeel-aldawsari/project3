package com.example.bank_system_full.DTO.IN;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTOIN {
    // @NotEmpty(message = "user name should not be empty")
// @Size(min=4,max=1-,message = "username between 4 and 10 characters")
    private String username;

    // @NotEmpty(message = "password should not be empty")
    // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()]).{6,20}$", message = "Password must be at least 6 characters long, and include at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#&()).")
    private String password;

    // @NotEmpty(message = "name should not be empty")
    //@Size(min=2,max = 20,message = "name Length between 2 and 20 characters")
    private String name;

    // @Email(message = "enter valid email")
    private String email;

   // @NotNull
   // @Pattern(regexp = "^05\\d{8}$")
    private String phoneNumber;



}