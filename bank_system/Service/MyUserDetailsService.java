package com.example.bank_system.Service;

import com.example.bank_system.ApiResponse.ApiException;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Repository.MyUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;

    public MyUserDetailsService(MyUserRepository authRepository) {
        this.myUserRepository = authRepository;
    }

@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=myUserRepository.findMyUserByUsername(username);
        if(myUser==null) throw new ApiException("wrong username or password");

        return myUser;
    }
}
