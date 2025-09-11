package com.rahman.learning.controller;
import com.rahman.learning.entity.User;
import com.rahman.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUsers(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.findAllUsers();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/id/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
