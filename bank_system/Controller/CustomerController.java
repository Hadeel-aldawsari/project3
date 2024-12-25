package com.example.bank_system_full.Controller;


import com.example.bank_system_full.ApiResponse.ApiResponse;
import com.example.bank_system_full.DTO.IN.CustomerDTOIN;
import com.example.bank_system_full.Model.MyUser;
import com.example.bank_system_full.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    //admin get all customer
    @GetMapping("/admin/get-all-customer")
    public ResponseEntity getAllCustomers(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(customerService.getAllCustomer());
    }

    //customer updated his profile
    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal MyUser myUser,@RequestBody @Valid CustomerDTOIN customerDTOIN){
        customerService.update(myUser.getId(),customerDTOIN);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }


    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser){
        customerService.delete(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("delete successfully"));

    }


}
