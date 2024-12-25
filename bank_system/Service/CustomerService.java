package com.example.bank_system_full.Service;

import com.example.bank_system_full.ApiResponse.ApiException;
import com.example.bank_system_full.DTO.IN.CustomerDTOIN;
import com.example.bank_system_full.DTO.OUT.CustomerDTOOUT;
import com.example.bank_system_full.Model.Customer;
import com.example.bank_system_full.Model.MyUser;
import com.example.bank_system_full.Repository.MyUserRepository;
import com.example.bank_system_full.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    //admin get all customer
    public List<CustomerDTOOUT> getAllCustomer(){
        List<Customer> customers=customerRepository.findAll();
        List<CustomerDTOOUT> dtos=new ArrayList<>();

        for(Customer c:customers){
            CustomerDTOOUT out=new CustomerDTOOUT(c.getUser().getUsername(),c.getUser().getName(),c.getUser().getEmail(),c.getPhoneNumber());
            dtos.add(out);
        }
        return dtos;
    }

    //customer update his data
    public void update(Integer userid, CustomerDTOIN customerDTOIN){
        Customer oldCustomer=customerRepository.findCustomerById(userid);
        if(oldCustomer==null)throw new ApiException("customer not found");

        String hashPass=new BCryptPasswordEncoder().encode(customerDTOIN.getPassword());

        oldCustomer.setPhoneNumber(customerDTOIN.getPhoneNumber());
        oldCustomer.getUser().setName(customerDTOIN.getName());
        oldCustomer.getUser().setEmail(customerDTOIN.getEmail());
        oldCustomer.getUser().setPassword(hashPass);
        oldCustomer.getUser().setUsername(customerDTOIN.getUsername());
        customerRepository.save(oldCustomer);
    }

    //customer delete his data
    public  void delete(Integer customerid){
        MyUser myUser=myUserRepository.findMyUserById(customerid);
        if(myUser==null)throw new ApiException("no customer found by this id");
        myUserRepository.delete(myUser);
    }


}
