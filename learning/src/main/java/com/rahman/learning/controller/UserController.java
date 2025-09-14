package com.rahman.learning.controller;
import com.rahman.learning.entity.User;
import com.rahman.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserService setUserService(UserService userService) {
        return this.userService = userService;
    }

    @PostMapping
    public Map<String, Object> saveUsers(@RequestBody User user) {
        final Map<String, Object> response = new HashMap<>();
        try {
            if (Objects.isNull(user)) {
                response.put("success", false);
                response.put("message", "User is null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response).getBody();
            }

            Map<String, Object> savedUser = userService.saveUser(user);
            response.put("success", true);
            response.put("message", "save user successfully");
            response.put("data", savedUser);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response).getBody();

        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", e.getMessage());
        }
        return response;
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getUsers(@RequestParam int page, @RequestParam int size) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> usersData = userService.fetchUsers(page, size);
            return ResponseEntity.ok(usersData);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @GetMapping("/id/{id}")
    public Map<String, Object> userDetail(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (Objects.isNull(id)) {
                response.put("success", false);
                response.put("message", "id is null");
            }
            Map<String, Object> user = userService.userDetails(id);
            response.put("success", true);
            response.put("message", "record fetched successfully");
            response.put("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", e.getMessage());
        }
        return response;
    }
}
