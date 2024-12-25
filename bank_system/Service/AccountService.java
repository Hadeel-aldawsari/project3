package com.example.bank_system.Service;

import com.example.bank_system.ApiResponse.ApiException;
import com.example.bank_system.DTO.IN.AccountDTOIN;
import com.example.bank_system.DTO.OUT.AccountDTOOUT;
import com.example.bank_system.Model.Account;
import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Repository.AccountRepository;
import com.example.bank_system.Repository.CustomerRepository;
import com.example.bank_system.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final CustomerRepository customerRepository;
    private final  MyUserRepository myUserRepository;
    private final AccountRepository accountRepository;

    //Customer add account
    public void addAccount(Integer customerid,AccountDTOIN accountDTOIN){
        Customer customer=customerRepository.findCustomerById(customerid);
        if(customer==null)throw new ApiException("customer not found");
        //create account
        Account account=new Account(null,accountDTOIN.getAccountNumber(),accountDTOIN.getBalance(),false,customer);
        accountRepository.save(account);
    }

    //Customer view his account details
    public AccountDTOOUT viewMyAccountDetails(Integer customerid,Integer accountid){
        Customer customer=customerRepository.findCustomerById(customerid);
        if(customer ==null)throw new ApiException("customer not found");

        Account account=accountRepository.findAccountById(accountid);
        if(account==null)
            throw new ApiException("Account not found");

        if(account.getCustomer().getId()!=customerid)
            throw new ApiException("Unauthorized, to view this account");

        //create dto out
        AccountDTOOUT accountDTOOUT=new AccountDTOOUT(account.getAccountNumber(),account.getBalance(),account.getIsActive());
        return accountDTOOUT;
    }

// customer List his all Accounts
    public List<AccountDTOOUT> ListMyAccounts(Integer userid){
        //this is optional
        MyUser myUser=myUserRepository.findMyUserById(userid);
        if(myUser ==null)throw new ApiException("user not found");

        List<Account> accounts=accountRepository.findAccountByCustomerId(userid);
        if(accounts==null || accounts.isEmpty())  throw new ApiException("there is no account added");

        List<AccountDTOOUT>accountDTOOUTS=new ArrayList<>();

        for(Account a:accounts){
            AccountDTOOUT dto=new AccountDTOOUT(a.getAccountNumber(),a.getBalance(),a.getIsActive());
            accountDTOOUTS.add(dto);
        }
        return accountDTOOUTS;
    }

    //customer Deposit ammout
    public void Deposit(Integer customerid,Integer accontid,Double amount){
        Customer customer=customerRepository.findCustomerById(customerid);
        if(customer ==null)throw new ApiException("customer not found");

        Account account=accountRepository.findAccountById(customerid);
        if(account==null)
            throw new ApiException("Account not found");

        if(!account.getIsActive())
            throw new ApiException("Account unActive,you can't not do any operation");

        if(account.getCustomer().getId()!=customerid)
            throw new ApiException("Unauthorized, to deposit to this account");

        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }

    //customer Withdraw
    public void Withdraw(Integer customerid,Integer accontid,Double amount){
        Customer customer=customerRepository.findCustomerById(customerid);
        if(customer ==null)throw new ApiException("customer not found");

        Account account=accountRepository.findAccountById(customerid);
        if(account==null)
            throw new ApiException("Account not found");

        if(!account.getIsActive())
            throw new ApiException("Account unActive,you can't not do any operation");

        if(account.getCustomer().getId()!=customerid)
            throw new ApiException("Unauthorized, to withdraw from this account");

        if(amount>account.getBalance())
            throw new ApiException("sorry, ammount grater than your balance");

        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }

    public void Transfer (Integer customerid,Integer accontid1,Integer accontid2,Double amount){
        Customer customer=customerRepository.findCustomerById(customerid);
        if(customer ==null)throw new ApiException("customer not found");

        Account account1=accountRepository.findAccountById(customerid);
        if(account1==null)
            throw new ApiException("Account not found");

        if(!account1.getIsActive())
            throw new ApiException("Account unActive,you can't not do any operation");

        if(account1.getCustomer().getId()!=customerid)
            throw new ApiException("Unauthorized, to transfer from this account");

        Account account2=accountRepository.findAccountById(accontid2);
        if(account2==null)
            throw new ApiException("the Account you try to transfer to it not found");

        if(!account2.getIsActive())
            throw new ApiException("unSuccessful,some thing went wrong with 2 account");

        if(amount>account1.getBalance())
            throw new ApiException("sorry, amount grater than your balance");

        account1.setBalance(account1.getBalance()-amount);
        account2.setBalance(account2.getBalance()+amount);
        accountRepository.save(account1);
        accountRepository.save(account2);

    }


//************* Admin & E

    //admin view customer account details
    public AccountDTOOUT viewAccountDetails(Integer userid,Integer accountid){
       //this is optional because he already logged in
        MyUser myUser=myUserRepository.findMyUserById(userid);
        if(myUser ==null)throw new ApiException("user not found");

        Account account=accountRepository.findAccountById(accountid);
        if(account==null)
            throw new ApiException("Account not found");

        //create dto out
        AccountDTOOUT accountDTOOUT=new AccountDTOOUT(account.getAccountNumber(),account.getBalance(),account.getIsActive());
        return accountDTOOUT;

    }
     //get all
    //admin view all user account list
    public List<Account> ListAllAccounts(){
        List<Account> accountList=accountRepository.findAll();
        if(accountList.isEmpty()) throw new ApiException("there is no account yet");
        return accountList;
    }

    public void ActiveAccount(Integer userid,Integer accountid){
        //this is optional
        MyUser myUser=myUserRepository.findMyUserById(userid);
        if(myUser ==null)throw new ApiException("user not found");

        Account account=accountRepository.findAccountById(accountid);
        if(account==null)
            throw new ApiException("Account not found");

        if(account.getIsActive())throw new ApiException("Account already active");

        account.setIsActive(true);
        accountRepository.save(account);
    }

    //admin & E block an account
    public void BlockAccount(Integer userid,Integer accountid){
        //this is optional
        MyUser myUser=myUserRepository.findMyUserById(userid);
        if(myUser ==null)throw new ApiException("user not found");

        Account account=accountRepository.findAccountById(accountid);
        if(account==null)
            throw new ApiException("Account not found");

        if(!account.getIsActive())throw new ApiException("Account already unActive");

        account.setIsActive(false);
        accountRepository.save(account);

    }
}
