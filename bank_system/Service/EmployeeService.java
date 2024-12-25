package com.example.bank_system.Service;

import com.example.bank_system.DTO.IN.CustomerDTOIN;
import com.example.bank_system.DTO.IN.EmployeeDTOIN;
import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.Employee;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Repository.CustomerRepository;
import com.example.bank_system.Repository.EmployeeRepository;
import com.example.bank_system.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final MyUserRepository myUserRepository;

    public void EmployeeRegister(EmployeeDTOIN employeeDTOIN){
        String hashPass=new BCryptPasswordEncoder().encode(employeeDTOIN.getPassword());

        MyUser user = new MyUser(null,employeeDTOIN.getUsername()
                ,hashPass
                ,employeeDTOIN.getName()
                ,employeeDTOIN.getEmail()
                ,"EMPLOYEE",null,null);

        myUserRepository.save(user);
        Employee employee=new Employee(user.getId(), employeeDTOIN.getPosition(),employeeDTOIN.getSalary(),user);
        employeeRepository.save(employee);

    }
}
