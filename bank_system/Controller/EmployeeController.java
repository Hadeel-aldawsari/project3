package com.example.bank_system_full.Controller;

import com.example.bank_system_full.ApiResponse.ApiResponse;
import com.example.bank_system_full.DTO.IN.EmployeeDTOIN;
import com.example.bank_system_full.Model.MyUser;
import com.example.bank_system_full.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    //admin get all customer
    @GetMapping("/admin/get-all-employee")
    public ResponseEntity getAllEmployee(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(employeeService.getAllEmployee());
    }

    //employee updated his profile
    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal MyUser myUser,@RequestBody @Valid EmployeeDTOIN employeeDTOIN){
        employeeService.update(myUser.getId(),employeeDTOIN);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }


    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser){
        employeeService.delete(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("delete successfully"));

    }
}
