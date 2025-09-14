package com.rahman.learning.service;
import com.rahman.learning.dto.UserDTO;
import com.rahman.learning.entity.User;
import com.rahman.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserRepository setUserRepository(UserRepository userRepository) {
        return this.userRepository = userRepository;
    }

    public Map<String, Object> saveUser(User user) {
        final Map<String, Object> response = new HashMap<>();
        try {
            if (Objects.isNull(user)){
                response.put("error", "please enter user details");
            }
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            response.put("success", true);
            response.put("output", "user has been saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> fetchUsers(int page, int size) {
        Map<String, Object> response = new HashMap<>();
        try {
            Page<User> users = userRepository.findAll(PageRequest.of(page - 1, size));
            List<UserDTO> userDTOs = users.stream()
                    .map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName()))
                    .toList();
            response.put("users", userDTOs);
            response.put("success", true);
            response.put("output", "Users have been fetched successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> userDetails(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (Objects.isNull(id)) {
                response.put("success", false);
                response.put("error", "User ID is null");
                return response;
            }

            Optional<User> userOptional = userRepository.findById(id);

            if (!userOptional.isPresent()) {
                response.put("success", false);
                response.put("message", "User not found by this ID: " + id);
                return response;
            }

            User user = userOptional.get();
            UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName());

            response.put("success", true);
            response.put("message", "User fetched successfully");
            response.put("user", userDTO);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }
}
