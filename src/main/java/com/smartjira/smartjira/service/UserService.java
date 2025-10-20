package com.smartjira.smartjira.service;

import com.smartjira.smartjira.enums.Role;
import com.smartjira.smartjira.model.User;
import com.smartjira.smartjira.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private  final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User authenticate(String email, String password) {
       Optional<User> u=userRepository.findByEmail(email);
       if(u.isPresent()){
           if(password.equals(u.get().getPassword())){
               return u.get();
           }
       }
       else{
           return null;
       }
       return null;
    }

    public User saveUser(String name, String password, String email) {
        if (userRepository.existsByEmail((email))) {
            throw new RuntimeException("email already exists");
        }
        User user = new User();
        user.setRoles(Role.DEVELOPER);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);

        return userRepository.save(user);
    }


}
