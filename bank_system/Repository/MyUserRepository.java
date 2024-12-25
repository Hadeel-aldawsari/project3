package com.example.bank_system.Repository;

import com.example.bank_system.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUserByUsername(String username);
    MyUser findMyUserById(Integer id);
    List<MyUser> findMyUserByRole(String role);
}
