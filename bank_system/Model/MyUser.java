package com.example.bank_system.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Data
@NoArgsConstructor
@Check(constraints = "role='CUSTOMER' or role='EMPLOYEE' or role='ADMIN'")
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

// @NotEmpty(message = "user name should not be empty")
// @Size(min=4,max=1-,message = "username between 4 and 10 characters")
//  @Column(columnDefinition ="varchar(10) not null unique")
    private String username;

 // @NotEmpty(message = "password should not be empty")
 // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()]).{6,20}$", message = "Password must be at least 6 characters long, and include at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#&()).")
// @Column(columnDefinition ="varchar not null")
    private String password;

    // @NotEmpty(message = "name should not be empty")
   //@Size(min=2,max = 20,message = "name Length between 2 and 20 characters")
   //  @Column(columnDefinition ="varchar(20) not null")
    private String name;

   // @Email(message = "enter valid email")
    //  @Column(columnDefinition ="varchar not null unique")
    private String email;

   // @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN",message = "role should be admin or user or employee")
  // @Column(columnDefinition = "varchar(6) ")
    private String role;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Employee employee;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Customer customer;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    public void setRole(String role){
        this.role=role;
    }


    public void setPassword(String passeord){
        this.password=passeord;
    }





}


