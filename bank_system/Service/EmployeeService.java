package com.example.bank_system_full.Service;

import com.example.bank_system_full.ApiResponse.ApiException;
import com.example.bank_system_full.DTO.IN.EmployeeDTOIN;
import com.example.bank_system_full.DTO.OUT.EmployeeDTOOUT;
import com.example.bank_system_full.Model.Employee;
import com.example.bank_system_full.Model.MyUser;
import com.example.bank_system_full.Repository.EmployeeRepository;
import com.example.bank_system_full.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    //admin get all Emp
    public List<EmployeeDTOOUT> getAllEmployee(){
        List<Employee> employees=employeeRepository.findAll();
        List<EmployeeDTOOUT> dtos=new ArrayList<>();

        for(Employee e:employees){
            EmployeeDTOOUT out=new EmployeeDTOOUT(e.getUser().getUsername(),e.getUser().getName(),e.getUser().getEmail(),e.getPosition(),e.getSalary());
            dtos.add(out);
        }
        return dtos;
    }


    //Emp update his data
    public void update(Integer userid, EmployeeDTOIN employeeDTOIN){
        Employee oldEmployee=employeeRepository.findEmployeeById(userid);
        if(oldEmployee==null)throw new ApiException("employee not found");

        String hashPass=new BCryptPasswordEncoder().encode(employeeDTOIN.getPassword());

        oldEmployee.getUser().setName(employeeDTOIN.getName());
        oldEmployee.getUser().setEmail(employeeDTOIN.getEmail());
        oldEmployee.getUser().setPassword(hashPass);
        oldEmployee.getUser().setUsername(employeeDTOIN.getUsername());
        oldEmployee.setPosition(employeeDTOIN.getPosition());
        oldEmployee.setSalary(employeeDTOIN.getSalary());
        employeeRepository.save(oldEmployee);
    }

    //Emp
    public  void delete(Integer emid){
        MyUser myUser=myUserRepository.findMyUserById(emid);
        if(myUser==null)throw new ApiException("no employee found by this id");

        myUserRepository.delete(myUser);
    }

}
