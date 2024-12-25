package com.example.bank_system.Config;

import com.example.bank_system.Service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    private final MyUserDetailsService myUserDetailsService;

    public ConfigSecurity(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    http.csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        .authenticationProvider(daoAuthenticationProvider())
        .authorizeHttpRequests()
        .requestMatchers("/api/v1/bank/customer/register").permitAll()
        .requestMatchers("/api/v1/bank/employee/register").permitAll()
        .requestMatchers("/api/v1/bank/account/customer/**").hasAuthority("CUSTOMER")
         .requestMatchers("/api/v1/bank/account/admin/view-account-details").hasAuthority("ADMIN")
         .requestMatchers("/api/v1/bank/account/admin/list-accounts").hasAuthority("ADMIN")
         .requestMatchers("/api/v1/bank/account/active-account/**").hasAnyAuthority("ADMIN","EMPLOYEE")
         .requestMatchers("/api/v1/bank/account/block-account/").hasAnyAuthority("ADMIN","EMPLOYEE")

            .anyRequest().authenticated()
        .and()
        .logout().logoutUrl("/api/v1/logout")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .and()
        .httpBasic();
    return http.build();
    }


}
