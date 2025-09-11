package com.rahman.learning.controller;
import com.rahman.learning.dto.AuthRequest;
import com.rahman.learning.entity.User;
import com.rahman.learning.service.UserService;
import com.rahman.learning.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        User user = userService.findByEmail(authRequest.getEmail());
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        if (user.getPassword().equals(authRequest.getPassword())) {
            String token = JwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok().body("Bearer " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
