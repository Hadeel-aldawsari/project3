package com.example.bank_system_full.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

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


//  @Column(columnDefinition ="varchar(10) not null unique")
    private String username;


// @Column(columnDefinition ="varchar not null")
    private String password;


   //  @Column(columnDefinition ="varchar(20) not null")
    private String name;

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


