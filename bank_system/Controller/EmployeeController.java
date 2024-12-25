package com.example.bank_system.Controller;

import com.example.bank_system.ApiResponse.ApiResponse;
import com.example.bank_system.DTO.IN.CustomerDTOIN;
import com.example.bank_system.DTO.IN.EmployeeDTOIN;
import com.example.bank_system.Service.CustomerService;
import com.example.bank_system.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity EmployeeRegister(@RequestBody @Valid EmployeeDTOIN employeeDTOIN){
        employeeService.EmployeeRegister(employeeDTOIN);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered successfully"));

    }
}
