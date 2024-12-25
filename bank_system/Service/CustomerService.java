package com.example.bank_system.Service;

import com.example.bank_system.ApiResponse.ApiException;
import com.example.bank_system.DTO.IN.CustomerDTOIN;
import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Repository.MyUserRepository;
import com.example.bank_system.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
   private final MyUserRepository myUserRepository;

    public void CustomerRegister(CustomerDTOIN customerDTOIN){

        String hashPass=new BCryptPasswordEncoder().encode(customerDTOIN.getPassword());

        MyUser user = new MyUser(null,customerDTOIN.getUsername()
        ,hashPass
        ,customerDTOIN.getName()
        ,customerDTOIN.getEmail()
        ,"CUSTOMER",null,null);

        myUserRepository.save(user);
        Customer customer=new Customer(user.getId(), customerDTOIN.getPhoneNumber(),user,null);
        customerRepository.save(customer);

    }




}
