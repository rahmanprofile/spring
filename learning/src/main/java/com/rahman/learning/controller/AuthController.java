package com.rahman.learning.controller;
import com.rahman.learning.dto.AuthRequest;
import com.rahman.learning.entity.User;
import com.rahman.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        User user = userService.findByEmail(authRequest.getEmail());
        if (user == null) {
            return "User not found";
        }

        if (user.getPassword().equals(authRequest.getPassword())) {
            return "Authentication Successful";
        } else {
            return "Invalid credentials";
        }
    }
}
