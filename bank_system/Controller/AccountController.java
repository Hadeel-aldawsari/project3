package com.example.bank_system.Controller;

import com.example.bank_system.ApiResponse.ApiResponse;
import com.example.bank_system.DTO.IN.AccountDTOIN;
import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    //customer will add his account
    @PostMapping("/customer/add-account")
    public ResponseEntity AddAccount(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid AccountDTOIN accountDTOIN){
        accountService.addAccount(myUser.getId(),accountDTOIN);
        return ResponseEntity.status(200).body(new ApiResponse("Account added successfully"));
    }

    //customer view his account details
    @GetMapping("/customer/view-my-account/{accountid}")
    public ResponseEntity viewMyAccountDetails(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountid){
        return ResponseEntity.status(200).body(accountService.viewMyAccountDetails(myUser.getId(),accountid));
    }

    //customer view all his accounts
    @GetMapping("/customer/list-my-accounts")
    public ResponseEntity ListMyAccounts(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(accountService.ListMyAccounts(myUser.getId()));
    }

    //customer Deposit ammout
    @PutMapping("/customer/deposit/{accountid}/{amount}")
    public ResponseEntity Deposit(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountid,@PathVariable Double amount){
        accountService.Deposit(myUser.getId(), accountid,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Deposit done"));
    }

    //customer Withdraw ammout
    @PutMapping("/customer/Withdraw/{accountid}/{amount}")
    public ResponseEntity Withdraw(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountid,@PathVariable Double amount){
        accountService.Withdraw(myUser.getId(), accountid,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Withdraw done"));
    }

//cusomer Transfer
    @PutMapping("/customer/transfer/{accountid1}/{accountid2}/{amount}")
    public ResponseEntity Transfer(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountid1,@PathVariable Integer accountid2,@PathVariable Double amoun){
        accountService.Transfer(myUser.getId(), accountid1,accountid2,amoun);
        return ResponseEntity.status(200).body(new ApiResponse("transfer done"));
    }

    //admin  view specific account
    @GetMapping("/admin/view-account-details/{accountid}")
    public ResponseEntity viewAccountDetails(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountid){
        return ResponseEntity.status(200).body(accountService.viewAccountDetails(myUser.getId(),accountid));
    }

    //admin list all accounts
    @GetMapping("/admin/list-accounts")
    public ResponseEntity viewAccountDetails(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(accountService.ListAllAccounts());
    }


    //Admin & Employee
    @PutMapping("/active-account/{accountid}")
    public ResponseEntity ActiveAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountid){
        accountService.ActiveAccount(myUser.getId(),accountid);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated"));

    }

    //Admin & Employee
    @PutMapping("/block-account/{accountid}")
    public ResponseEntity BlockAccount(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer accountid){
        accountService.BlockAccount(myUser.getId(),accountid);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked"));

    }


}
