package com.example.bank_system.Repository;

import com.example.bank_system.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findAccountById(Integer id);
    List<Account> findAccountByCustomerId(Integer customerid);
}
