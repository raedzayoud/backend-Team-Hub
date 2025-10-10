package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.config.JwtService;
import com.smartjira.smartjira.dto.AuthRequest;
import com.smartjira.smartjira.model.User;
import com.smartjira.smartjira.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200") // allow requests from Angular
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@Valid @RequestBody User request) {
        Map<String, String> response = new HashMap<>();
        userService.saveUser(request.getName(), request.getPassword(), request.getEmail());
        response.put("message","User registred succesfully !");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>>login(@Valid @RequestBody AuthRequest request){
        User user = userService.authenticate(request.getEmail(), request.getPassword());
        Map<String,String>response=new HashMap<>();
        if (user != null) {
            String token = jwtService.generateToken(user.getEmail());
            response.put("status","success");
            response.put("token",token);
            response.put("role",user.getRoles().toString());
            return  ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Email or password incorrect");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
