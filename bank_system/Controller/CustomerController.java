package com.example.bank_system.Controller;


import com.example.bank_system.ApiResponse.ApiResponse;
import com.example.bank_system.DTO.IN.CustomerDTOIN;
import com.example.bank_system.Model.Customer;
import com.example.bank_system.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity CustomerRegister(@RequestBody @Valid CustomerDTOIN customerDTOIN){
        customerService.CustomerRegister(customerDTOIN);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));

    }
}
